package com.example.moviecatalogue.ui.tv.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMoviesViewModelTest
{

    private lateinit var viewModel: FavoriteMoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var moviesCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteMoviesViewModel(moviesCatalogueRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(10)
        val movies = MutableLiveData<PagedList<MoviesEntity>>()
        movies.value = dummyMovies

        `when`(moviesCatalogueRepository.getAllMoviesFavorite()).thenReturn(movies)
        val moviesEntities = viewModel.getFavoriteMovies().value
        Mockito.verify(moviesCatalogueRepository).getAllMoviesFavorite()
        Assert.assertNotNull(moviesEntities)
        Assert.assertEquals(10, moviesEntities?.size)

        viewModel.getFavoriteMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }
}