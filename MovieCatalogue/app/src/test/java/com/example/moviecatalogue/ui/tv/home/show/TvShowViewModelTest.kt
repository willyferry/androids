package com.example.moviecatalogue.ui.tv.home.show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.vo.Resource
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var moviesCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(moviesCatalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dummyShows = Resource.success(pagedList)
        `when`(dummyShows.data?.size).thenReturn(10)
        val shows = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        shows.value = dummyShows

        `when`(moviesCatalogueRepository.getShows()).thenReturn(shows)
        val tvShowEntities = viewModel.getTvShows().value?.data
        Mockito.verify(moviesCatalogueRepository).getShows()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyShows)
    }
}