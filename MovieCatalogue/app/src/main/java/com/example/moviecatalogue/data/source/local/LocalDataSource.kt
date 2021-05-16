package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao) : LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MoviesEntity> = mMovieDao.getAllMovies()

    fun getAllShows(): DataSource.Factory<Int, MoviesEntity> = mMovieDao.getAllShows()

    fun getAllMoviesFavorite(): DataSource.Factory<Int, MoviesEntity> = mMovieDao.getAllMoviesFavorite()

    fun getAllShowsFavorite(): DataSource.Factory<Int, MoviesEntity> = mMovieDao.getAllShowsFavorite()

    fun getDetail(id: Int): LiveData<MoviesEntity> = mMovieDao.getDetail(id)

    fun insertMoviesOrShows(moviesOrShows: List<MoviesEntity>) = mMovieDao.insertMoviesOrShows(moviesOrShows)

    fun setFavorite(movie: MoviesEntity, newState: Boolean) {
        movie.favorite = newState
        mMovieDao.updateMovie(movie)
    }

}