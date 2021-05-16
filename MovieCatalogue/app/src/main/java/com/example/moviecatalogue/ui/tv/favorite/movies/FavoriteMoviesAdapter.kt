package com.example.moviecatalogue.ui.tv.favorite.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.databinding.ItemsCardBinding
import com.example.moviecatalogue.ui.detail.DetailActivity

class FavoriteMoviesAdapter : PagedListAdapter<MoviesEntity, FavoriteMoviesAdapter.FavoriteMoviesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }


    class FavoriteMoviesViewHolder (private val binding: ItemsCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MoviesEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemGenre.text = movie.genre

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL, movie.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, movie.type)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(movie.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.baseline_pending_black_24dp))
                    .error(R.drawable.baseline_error_black_24dp)
                    .into(imgPoster)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoviesViewHolder {
        val itemsCardBinding = ItemsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMoviesViewHolder(itemsCardBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

}