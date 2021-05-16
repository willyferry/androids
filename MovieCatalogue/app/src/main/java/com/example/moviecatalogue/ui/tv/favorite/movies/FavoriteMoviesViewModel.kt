package com.example.moviecatalogue.ui.tv.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity

class FavoriteMoviesViewModel (private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getFavoriteMovies() : LiveData<PagedList<MoviesEntity>> = movieCatalogueRepository.getAllMoviesFavorite()

}