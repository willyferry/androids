package com.example.moviecatalogue.ui.tv.movies

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.utils.DataDummy

class MoviesViewModel : ViewModel() {

    private var listMovies = ArrayList<MoviesEntity>()

    fun getMovies() : List<MoviesEntity> {
        for (movies in DataDummy.generateDummyData()) {
            if (movies.type == "Movies") {
                listMovies.add(movies)
            }
        }
        return listMovies
    }

}