package com.example.moviecatalogue.data.source.remote.response

data class MoviesResponse (
    var id: Int,
    var title: String,
    var description: String,
    var genre: String,
    var rating: String,
    var release: String,
    var duration: String,
    var score: String,
    var type: String,
    var image: String,
)