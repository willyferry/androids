package com.example.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.di.Injection
import com.example.moviecatalogue.ui.detail.DetailViewModel
import com.example.moviecatalogue.ui.tv.favorite.movies.FavoriteMoviesViewModel
import com.example.moviecatalogue.ui.tv.favorite.show.FavoriteTvShowViewModel
import com.example.moviecatalogue.ui.tv.home.movies.MoviesViewModel
import com.example.moviecatalogue.ui.tv.home.show.TvShowViewModel

class ViewModelFactory private constructor(private val mMovieCatalogueRepository: MovieCatalogueRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                        instance = this
                    }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java) -> {
                return FavoriteMoviesViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                return FavoriteTvShowViewModel(mMovieCatalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}