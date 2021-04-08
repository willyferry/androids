package com.example.submission3.ui.favorite

import android.content.Intent
import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission3.adapter.UserAdapter
import com.example.submission3.databinding.ActivityFavoriteBinding
import com.example.submission3.db.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.example.submission3.db.model.UserModel
import com.example.submission3.helper.MappingHelper
import com.example.submission3.ui.main.MainActivity
import com.example.submission3.ui.setting.SettingActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.setHasFixedSize(true)
        adapter = UserAdapter()
        binding.rvUsers.adapter = adapter

        binding.imgbtnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.imgbtnSetting.setOnClickListener {
            val mIntent = Intent(this, SettingActivity::class.java)
            startActivity(mIntent)
        }

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler) {
            override fun onChange(selfChange: Boolean) {
                loadUsersAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)

        if(savedInstanceState == null) {
            loadUsersAsync()
        } else {
            savedInstanceState.getParcelableArrayList<UserModel>(EXTRA_STATE)?.also { adapter.setData(it) }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.usersData)
    }

    private fun loadUsersAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            binding.progressBar.visibility = View.VISIBLE
            val deferredUsers = async(Dispatchers.IO) {
                // CONTENT_URI = content://com.example.submission3/user
                val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }
            val users = deferredUsers.await()
            binding.progressBar.visibility = View.INVISIBLE
            if (users.size > 0) {
                adapter.setData(users)
                binding.detailText.visibility = View.INVISIBLE
            } else {
                adapter.setData(ArrayList())
                binding.detailText.visibility = View.VISIBLE
                Toast.makeText(this@FavoriteActivity, "Tidak ada data saat ini", Toast.LENGTH_SHORT).show()
            }
        }
    }
}