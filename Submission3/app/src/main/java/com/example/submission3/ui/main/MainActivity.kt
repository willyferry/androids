package com.example.submission3.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission3.adapter.UserAdapter
import com.example.submission3.databinding.ActivityMainBinding
import com.example.submission3.ui.favorite.FavoriteActivity
import com.example.submission3.ui.setting.SettingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.btnSearch.setOnClickListener {
            val user = binding.etSearch.text.toString()
            if(user.isEmpty()) return@setOnClickListener
            showLoading(true)
            mainViewModel.setUser(user)
        }

        binding.imgbtnSetting.setOnClickListener {
            val mIntent = Intent(this, SettingActivity::class.java)
            startActivity(mIntent)
        }
        binding.imgbtnFavorite.setOnClickListener {
            val mIntent = Intent(this@MainActivity, FavoriteActivity::class.java)
            startActivity(mIntent)
        }

        mainViewModel.getUsers().observe(this, {users ->
            if (users != null) {
                adapter.setData(users)
                showLoading(false)
            }
        })
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}