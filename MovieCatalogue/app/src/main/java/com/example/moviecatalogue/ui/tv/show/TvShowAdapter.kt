package com.example.moviecatalogue.ui.tv.show

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.databinding.ItemsCardBinding
import com.example.moviecatalogue.ui.detail.DetailActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShow = ArrayList<MoviesEntity>()

    fun setTvShows(tvShows: List<MoviesEntity>?) {
        if (tvShows == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsCardBinding = ItemsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsCardBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    class TvShowViewHolder (private val binding: ItemsCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: MoviesEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemGenre.text = tvShow.genre

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL, tvShow.id)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                        .load(tvShow.image)
                        .apply(RequestOptions.placeholderOf(R.drawable.baseline_pending_black_24dp))
                        .error(R.drawable.baseline_error_black_24dp)
                        .into(imgPoster)
            }
        }

    }
}