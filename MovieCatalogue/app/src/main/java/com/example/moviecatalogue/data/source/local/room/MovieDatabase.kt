package com.example.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context) : MovieDatabase =
            INSTANCE ?: synchronized(this) {
                Room .databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "Movies.db"
                ).build().apply {
                    INSTANCE = this
                }
            }

    }

}