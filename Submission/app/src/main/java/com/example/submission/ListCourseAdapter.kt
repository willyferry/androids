package com.example.submission

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class ListCourseAdapter(private val listCourse: ArrayList<Course>, private val context: Context) : RecyclerView.Adapter<ListCourseAdapter.ListViewHolder>(){

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.item_course_title)
        var tvDetail: TextView = itemView.findViewById(R.id.item_course_detail)
        var imgThumbnail: ImageView = itemView.findViewById(R.id.item_course_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_courses, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val course = listCourse[position]

        Glide.with(holder.itemView.context)
            .load(course.thumbnail)
            .apply(RequestOptions().override(80, 80))
            .into(holder.imgThumbnail)

        holder.tvTitle.text = course.title
        holder.tvDetail.text = course.detail

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, course)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listCourse.size
    }
}