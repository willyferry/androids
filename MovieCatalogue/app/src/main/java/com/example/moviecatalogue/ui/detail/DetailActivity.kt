package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.databinding.ContentDetailBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if(extras != null) {
            val movieId = extras.getInt(EXTRA_DETAIL, 0)
            val movieType = extras.getString(EXTRA_TYPE)
            if(movieId != null) {
                activityDetailBinding.progressBar.visibility = View.VISIBLE
                viewModel.selectedMovie(movieId)
                if(movieType == "Movies") {
                    viewModel.getMovie().observe(this, { movie ->
                        activityDetailBinding.progressBar.visibility = View.GONE
                        populateMovie(movie)
                    })
                } else {
                    viewModel.getShow().observe(this, { show ->
                        activityDetailBinding.progressBar.visibility = View.GONE
                        populateMovie(show)
                    })
                }
            }
        }
    }

    private fun populateMovie(movieEntity: MoviesEntity?) {
        detailBinding.tvItemTitle.text = movieEntity?.title
        detailBinding.tvItemDescription.text = movieEntity?.description
        detailBinding.tvItemGenre.text = movieEntity?.genre
        detailBinding.tvItemRating.text = movieEntity?.rating
        detailBinding.tvItemDuration.text = movieEntity?.duration
        detailBinding.tvItemScore.text = movieEntity?.score
        detailBinding.tvItemRelease.text = movieEntity?.release
        supportActionBar?.title = movieEntity?.title

        Glide.with(this)
                .load(movieEntity?.image)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.baseline_pending_black_24dp))
                .error(R.drawable.baseline_error_black_24dp)
                .into(detailBinding.imgPoster)

        findViewById<FloatingActionButton>(R.id.fab_share).setOnClickListener {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang")
                    .setText(resources.getString(R.string.share_text, movieEntity?.title))
                    .startChooser()
        }
    }
}