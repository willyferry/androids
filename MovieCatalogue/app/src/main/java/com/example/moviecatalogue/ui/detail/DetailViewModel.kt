package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.utils.DataDummy

class DetailViewModel : ViewModel() {

    private lateinit var movieId: String

    fun selectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): MoviesEntity {
        lateinit var movie: MoviesEntity

        val moviesEntities = DataDummy.generateDummyData()

        for (movieEntity in moviesEntities) {
            if (movieEntity.id == movieId) {
                movie = movieEntity
            }
        }

        return movie
    }

}