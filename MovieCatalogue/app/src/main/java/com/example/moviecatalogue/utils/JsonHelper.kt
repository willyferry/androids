package com.example.moviecatalogue.utils

import android.content.Context
import com.example.moviecatalogue.data.source.remote.response.MoviesResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private val base_image_url: String = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2"

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MoviesResponse> {
        val list = ArrayList<MoviesResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("MoviesResponse.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getInt("id")
                val title = movie.getString("title")
                val description = movie.getString("description")
                val genre = movie.getString("genre")
                val rating = movie.getString("rating")
                val release = movie.getString("release")
                val duration = movie.getString("duration")
                val score = movie.getString("score")
                val type = movie.getString("type")
                val poster_path = movie.getString("poster_path")

                val movieResponse = MoviesResponse(
                    id,
                    title,
                    description,
                    genre,
                    rating,
                    release,
                    duration,
                    score,
                    type,
                    "$base_image_url$poster_path"
                )
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadDetailMovie(movieId: Int): MoviesResponse? {
        val fileName = String.format("Movie_%s.json", movieId)
        var movieResponse: MoviesResponse? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)


                val id = responseObject.getInt("id")
                val title = responseObject.getString("title")
                val description = responseObject.getString("description")
                val genre = responseObject.getString("genre")
                val rating = responseObject.getString("rating")
                val release = responseObject.getString("release")
                val duration = responseObject.getString("duration")
                val score = responseObject.getString("score")
                val type = responseObject.getString("type")
                val poster_path = responseObject.getString("poster_path")


                val movie = MoviesResponse(
                    id,
                    title,
                    description,
                    genre,
                    rating,
                    release,
                    duration,
                    score,
                    type,
                    "$base_image_url$poster_path"
                )
                movieResponse = movie
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movieResponse
    }

    fun loadShows(): List<MoviesResponse> {
        val list = ArrayList<MoviesResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("ShowsResponse.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val show = listArray.getJSONObject(i)

                val id = show.getInt("id")
                val title = show.getString("title")
                val description = show.getString("description")
                val genre = show.getString("genre")
                val rating = show.getString("rating")
                val release = show.getString("release")
                val duration = show.getString("duration")
                val score = show.getString("score")
                val type = show.getString("type")
                val poster_path = show.getString("poster_path")

                val showResponse = MoviesResponse(
                    id,
                    title,
                    description,
                    genre,
                    rating,
                    release,
                    duration,
                    score,
                    type,
                    "$base_image_url$poster_path"
                )

                list.add(showResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadDetailShow(showId: Int): MoviesResponse? {
        val fileName = String.format("Show_%s.json", showId)
        var showResponse: MoviesResponse? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)
                val id = responseObject.getInt("id")
                val title = responseObject.getString("title")
                val description = responseObject.getString("description")
                val genre = responseObject.getString("genre")
                val rating = responseObject.getString("rating")
                val release = responseObject.getString("release")
                val duration = responseObject.getString("duration")
                val score = responseObject.getString("score")
                val type = responseObject.getString("type")
                val poster_path = responseObject.getString("poster_path")


                val show = MoviesResponse(
                    id,
                    title,
                    description,
                    genre,
                    rating,
                    release,
                    duration,
                    score,
                    type,
                    "$base_image_url$poster_path"
                )
                showResponse = show
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return showResponse
    }

}