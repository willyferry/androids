package com.example.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote)

    private val moviesResponse = DataDummy.generateDummyDataMoviesResponse()
    private val movieId = moviesResponse[0].id

    private val showsResponse = DataDummy.generateDummyDataShowsResponse()
    private val showId = showsResponse[0].id

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(moviesResponse)
            null
        }.`when`(remote).getAllMovies(any())

        val moviesEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(moviesEntities)
        assertEquals(moviesResponse.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadShowsCallback)
                .onAllShowsReceived(showsResponse)
            null
        }.`when`(remote).getAllShows(any())

        val showsEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getShows())
        verify(remote).getAllShows(any())
        assertNotNull(showsEntities)
        assertEquals(showsResponse.size.toLong(), showsEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                .onMovieDetailReceived(moviesResponse[0])
            null
        }.`when`(remote).getDetailMovie(eq(movieId), any())

        val movieEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailMovie(movieId))
        verify(remote).getDetailMovie(eq(movieId), any())
        assertNotNull(movieEntity)
        assertEquals(moviesResponse[0].id, movieEntity.id)
        assertEquals(moviesResponse[0].title, movieEntity.title)
        assertEquals(moviesResponse[0].description, movieEntity.description)
        assertEquals(moviesResponse[0].genre, movieEntity.genre)
        assertEquals(moviesResponse[0].rating, movieEntity.rating)
        assertEquals(moviesResponse[0].duration, movieEntity.duration)
        assertEquals(moviesResponse[0].score, movieEntity.score)
        assertEquals(moviesResponse[0].release, movieEntity.release)
        assertEquals(moviesResponse[0].image, movieEntity.image)
    }

    @Test
    fun getDetailShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadShowDetailCallback)
                .onShowDetailReceived(showsResponse[0])
            null
        }.`when`(remote).getDetailShow(eq(showId), any())

        val showEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailShow(showId))
        verify(remote).getDetailShow(eq(showId), any())
        assertNotNull(showEntity)
        assertEquals(showsResponse[0].id, showEntity.id)
        assertEquals(showsResponse[0].title, showEntity.title)
        assertEquals(showsResponse[0].description, showEntity.description)
        assertEquals(showsResponse[0].genre, showEntity.genre)
        assertEquals(showsResponse[0].rating, showEntity.rating)
        assertEquals(showsResponse[0].duration, showEntity.duration)
        assertEquals(showsResponse[0].score, showEntity.score)
        assertEquals(showsResponse[0].release, showEntity.release)
        assertEquals(showsResponse[0].image, showEntity.image)
    }
}