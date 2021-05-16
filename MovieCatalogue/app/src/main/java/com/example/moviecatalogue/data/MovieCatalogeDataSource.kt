package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.vo.Resource

interface MovieCatalogeDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getShows(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MoviesEntity>>

    fun getDetailShow(showId: Int): LiveData<Resource<MoviesEntity>>

    fun getAllMoviesFavorite(): LiveData<PagedList<MoviesEntity>>

    fun getAllShowsFavorite(): LiveData<PagedList<MoviesEntity>>

    fun setFavorite(movieOrShow: MoviesEntity, state: Boolean)
}