package com.example.moviecatalogue.ui.tv.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity

class TvShowViewModel (private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<MoviesEntity>> = movieCatalogueRepository.getShows()
    
}