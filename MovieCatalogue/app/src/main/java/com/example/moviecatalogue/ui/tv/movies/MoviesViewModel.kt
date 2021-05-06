package com.example.moviecatalogue.ui.tv.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity

class MoviesViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getMovies() : LiveData<List<MoviesEntity>> = movieCatalogueRepository.getMovies()

}