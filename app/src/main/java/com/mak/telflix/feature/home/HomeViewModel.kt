package com.mak.telflix.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mak.telflix.domain.interactors.home.RefreshPopularTV
import com.mak.telflix.domain.interactors.home.PopularTVParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val refreshPopularTv: RefreshPopularTV
): ViewModel() {

    init {
        loadData()
    }

    private fun loadData() {
        refreshHomePage()
    }

    private fun refreshHomePage() {
        refreshPopularTv(PopularTVParams()).onEach { data ->
            Log.d("TAG", "getHomePage: $data")
        }.launchIn(viewModelScope)
    }
}