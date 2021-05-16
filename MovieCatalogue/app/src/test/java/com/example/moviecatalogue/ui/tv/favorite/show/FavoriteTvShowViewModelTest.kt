package com.example.moviecatalogue.ui.tv.favorite.show

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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest
{

    private lateinit var viewModel: FavoriteTvShowViewModel

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
        viewModel = FavoriteTvShowViewModel(moviesCatalogueRepository)
    }

    @Test
    fun getFavoriteShows() {
        val dummyShows = pagedList
        `when`(dummyShows.size).thenReturn(10)
        val shows = MutableLiveData<PagedList<MoviesEntity>>()
        shows.value = dummyShows

        `when`(moviesCatalogueRepository.getAllShowsFavorite()).thenReturn(shows)
        val showsEntities = viewModel.getFavoriteShows().value
        verify(moviesCatalogueRepository).getAllShowsFavorite()
        Assert.assertNotNull(showsEntities)
        Assert.assertEquals(10, showsEntities?.size)

        viewModel.getFavoriteShows().observeForever(observer)
        verify(observer).onChanged(dummyShows)
    }
}