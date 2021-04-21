package com.example.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyData = DataDummy.generateDummyData()

    private var listMovies = ArrayList<MoviesEntity>()
    private var listTvShow = ArrayList<MoviesEntity>()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        for (movies in dummyData) {
            if (movies.type == "Movies") {
                listMovies.add(movies)
            }
        }

        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(listMovies.size))
    }

    @Test
    fun loadDetail() {
        for (movies in dummyData) {
            if (movies.type == "Movies") {
                listMovies.add(movies)
            }
            break
        }

        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(listMovies[0].title)))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(withText(listMovies[0].description)))
        onView(withId(R.id.tv_item_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_duration)).check(matches(withText(listMovies[0].duration)))
        onView(withId(R.id.tv_item_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genre)).check(matches(withText(listMovies[0].genre)))
        onView(withId(R.id.tv_item_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_rating)).check(matches(withText(listMovies[0].rating)))
        onView(withId(R.id.tv_item_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release)).check(matches(withText(listMovies[0].release)))
        onView(withId(R.id.tv_item_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score)).check(matches(withText(listMovies[0].score)))
    }

    @Test
    fun loadTvShows() {
        for (movies in dummyData) {
            if (movies.type == "Tv Show") {
                listTvShow.add(movies)
            }
        }

        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(listTvShow.size))
    }

}