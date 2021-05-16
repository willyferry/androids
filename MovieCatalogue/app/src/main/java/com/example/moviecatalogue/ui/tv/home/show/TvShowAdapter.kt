package com.example.moviecatalogue.ui.tv.home.show

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

class TvShowAdapter : PagedListAdapter<MoviesEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsCardBinding = ItemsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsCardBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    class TvShowViewHolder (private val binding: ItemsCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: MoviesEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemGenre.text = tvShow.genre

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL, tvShow.id)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, tvShow.type)
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