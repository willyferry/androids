package com.example.githubusersapplication

import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private var list: ArrayList<User> = arrayListOf()

    private lateinit var userName: Array<String>
    private lateinit var userUsername: Array<String>
    private lateinit var userLocation: Array<String>
    private lateinit var userRepository: Array<String>
    private lateinit var userCompany: Array<String>
    private lateinit var userFollowers: Array<String>
    private lateinit var userFollowing: Array<String>
    private lateinit var userAvatar: TypedArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        prepare()
        addItem()
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        val usersAdapter = UsersAdapter(list, this)
        rvUsers.adapter = usersAdapter
    }

    private fun prepare() {
        userName = resources.getStringArray(R.array.name)
        userUsername = resources.getStringArray(R.array.username)
        userLocation = resources.getStringArray(R.array.location)
        userRepository = resources.getStringArray(R.array.repository)
        userCompany = resources.getStringArray(R.array.company)
        userFollowers = resources.getStringArray(R.array.followers)
        userFollowing = resources.getStringArray(R.array.following)
        userAvatar = resources.obtainTypedArray(R.array.avatar)
    }

    private fun addItem() {
        for(position in userName.indices) {
            val user = User()
            user.name = userName[position]
            user.username = userUsername[position]
            user.location = userLocation[position]
            user.repository = userRepository[position]
            user.company = userCompany[position]
            user.followers = userFollowers[position].toInt()
            user.following = userFollowing[position].toInt()
            user.avatar = userAvatar.getResourceId(position, -1)
            list.add(user)
        }
    }
}