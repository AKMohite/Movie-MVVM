package mak.app.nestedrecycler.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MakRecyclerview : RecyclerView {
    private var emptyView: View? = null
    private val observer: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            val adapter = adapter
            if (adapter != null && emptyView != null) {
                if (adapter.itemCount == 0) {
                    emptyView?.visibility = VISIBLE
                    this@MakRecyclerview.visibility = GONE
                } else {
                    emptyView?.visibility = GONE
                    this@MakRecyclerview.visibility = VISIBLE
                }
            }
        }
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(
        context, attrs
    )

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context, attrs, defStyle
    )

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(observer)
        observer.onChanged()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        adapter?.unregisterAdapterDataObserver(observer)
    }

    fun setEmptyView(view: View) {
        this.emptyView = view
        observer.onChanged()
    }
}