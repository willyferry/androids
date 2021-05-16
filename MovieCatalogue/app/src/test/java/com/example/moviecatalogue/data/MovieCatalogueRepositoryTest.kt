package com.example.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.example.moviecatalogue.utils.PagedListUtil
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummy.generateDummyDataMoviesResponse()
    private val movieId = moviesResponse[0].id

    private val showsResponse = DataDummy.generateDummyDataShowsResponse()
    private val showId = showsResponse[0].id

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getMovies()

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDataMovies()))
        verify(local).getAllMovies()
        assertNotNull(moviesEntities.data)
        assertEquals(moviesResponse.size.toLong(), moviesEntities.data?.size?.toLong())
    }

    @Test
    fun getShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getAllShows()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getShows()

        val showsEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyDataShows()))
        verify(local).getAllShows()
        assertNotNull(showsEntities.data)
        assertEquals(showsResponse.size.toLong(), showsEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyEntity = MutableLiveData<MoviesEntity>()
        dummyEntity.value = DataDummy.generateDummyDataMovies()[0]
        `when`(local.getDetail(movieId)).thenReturn(dummyEntity)

        val movieEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailMovie(movieId))
        verify(local).getDetail(movieId)
        assertNotNull(movieEntity)
        assertNotNull(movieEntity.data)
        assertNotNull(movieEntity.data?.id)
        assertNotNull(movieEntity.data?.title)
        assertNotNull(movieEntity.data?.description)
        assertNotNull(movieEntity.data?.genre)
        assertNotNull(movieEntity.data?.rating)
        assertNotNull(movieEntity.data?.duration)
        assertNotNull(movieEntity.data?.score)
        assertNotNull(movieEntity.data?.release)
        assertNotNull(movieEntity.data?.image)
        assertEquals(moviesResponse[0].id, movieEntity.data?.id)
        assertEquals(moviesResponse[0].title, movieEntity.data?.title)
        assertEquals(moviesResponse[0].description, movieEntity.data?.description)
        assertEquals(moviesResponse[0].genre, movieEntity.data?.genre)
        assertEquals(moviesResponse[0].rating, movieEntity.data?.rating)
        assertEquals(moviesResponse[0].duration, movieEntity.data?.duration)
        assertEquals(moviesResponse[0].score, movieEntity.data?.score)
        assertEquals(moviesResponse[0].release, movieEntity.data?.release)
        assertEquals(moviesResponse[0].image, movieEntity.data?.image)
    }

    @Test
    fun getDetailShow() {
        val dummyEntity = MutableLiveData<MoviesEntity>()
        dummyEntity.value = DataDummy.generateDummyDataShows()[0]
        `when`(local.getDetail(showId)).thenReturn(dummyEntity)

        val showEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailShow(showId))
        verify(local).getDetail(showId)
        assertNotNull(showEntity)
        assertNotNull(showEntity.data)
        assertNotNull(showEntity.data?.id)
        assertNotNull(showEntity.data?.title)
        assertNotNull(showEntity.data?.description)
        assertNotNull(showEntity.data?.genre)
        assertNotNull(showEntity.data?.rating)
        assertNotNull(showEntity.data?.duration)
        assertNotNull(showEntity.data?.score)
        assertNotNull(showEntity.data?.release)
        assertNotNull(showEntity.data?.image)
        assertEquals(showsResponse[0].id, showEntity.data?.id)
        assertEquals(showsResponse[0].title, showEntity.data?.title)
        assertEquals(showsResponse[0].description, showEntity.data?.description)
        assertEquals(showsResponse[0].genre, showEntity.data?.genre)
        assertEquals(showsResponse[0].rating, showEntity.data?.rating)
        assertEquals(showsResponse[0].duration, showEntity.data?.duration)
        assertEquals(showsResponse[0].score, showEntity.data?.score)
        assertEquals(showsResponse[0].release, showEntity.data?.release)
        assertEquals(showsResponse[0].image, showEntity.data?.image)
    }
}