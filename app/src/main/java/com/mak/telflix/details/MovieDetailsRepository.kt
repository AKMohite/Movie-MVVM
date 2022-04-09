package com.mak.telflix.details

import androidx.lifecycle.LiveData
import com.mak.telflix.data.api.TMDBAPIInterface
import com.mak.telflix.data.repository.MovieDetailsDataSource
import com.mak.telflix.data.repository.NetworkState
import com.mak.telflix.data.vo.moviedetails.MovieDetailsVO
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService: TMDBAPIInterface){

    lateinit var movieDetailsDataSource: MovieDetailsDataSource

    fun fetchMovieDetails(compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetailsVO>{

        movieDetailsDataSource = MovieDetailsDataSource(apiService, compositeDisposable)
        movieDetailsDataSource.fetchMovieDetails(movieId)

        return movieDetailsDataSource.movieDetailsResponse
    }

    fun getDetailNetworkState() : LiveData<NetworkState>{
        return movieDetailsDataSource.newtworkState
    }
}