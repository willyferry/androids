package com.example.moviecatalogue.ui.tv.show

import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShowEntities = viewModel.getTvShows()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(10, tvShowEntities.size)
    }
}