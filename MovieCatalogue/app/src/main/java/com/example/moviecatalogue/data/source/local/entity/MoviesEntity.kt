package com.example.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviesentities")
data class MoviesEntity (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "rating")
    var rating: String,

    @ColumnInfo(name = "release")
    var release: String,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "score")
    var score: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,

    @ColumnInfo(name = "image")
    var image: String,
)