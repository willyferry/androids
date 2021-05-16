package com.example.moviecatalogue.ui.tv.home.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.vo.Resource

class TvShowViewModel (private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getTvShows(): LiveData<Resource<PagedList<MoviesEntity>>> = movieCatalogueRepository.getShows()
    
}