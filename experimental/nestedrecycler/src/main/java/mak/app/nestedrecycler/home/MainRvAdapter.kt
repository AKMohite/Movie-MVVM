package mak.app.nestedrecycler.home

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import mak.app.nestedrecycler.model.Contents
import mak.app.nestedrecycler.model.ContentsType
import mak.app.nestedrecycler.model.SectionHeader
import mak.app.nestedrecycler.model.subcomponents.BodyItems
import mak.app.nestedrecycler.model.subcomponents.BodySection
import mak.app.nestedrecycler.model.subcomponents.HeaderContents
import mak.app.nestedrecycler.utils.SwRecyclerViewAdapter


class MainRvAdapter
    (
    context: Context,
    fm: FragmentManager,
    list: ArrayList<Contents?>,
    clickListener: OnViewClickListener?
) : SwRecyclerViewAdapter<Contents?>(context, list, TAG, clickListener) {
    private val fm: FragmentManager = fm
    private val subHorRvItemDecoration: SubHorRvItemDecoration

    init {
        this.subHorRvItemDecoration = SubHorRvItemDecoration(context)
    }

    override fun createView(
        context: Context?,
        viewGroup: ViewGroup?,
        viewType: Int
    ): View {
        return if (viewType == HeaderContents.VIEWTYPE_VALUE) {
            // 0 == Header contents
            LayoutInflater.from(context).inflate(R.layout.main_item_header, viewGroup, false)
        } else if (viewType == BodySection.FULL_VIEWTYPE_VALUE) {
            // BODY contents (Span 2)
            LayoutInflater.from(context).inflate(R.layout.main_item_body_full, viewGroup, false)
        } else if (viewType == BodySection.HALF_VIEWTYPE_VALUE) {
            // BODY contents (Span 1)
            LayoutInflater.from(context).inflate(R.layout.main_item_body_half, viewGroup, false)
        } else if (viewType == FOOTER_LOADMORE) {
            // list.size() + 1 == Footer contents
            LayoutInflater.from(context).inflate(R.layout.main_item_footer, viewGroup, false)
        } else {
            // Section Headers
            LayoutInflater.from(context)
                .inflate(R.layout.main_item_section_header, viewGroup, false)
        }
    }

    protected override fun bindView(viewType: Int, item: Contents, viewHolder: ViewHolder) {
        if (viewType == HeaderContents.VIEWTYPE_VALUE) {
            if (item is HeaderContents) {
                val vp: InfiniteViewPager =
                    viewHolder.getView(R.id.main_item_header_viewpager) as InfiniteViewPager
                val wrrappedAdapter: InfinitePagerAdapter =
                    InfinitePagerAdapter(HeaderViewPagerAdapter(fm, item.itemList))
                vp.setAdapter(wrrappedAdapter)
                vp.setTag(wrrappedAdapter)

                val indicator: ViewPagerIndicator =
                    viewHolder.getView(R.id.main_item_header_vp_indicator) as ViewPagerIndicator
                indicator.setCircleMarginDP(6)
                indicator.setViewPager(vp, wrrappedAdapter.getRealCount())
            }
        } else if (viewType == BodySection.FULL_VIEWTYPE_VALUE || viewType == BodySection.HALF_VIEWTYPE_VALUE) {
            // BODYs
            if (item is BodySection) {
                val rv: SnapRecyclerView =
                    viewHolder.getView(R.id.main_item_body_rv_horizontal) as SnapRecyclerView
                rv.setHasFixedSize(true)
                rv.setLayoutManager(
                    SnappyLinearLayoutManager(
                        context,
                        SnappyLinearLayoutManager.HORIZONTAL,
                        false
                    )
                )
                rv.removeItemDecoration(subHorRvItemDecoration)
                rv.addItemDecoration(subHorRvItemDecoration)

                val adapter: SectionRvAdapter =
                    SectionRvAdapter(context, item.bodyItemses, clickListener)
                rv.setAdapter(adapter)
            } else {
                val bodyItems = item as BodyItems

                // Span 1 size Bodys.
                val tvTitle = viewHolder.getView(R.id.main_item_h_section_tv_title) as TextView
                tvTitle.text = if (bodyItems.title != null) bodyItems.title else ""

                val ivBg = viewHolder.getView(R.id.main_item_h_section_bg_iv) as ImageView
                if (!TextUtils.isEmpty(bodyItems.thumbnailImgUrl)) {
                    Picasso.with(context)
                        .load(bodyItems.thumbnailImgUrl)
                        .fit()
                        .centerCrop()
                        .into(ivBg)
                }

                val tvDesc = viewHolder.getView(R.id.main_item_h_section_tv_desc) as TextView
                tvDesc.text = if (bodyItems.desc != null) bodyItems.desc else ""

                val ibtnFavorites =
                    viewHolder.getView(R.id.main_item_h_section_ibtn_favorites) as ImageButton
                if (!ibtnFavorites.isEnabled) {
                    ibtnFavorites.isEnabled = true
                }
                ibtnFavorites.setImageResource(
                    if (bodyItems.isFavorites) R.drawable.btn_star_big_on else R.drawable.btn_star_big_off
                )
            }
        } else if (viewType == FOOTER_LOADMORE) {
            // FOOTER -> Load more (Contents item is Null)
            val pb = viewHolder.getView(R.id.main_item_footer_pb) as ProgressBar
            pb.isIndeterminate = true
        } else {
            // Section Header
            val sectionHeader = item as SectionHeader

            val tvHeader = viewHolder.getView(R.id.main_item_sh_tv_title) as TextView
            tvHeader.text = if (sectionHeader.title != null) sectionHeader.title else ""
        }
    } // end of bindView() methods

    override fun getItemViewType(position: Int): Int {
        if (getItem(position) != null) {
            if (isHeader(position)) {
                return HeaderContents.VIEWTYPE_VALUE
            } else if (getItem(position) is BodySection) {
                return BodySection.FULL_VIEWTYPE_VALUE
            } else if (getItem(position) is BodyItems) {
                return BodySection.HALF_VIEWTYPE_VALUE
            }
            return SectionHeader.VIEWTYPE_VALUE
        } else {
            return FOOTER_LOADMORE
        }
    }

    val firstHalfBodyContentsPosition: Int
        get() {
            for (i in 0 until getItemCount()) {
                if (getItem(i).getContentType() === ContentsType.BODY_HALF) {
                    return i
                }
            }
            return -1
        }

    fun showLoadMore() {
        if (!isEmptyList() && getItem(list.size() - 1) != null) {
            addItem(null)
        }
    }

    fun hideLoadMore() {
        removeLast()
    }

    fun hasHeader(): Boolean {
        return (!isEmptyList() && getItem(0).getContentType() === ContentsType.HEADER)
    }

    fun isHeader(position: Int): Boolean {
        return (!isEmptyList() && position == 0 && getItem(position).getContentType() === ContentsType.HEADER)
    }

    fun isFooter(position: Int): Boolean {
        return (!isEmptyList() && position == footerPosition)
    }

    val footerPosition: Int
        get() = (if (isEmptyList()) 0 else list.size())

    companion object {
        val TAG: String = MainRvAdapter::class.java.simpleName
        const val FOOTER_LOADMORE: Int = 99
    }
}