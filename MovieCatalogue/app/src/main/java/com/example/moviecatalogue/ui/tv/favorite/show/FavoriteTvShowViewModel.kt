package com.example.moviecatalogue.ui.tv.favorite.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity

class FavoriteTvShowViewModel (private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getFavoriteShows() : LiveData<PagedList<MoviesEntity>> = movieCatalogueRepository.getAllShowsFavorite()

}