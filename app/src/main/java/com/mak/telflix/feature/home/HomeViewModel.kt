package com.mak.telflix.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mak.telflix.domain.interactors.home.PopularTVParams
import com.mak.telflix.domain.interactors.home.RefreshPopularTV
import com.mak.telflix.domain.paginate.Paginator
import com.mak.telflix.domain.util.TFConstants.DEFAULT_PAGE
import com.mak.telflix.domain.util.TFConstants.DEFAULT_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val refreshPopularTv: RefreshPopularTV
): ViewModel() {

    var state by mutableStateOf(HomeState())
    private val paginator = Paginator(
        initialKey = DEFAULT_PAGE,
        onLoadUpdated = { loading ->
            state = state.copy(isLoading = loading)
        },
        onRequest = { nextPage ->
            refreshPopularTv(PopularTVParams(page = nextPage))
        },
        getNextKey = { items ->
            state.page + 1
        },
        onError = { throwable ->
            state = state.copy(error = throwable?.message)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty() || items.size < DEFAULT_PAGE_SIZE
            )
        }
    )

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

}
