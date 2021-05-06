package com.example.moviecatalogue.ui.tv.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.databinding.ItemsCardBinding
import com.example.moviecatalogue.ui.detail.DetailActivity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var listMovies = ArrayList<MoviesEntity>()

    fun setMovies(movies: List<MoviesEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemsCardBinding = ItemsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemsCardBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    class MoviesViewHolder (private val binding: ItemsCardBinding) : RecyclerView.ViewHolder(binding.root) {

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
}