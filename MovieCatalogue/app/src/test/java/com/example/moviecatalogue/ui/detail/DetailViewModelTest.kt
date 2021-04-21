package com.example.moviecatalogue.ui.detail

import com.example.moviecatalogue.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyData = DataDummy.generateDummyData()[0]
    private val movieId = dummyData.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.selectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        viewModel.selectedMovie(dummyData.id)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyData.id, movieEntity.id)
        assertEquals(dummyData.title, movieEntity.title)
        assertEquals(dummyData.description, movieEntity.description)
        assertEquals(dummyData.genre, movieEntity.genre)
        assertEquals(dummyData.rating, movieEntity.rating)
        assertEquals(dummyData.duration, movieEntity.duration)
        assertEquals(dummyData.score, movieEntity.score)
        assertEquals(dummyData.release, movieEntity.release)
        assertEquals(dummyData.image, movieEntity.image)
    }
}
