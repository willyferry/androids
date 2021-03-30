package com.example.submission

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        val eCourse = intent.getParcelableExtra<Course>(ListCourseAdapter.INTENT_PARCELABLE)

        val tvCourseTitle: TextView = findViewById(R.id.tv_data_course_title)
        val tvCourseCategory: TextView = findViewById(R.id.tv_data_course_category)
        val tvCourseDetail: TextView = findViewById(R.id.tv_data_course_detail)
        val tvCourseThumbnail: ImageView = findViewById(R.id.tv_data_course_thumbnail)
        val tvCourseModul: TextView = findViewById(R.id.tv_data_course_modul)
        val tvCourseTime: TextView = findViewById(R.id.tv_data_course_time)
        val tvCourseLevel: TextView = findViewById(R.id.tv_data_course_level)
        val tvCourseRate: TextView = findViewById(R.id.tv_data_course_rate)
        val tvCourseBtnFavorite: Button = findViewById(R.id.tv_data_course_btn_favorite)

        if (eCourse != null) {
            actionBar!!.title = eCourse.title
        }

        tvCourseBtnFavorite.setOnClickListener {
            Toast.makeText(this, eCourse?.title + " berhasil ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
        }

        if (eCourse != null) {
            tvCourseTitle.text = eCourse.title
            tvCourseCategory.text = eCourse.category
            tvCourseDetail.text = eCourse.detail
            if(eCourse.rate != ""){
                tvCourseRate.text = eCourse.rate + " / 5"
                tvCourseRate.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.star, 0,0,0)
            }
            else {
                tvCourseRate.text = eCourse.rate
            }
            tvCourseModul.text = eCourse.modul + " Modul"
            tvCourseTime.text = eCourse.time + " Jam"
            tvCourseLevel.text = eCourse.level
            tvCourseThumbnail.setImageResource(eCourse.thumbnail)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}