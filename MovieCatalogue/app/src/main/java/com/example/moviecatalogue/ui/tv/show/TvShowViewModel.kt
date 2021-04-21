package com.example.moviecatalogue.ui.tv.show

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {
    private var listTvShow = ArrayList<MoviesEntity>()

    fun getTvShows(): List<MoviesEntity> {
        for (movies in DataDummy.generateDummyData()) {
            if (movies.type == "Tv Show") {
                listTvShow.add(movies)
            }
        }
        return listTvShow
    }
}