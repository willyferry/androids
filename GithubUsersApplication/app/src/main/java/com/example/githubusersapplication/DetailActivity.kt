package com.example.githubusersapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var tvName: TextView = findViewById(R.id.user_name)
        var tvUsername: TextView = findViewById(R.id.user_username)
        var tvLocation: TextView = findViewById(R.id.user_location)
        var tvRepository: TextView = findViewById(R.id.user_repository)
        var tvCompany: TextView = findViewById(R.id.user_company)
        var tvFollowers: TextView = findViewById(R.id.user_followers)
        var tvFollowing: TextView = findViewById(R.id.user_following)
        var imgAvatar: ImageView = findViewById(R.id.user_avatar)
        var btnShare: Button = findViewById(R.id.btn_share)

        val aUser = intent.getParcelableExtra<User>(UsersAdapter.INTENT_PARCELABLE)

        if(aUser != null) {
            actionBar!!.title = aUser.name

            tvName.text = aUser.name
            Glide.with(this)
                .load(aUser.avatar)
                .apply(RequestOptions().override(200, 200))
                .into(imgAvatar)
            tvUsername.text = "@" + aUser.username
            tvLocation.text = "Location: " + aUser.location
            tvRepository.text = "Repository: " + aUser.repository
            tvCompany.text = "Company: " + aUser.company
            tvFollowers.text = "Followers: " + aUser.followers.toString()
            tvFollowing.text = "Following: " + aUser.following.toString()

            btnShare.setOnClickListener {
                Toast.makeText(this, "Berhasil share profile " + aUser.name , Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}