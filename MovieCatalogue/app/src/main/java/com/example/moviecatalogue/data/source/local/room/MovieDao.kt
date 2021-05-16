package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM moviesentities WHERE type = 'Movies'")
    fun getAllMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM moviesentities WHERE type = 'Tv Show'")
    fun getAllShows(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM moviesentities WHERE type = 'Tv Show' AND favorite = 1")
    fun getAllShowsFavorite(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM moviesentities WHERE type = 'Movies' AND favorite = 1")
    fun getAllMoviesFavorite(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM moviesentities WHERE id = :id")
    fun getDetail(id: Int): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesOrShows(moviesOrShows: List<MoviesEntity>)

    @Update
    fun updateMovie(movie: MoviesEntity)
}