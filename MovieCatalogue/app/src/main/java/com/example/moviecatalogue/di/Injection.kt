package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.room.MovieDatabase
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}