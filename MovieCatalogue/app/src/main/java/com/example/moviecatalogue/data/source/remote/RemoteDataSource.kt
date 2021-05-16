package com.example.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getAllMovies(): LiveData<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MoviesResponse>>>()
        handler.postDelayed({
            resultMovies.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovies
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<MoviesResponse>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MoviesResponse>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadDetailMovie(movieId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovie
    }

    fun getAllShows(): LiveData<ApiResponse<List<MoviesResponse>>> {
        EspressoIdlingResource.increment()
        val resultShows = MutableLiveData<ApiResponse<List<MoviesResponse>>>()
        handler.postDelayed({
            resultShows.value = ApiResponse.success(jsonHelper.loadShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultShows
    }

    fun getDetailShow(showId: Int): LiveData<ApiResponse<MoviesResponse>> {
        EspressoIdlingResource.increment()
        val resultShow = MutableLiveData<ApiResponse<MoviesResponse>>()
        handler.postDelayed({
            resultShow.value = ApiResponse.success(jsonHelper.loadDetailShow(showId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultShow
    }

}
