package mak.app.nestedrecycler.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mak.app.nestedrecycler.BaseViewModel
import mak.app.nestedrecycler.model.Contents
import mak.app.nestedrecycler.model.SectionHeader
import mak.app.nestedrecycler.model.subcomponents.BodyItems
import mak.app.nestedrecycler.model.subcomponents.BodySection
import mak.app.nestedrecycler.model.subcomponents.HeaderContents
import java.util.Random
import java.util.concurrent.TimeUnit

data class NestedState(
    val results: List<Contents> = emptyList(),
    val isLoadMore: Boolean = false
)

class NestedViewModel: BaseViewModel() {

    companion object {
        val TAG = NestedViewModel::class.java.simpleName
    }

    private val _state = MutableStateFlow(NestedState())
    val state: Flow<NestedState>
        get() = _state.asStateFlow()

    private var imgs: Array<String> = arrayOf(
        "http://burkdog.cafe24.com/wp/wp-content/uploads/2016/06/rxjk.jpg",
        "https://d13yacurqjgara.cloudfront.net/users/46315/screenshots/936973/shot.gif",
        "http://designmodo.com/wp-content/uploads/2013/07/Long-Shadows.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/fox-icon.jpg",
        "https://d13yacurqjgara.cloudfront.net/users/72548/screenshots/976228/flat-ui-kit.png",
        "http://designmodo.com/wp-content/uploads/2013/07/clock.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/long.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/man_of_steel_icon.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/safari_icon.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/designmodo_long_shadow.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/m.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/rubee.jpg",
        "https://d13yacurqjgara.cloudfront.net/users/46315/screenshots/936548/retina.png",
        "http://designmodo.com/wp-content/uploads/2013/07/iosicons_flat.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/Long-Shadows.jpg",
        "http://burkdog.cafe24.com/wp/wp-content/uploads/2016/06/rxjk.jpg",
        "https://d13yacurqjgara.cloudfront.net/users/46315/screenshots/936973/shot.gif",
        "http://designmodo.com/wp-content/uploads/2013/07/Long-Shadows.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/fox-icon.jpg",
        "https://d13yacurqjgara.cloudfront.net/users/72548/screenshots/976228/flat-ui-kit.png",
        "http://designmodo.com/wp-content/uploads/2013/07/clock.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/long.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/man_of_steel_icon.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/safari_icon.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/designmodo_long_shadow.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/m.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/rubee.jpg",
        "https://d13yacurqjgara.cloudfront.net/users/46315/screenshots/936548/retina.png",
        "http://designmodo.com/wp-content/uploads/2013/07/iosicons_flat.jpg",
        "http://designmodo.com/wp-content/uploads/2013/07/Long-Shadows.jpg"
    )
    private var txts: Array<String> = arrayOf(
        "Lose", "away", "offset", "가나다라", "father", "API demo", "Shine on you",
        "Kids", "Number one", "Plastic", "Bawlings", "Dsco", "Soda pop confusion",
        "습관", "겨울은 가고", "내 손을 잡아줘", "ordinary joe", "safe and sound",
        "Lightning", "Refeeling", "Romance", "헤픈엔딩",
        "Lose", "away", "offset", "가나다라", "father", "API demo", "Shine on you",
        "Kids", "Number one", "Plastic", "Bawlings", "Dsco", "Soda pop confusion",
    )
    private var categories: Array<String> = arrayOf(
        "Game", "Movie", "Utility", "Photo", "Video", "Study", "For kids"
    )

    private var isLoading = false

    fun isLoading(): Boolean {
        return isLoading
    }

    fun setLoading(loading: Boolean) {
        isLoading = loading
    }

    fun retrieveMainListDatas(isLoadMore: Boolean) {
        if (isLoading) return
        uiScope.launch {
            var result: ArrayList<Contents> = ArrayList()
            if (isLoadMore) {
                // add dummy body datas
                for (i in 1..10) {
                    result.add(getRandomBodyItems(i, Random()))
                }
                // some running operations
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(2))
                } catch (ie: Exception) {
//                    subscriber.onError(ie)
                } finally {
                    _state.update { it.copy(results = result) }
                }
            } else {
                // refresh list items [ DUMMY DATAS ]
                result = retrieveDummyDatas()
                _state.update { it.copy(results = result) }
            }

            isLoading = false;
//            if (view != null && isLoadMore) {
//                view.loadMoreCompleted();
//            }
        }
    }

    private fun retrieveDummyDatas(): ArrayList<Contents> {
        val result = ArrayList<Contents>()
        val r = Random()


        // put header
        result.add(HeaderContents())


        // add first section header
        result.add(SectionHeader("Section 1"))


        // insert first section body items.
        val firstSectionResult = ArrayList<BodyItems>()

        // [1] get dummy datas
        for (i in 0 until imgs.size) {
            firstSectionResult.add(
                BodyItems(
                    i + 1, imgs[i], txts[i], ""
                )
            )
        }
        val firstSection = BodySection()
        firstSection.bodyItemses = firstSectionResult
        result.add(firstSection) // put result array list


        // add second section header
        result.add(SectionHeader("Section 2"))


        // insert second section body items.
        val secondSectionResult = ArrayList<BodyItems>()

        // [2] get dummy datas
        for (i in 0 until imgs.size) {
            secondSectionResult.add(
                BodyItems(
                    i + 1,
                    imgs[Math.abs(imgs.size - i - 1)],
                    txts[Math.abs(imgs.size - i - 1)], ""
                )
            )
        }
        val secondSection = BodySection()
        secondSection.bodyItemses = secondSectionResult
        result.add(secondSection) // put result array list


        // add third section header
        result.add(SectionHeader("Section 3"))


        // add default normal items  [SPAN 1]
        for (i in 1..10) {
            result.add(getRandomBodyItems(i, r))
        }

        return result
    }

    private fun getRandomBodyItems(i: Int, r: Random): BodyItems {
        return BodyItems(i, imgs[r.nextInt(imgs.size)], txts[r.nextInt(txts.size)], categories[r.nextInt(categories.size)])
    }

}