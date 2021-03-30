package com.example.submission

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCourses: RecyclerView
    private var list: ArrayList<Course> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Dicoding Course"

        rvCourses = findViewById(R.id.rv_courses)
        rvCourses.setHasFixedSize(true)

        list.addAll(CoursesData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvCourses.layoutManager = LinearLayoutManager(this)
        val listCourseAdapter = ListCourseAdapter(list, this)
        rvCourses.adapter = listCourseAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
        startActivity(moveIntent)
        return super.onOptionsItemSelected(item)
    }

}