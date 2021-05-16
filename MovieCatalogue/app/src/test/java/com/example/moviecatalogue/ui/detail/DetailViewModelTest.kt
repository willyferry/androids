package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyDataMovies()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MoviesEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieCatalogueRepository)
        viewModel.selectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val dummyMovieEntity = Resource.success(dummyMovie)
        val movie = MutableLiveData<Resource<MoviesEntity>>()
        movie.value = dummyMovieEntity

        `when`(movieCatalogueRepository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.selectedMovie(dummyMovie.id)
        val movieEntity = viewModel.getMovie().value
        verify(movieCatalogueRepository).getDetailMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity?.data?.id)
        assertEquals(dummyMovie.title, movieEntity?.data?.title)
        assertEquals(dummyMovie.description, movieEntity?.data?.description)
        assertEquals(dummyMovie.genre, movieEntity?.data?.genre)
        assertEquals(dummyMovie.rating, movieEntity?.data?.rating)
        assertEquals(dummyMovie.duration, movieEntity?.data?.duration)
        assertEquals(dummyMovie.score, movieEntity?.data?.score)
        assertEquals(dummyMovie.release, movieEntity?.data?.release)
        assertEquals(dummyMovie.image, movieEntity?.data?.image)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovieEntity)
    }
}
