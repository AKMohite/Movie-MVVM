package com.mak.telflix.ui.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mak.telflix.data.api.POST_PER_PAGE
import com.mak.telflix.data.api.TMDBAPIInterface
import com.mak.telflix.data.repository.MovieDataSource
import com.mak.telflix.data.repository.MovieDataSourceFactory
import com.mak.telflix.data.repository.NetworkState
import com.mak.telflix.data.vo.popularmovies.Result
import io.reactivex.disposables.CompositeDisposable

class MoviePageListRepository(private val apiService: TMDBAPIInterface) {

    lateinit var moviePageList: LiveData<PagedList<Result>>
    lateinit var moviesDaFactory: MovieDataSourceFactory

    fun fetchMoviePagedList(compositeDisposable: CompositeDisposable) : LiveData<PagedList<Result>>{
        moviesDaFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config : PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePageList = LivePagedListBuilder(moviesDaFactory, config).build();

        return moviePageList
    }

    fun getNetworkState() : LiveData<NetworkState>{
        return Transformations.switchMap<MovieDataSource, NetworkState>(moviesDaFactory.movieLiveDataSource, MovieDataSource::networkState)
    }
}