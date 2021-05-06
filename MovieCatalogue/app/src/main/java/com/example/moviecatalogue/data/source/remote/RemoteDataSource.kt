package com.example.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import com.example.moviecatalogue.data.source.remote.response.MoviesResponse
import com.example.moviecatalogue.utils.EspressoIdlingResource
import com.example.moviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper).apply { instance = this }
                }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
    EspressoIdlingResource.increment()
       handler.postDelayed({
           callback.onAllMoviesReceived(jsonHelper.loadMovies())
           EspressoIdlingResource.decrement()
       }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getDetailMovie(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onMovieDetailReceived(jsonHelper.loadDetailMovie(movieId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllShows(callback: LoadShowsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllShowsReceived(jsonHelper.loadShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getDetailShow(showId: Int, callback: LoadShowDetailCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onShowDetailReceived(jsonHelper.loadDetailShow(showId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesResponse: List<MoviesResponse>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: MoviesResponse?)
    }

    interface LoadShowsCallback {
        fun onAllShowsReceived(showsResponse: List<MoviesResponse>)
    }

    interface LoadShowDetailCallback {
        fun onShowDetailReceived(showDetailResponse: MoviesResponse?)
    }

}
