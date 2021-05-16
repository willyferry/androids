package com.example.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private var listMovies = DataDummy.generateDummyDataMovies()
    private var listTvShow = DataDummy.generateDummyDataShows()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(listMovies.size))
    }

    @Test
    fun loadDetail() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(listTvShow.size))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.favorite_button)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_button)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadFavoriteTvShows() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.favorite_button)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.action_favorite)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_score)).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_button)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}