package mak.app.nestedrecycler.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView


abstract class SwRecyclerViewAdapter<T>(
    protected var context: Context,
    list: MutableList<T>,
    tag: Any?,
    clickListener: OnViewClickListener?
) : RecyclerView.Adapter<SwRecyclerViewAdapter.ViewHolder?>() {
    protected var list: MutableList<T>?
    protected var clickListener: OnViewClickListener? = clickListener
    var tag: Any? = null

    @JvmOverloads
    constructor(
        context: Context,
        list: MutableList<T>,
        clickListener: OnViewClickListener? = null
    ) : this(context, list, null, clickListener)

    init {
        this.list = ArrayList()
        this.tag = tag
        setItem(list, false)
    }

    /**
     * viewType에 따라서 ViewHolder를만들고 bind할 View의 인스턴스를 반환 한다.
     */
    protected abstract fun createView(context: Context?, viewGroup: ViewGroup?, viewType: Int): View

    /**
     * createView()에서 생성한 View와 position의 Data를 기반으로 뷰를 업데이트 한다.
     */
    protected abstract fun bindView(viewType: Int, item: T?, viewHolder: ViewHolder?)

    fun clearItems() {
        if (list != null && !list!!.isEmpty()) {
            list!!.clear()
            notifyDataSetChanged()
        }
    }

    fun setItem(list: MutableList<T>, isClaearList: Boolean) {
        if (this.list != null) {
            if (isClaearList) {
                clearItems()
            }
            this.list = list
            notifyDataSetChanged()
        }
    }

    fun addItems(addItems: List<T?>) {
        if (list != null) {
            val startPos = list!!.size
            list!!.addAll(addItems)
            notifyItemRangeInserted(startPos, addItems.size)
        }
    }

    fun addItem(addItem: T) {
        if (list != null) {
            list!!.add(addItem)
            notifyItemInserted(list!!.size)
        }
    }

    fun addItem(position: Int, insertItem: T) {
        if (list != null) {
            list!!.add(position, insertItem)
            notifyItemRangeInserted(position, position + 1)
        }
    }

    fun removeItem(position: Int): T? {
        if (list != null) {
            if (position >= 0 && position < list!!.size) {
                val removedItem = list!!.removeAt(position)
                notifyItemRemoved(position)
                return removedItem
            }
        }
        return null
    }

    fun removeLast(): T? {
        return removeItem(if (list != null) list!!.size - 1 else -1)
    }

    fun replaceItem(position: Int, replaceItem: T) {
        if (list != null) {
            if (position >= 0 && position < list!!.size) {
                if (list!!.set(position, replaceItem) != null) {
                    notifyItemRemoved(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            createView(context, parent, viewType),
            tag, clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        bindView(viewType, getItem(position), holder)
    }

    override fun getItemCount(): Int {
        return (if (list != null) list!!.size else 0)
    }

    fun getItem(@IntRange(from = 0) position: Int): T? {
        return (if ((list != null && position < list!!.size)) list!![position] else null)
    }

    val isEmptyList: Boolean
        get() = (list == null || list!!.isEmpty())

    class ViewHolder
        (view: View, val tag: Any?, clickListener: OnViewClickListener?) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private val views: MutableMap<Int, View>
        private val clickListener: OnViewClickListener? = clickListener

        init {
            if (this.clickListener != null) {
                view.setOnClickListener(this)
            }
            views = HashMap()
            // insert RootView
            views[0] = view
        }

        fun findViewById(@IdRes id: Int): View? {
            // 헷갈려서 넣음.
            return getView(id)
        }

        fun getView(@IdRes id: Int): View? {
            if (!views.containsKey(id)) {
                initViewById(id)
            }
            return views[id]
        }

        fun initViewById(@IdRes id: Int) {
            // get RootView
            val view = (if (getView(0) != null) getView(0)!!.findViewById<View>(id) else null)
            if (view != null) {
                if (view.isClickable) {
                    view.setOnClickListener(this)
                }
                views[id] = view
            }
        }

        override fun onClick(v: View) {
            if (clickListener != null) {
                clickListener.onClicked(this, v, adapterPosition)
            }
        }
    } // end of ViewHolder class
}