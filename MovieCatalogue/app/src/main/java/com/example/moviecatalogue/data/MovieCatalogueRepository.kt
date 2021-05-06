package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MoviesResponse

class MovieCatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieCatalogeDataSource {

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null

        fun getInstance(remoteData: RemoteDataSource) : MovieCatalogueRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieCatalogueRepository(remoteData).apply { instance = this }
                }
    }

    override fun getMovies(): LiveData<List<MoviesEntity>> {
        val moviesResults = MutableLiveData<List<MoviesEntity>>()
        remoteDataSource.getAllMovies(object: RemoteDataSource.LoadMoviesCallback {

            override fun onAllMoviesReceived(moviesResponse: List<MoviesResponse>) {
                val movieList = ArrayList<MoviesEntity>()
                for(response in moviesResponse) {
                    val movie = MoviesEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.genre,
                            response.rating,
                            response.release,
                            response.duration,
                            response.score,
                            response.type,
                            response.image,
                    )
                    movieList.add(movie)
                }
                moviesResults.postValue(movieList)
            }

        })

        return moviesResults
    }

    override fun getShows(): LiveData<List<MoviesEntity>> {
        val showsResults = MutableLiveData<List<MoviesEntity>>()
        remoteDataSource.getAllShows(object: RemoteDataSource.LoadShowsCallback {

            override fun onAllShowsReceived(showsResponse: List<MoviesResponse>) {
                val movieList = ArrayList<MoviesEntity>()
                for(response in showsResponse) {
                    val show = MoviesEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.genre,
                        response.rating,
                        response.release,
                        response.duration,
                        response.score,
                        response.type,
                        response.image,
                    )
                    movieList.add(show)
                }
                showsResults.postValue(movieList)
            }

        })

        return showsResults
    }

    override fun getDetailMovie(movieId : Int): LiveData<MoviesEntity> {
        val detailMovieResult = MutableLiveData<MoviesEntity>()

        remoteDataSource.getDetailMovie(movieId, object : RemoteDataSource.LoadMovieDetailCallback {

            override fun onMovieDetailReceived(movieDetailResponse: MoviesResponse?) {
                if (movieDetailResponse != null) {
                    detailMovieResult.postValue(MoviesEntity(
                        movieDetailResponse.id,
                        movieDetailResponse.title,
                        movieDetailResponse.description,
                        movieDetailResponse.genre,
                        movieDetailResponse.rating,
                        movieDetailResponse.release,
                        movieDetailResponse.duration,
                        movieDetailResponse.score,
                        movieDetailResponse.type,
                        movieDetailResponse.image,
                    ))
                }
            }

        })

        return detailMovieResult
    }

    override fun getDetailShow(showId: Int): LiveData<MoviesEntity> {
        val detailShowResult = MutableLiveData<MoviesEntity>()

        remoteDataSource.getDetailShow(showId, object : RemoteDataSource.LoadShowDetailCallback {

            override fun onShowDetailReceived(showDetailResponse: MoviesResponse?) {
                if (showDetailResponse != null) {
                    detailShowResult.postValue(MoviesEntity(
                        showDetailResponse.id,
                        showDetailResponse.title,
                        showDetailResponse.description,
                        showDetailResponse.genre,
                        showDetailResponse.rating,
                        showDetailResponse.release,
                        showDetailResponse.duration,
                        showDetailResponse.score,
                        showDetailResponse.type,
                        showDetailResponse.image,
                    ))
                }
            }

        })

        return detailShowResult
    }

}