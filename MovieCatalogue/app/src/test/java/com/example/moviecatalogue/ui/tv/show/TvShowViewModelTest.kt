package com.example.moviecatalogue.ui.tv.show

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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var moviesCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(moviesCatalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dummyShows = DataDummy.generateDummyDataShows()
        val shows = MutableLiveData<List<MoviesEntity>>()
        shows.value = dummyShows

        `when`(moviesCatalogueRepository.getShows()).thenReturn(shows)
        val tvShowEntities = viewModel.getTvShows()
        verify(moviesCatalogueRepository).getShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities.value?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyShows)
    }
}