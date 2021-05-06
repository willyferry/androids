package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity

interface MovieCatalogeDataSource {

    fun getMovies(): LiveData<List<MoviesEntity>>

    fun getShows(): LiveData<List<MoviesEntity>>

    fun getDetailMovie(movieId: Int): LiveData<MoviesEntity>

    fun getDetailShow(showId: Int): LiveData<MoviesEntity>

}