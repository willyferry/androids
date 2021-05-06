package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity

class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    private var movieId: Int = 0
    private var movieType: String = "Movie"

    fun selectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun selectedType(movieType: String) {
        if(movieType == "Tv Shows") this.movieType = "Show"
    }

    fun getMovie(): LiveData<MoviesEntity> = movieCatalogueRepository.getDetailMovie(movieId)

    fun getShow(): LiveData<MoviesEntity> = movieCatalogueRepository.getDetailShow(movieId)

}