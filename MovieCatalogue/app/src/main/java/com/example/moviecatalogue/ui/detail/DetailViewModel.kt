package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.vo.Resource

class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    var id: Int = 0

    fun selectedMovie(movieId: Int) {
        this.id = movieId
    }

    fun getMovie(): LiveData<Resource<MoviesEntity>> {
        return movieCatalogueRepository.getDetailMovie(id)
    }

    fun getShow(): LiveData<Resource<MoviesEntity>> {
        return movieCatalogueRepository.getDetailShow(id)
    }

    fun setFavorite(movieOrShow: MoviesEntity) {
        val newState = !movieOrShow.favorite
        movieCatalogueRepository.setFavorite(movieOrShow, newState)
    }

}