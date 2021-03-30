package com.example.submissions2githubuserapi

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissions2githubuserapi.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        private val ARG_USERNAME = "ARG_USERNAME"

        @StringRes

        private val TAB_TITLES = intArrayOf(
                R.string.following_tab,
                R.string.follower_tab
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var argUsername: String? = intent.getStringExtra(ARG_USERNAME)
        showLoading(true)
        setDetail(argUsername)

        val sectionsPagerAdapter = SectionPagerAdapter(this)
        sectionsPagerAdapter.username = argUsername;
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        binding.imgbtnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.imgbtnSetting.setOnClickListener {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }

        supportActionBar?.elevation = 0f
    }

    fun setDetail(arg_username: String?) {

        val apiKey = "5671f9322292d05986a2461ffcc3cdc9e2e75d9b"
        val url = "https://api.github.com/users/${arg_username}"

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token ${apiKey}")
        client.addHeader("User-Agent", "request")

        client.get(url, object: AsyncHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    binding.userUsername.text = responseObject.getString("login")
                    binding.userName.text = responseObject.getString("name")
                    Glide.with(this@DetailActivity)
                        .load(responseObject.getString("avatar_url"))
                        .apply(RequestOptions().override(100, 100))
                        .into(binding.userAvatar)
                    showLoading(false)
                    binding.userFollower.text = getString(R.string.follower_detail, responseObject.getString("followers"))
                    binding.userFollowing.text = getString(R.string.following_detail, responseObject.getString("following"))
                    binding.userRepositoryDetail.text = getString(R.string.repository_detail, responseObject.getString("public_repos"))
                    binding.userCompanyDetail.text = getString(R.string.company_detail, responseObject.getString("company"))
                    binding.userLocationDetail.text = getString(R.string.location_detail, responseObject.getString("location"))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.d("Exception", error.message.toString())
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