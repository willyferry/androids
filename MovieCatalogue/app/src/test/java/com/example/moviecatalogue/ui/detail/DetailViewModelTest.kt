package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.utils.DataDummy
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
    private lateinit var movieObserver: Observer<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieCatalogueRepository)
        viewModel.selectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MoviesEntity>()
        movie.value = dummyMovie

        `when`(movieCatalogueRepository.getDetailMovie(movieId)).thenReturn(movie)
        viewModel.selectedMovie(dummyMovie.id)
        val movieEntity = viewModel.getMovie().value
        verify(movieCatalogueRepository).getDetailMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity?.id)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.description, movieEntity?.description)
        assertEquals(dummyMovie.genre, movieEntity?.genre)
        assertEquals(dummyMovie.rating, movieEntity?.rating)
        assertEquals(dummyMovie.duration, movieEntity?.duration)
        assertEquals(dummyMovie.score, movieEntity?.score)
        assertEquals(dummyMovie.release, movieEntity?.release)
        assertEquals(dummyMovie.image, movieEntity?.image)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }
}
