package com.mak.telflix.ui.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mak.telflix.data.repository.NetworkState
import com.mak.telflix.data.vo.popularmovies.Result
import io.reactivex.disposables.CompositeDisposable

class PopularMoviesViewModel(private val moviePageListRepository: MoviePageListRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePagedList : LiveData<PagedList<Result>> by lazy {
        moviePageListRepository.fetchMoviePagedList(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        moviePageListRepository.getNetworkState()
    }

    fun listIsEmpty() : Boolean{
        return moviePagedList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }
}