package com.example.consumerapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.consumerapp.R
import com.example.consumerapp.databinding.ItemRowUserBinding
import com.example.consumerapp.db.model.UserModel
import com.example.consumerapp.ui.detail.DetailActivity

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    var usersData = ArrayList<UserModel>()

    fun setData(items: ArrayList<UserModel>) {
        usersData.clear()
        usersData.addAll(items)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowUserBinding.bind(itemView)

        fun bind(user: UserModel) {
            binding.tvUsername.text = user.username

            Glide.with(itemView.context)
                .load(user.avatar_url)
                .apply(RequestOptions().override(40, 40))
                .into(binding.civPhoto)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("ARG_USERNAME", user.username)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return UserViewHolder(userView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(usersData[position])
    }

    override fun getItemCount(): Int {
        return usersData.size
    }
}