package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.databinding.ContentDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var detailBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        setSupportActionBar(activityDetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val extras = intent.extras
        if(extras != null) {
            val movieId = extras.getString(EXTRA_DETAIL)
            if (movieId != null) {
                viewModel.selectedMovie(movieId)
                val movies = viewModel.getMovie()
                populateMovie(movies)
            }
        }
    }

    private fun populateMovie(movieEntity: MoviesEntity) {
        detailBinding.tvItemTitle.text = movieEntity.title
        detailBinding.tvItemDescription.text = movieEntity.description
        detailBinding.tvItemGenre.text = movieEntity.genre
        detailBinding.tvItemRating.text = movieEntity.rating
        detailBinding.tvItemDuration.text = movieEntity.duration
        detailBinding.tvItemScore.text = movieEntity.score
        detailBinding.tvItemRelease.text = movieEntity.release
        supportActionBar?.title = movieEntity.title

        Glide.with(this)
                .load(movieEntity.image)
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
                    .setText(resources.getString(R.string.share_text, movieEntity.title))
                    .startChooser()
        }
    }
}