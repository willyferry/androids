package com.example.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding
import com.example.moviecatalogue.ui.favorite.SectionsPagerAdapter

class FavoriteActivity : AppCompatActivity() {

    private lateinit var activityFavoriteBinding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favorite List"

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityFavoriteBinding.viewPager.adapter = sectionsPagerAdapter
        activityFavoriteBinding.tabs.setupWithViewPager(activityFavoriteBinding.viewPager)

        supportActionBar?.elevation = 0f
    }
}