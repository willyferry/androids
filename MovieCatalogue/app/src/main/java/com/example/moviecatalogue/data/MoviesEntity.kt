package com.example.moviecatalogue.data

data class MoviesEntity (
    var id: String,
    var title: String,
    var description: String,
    var genre: String,
    var rating: String,
    var release: String,
    var duration: String,
    var score: String,
    var type: String,
    var image: Int,
)