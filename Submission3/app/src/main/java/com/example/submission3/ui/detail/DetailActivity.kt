package com.example.submission3.ui.detail

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission3.BuildConfig
import com.example.submission3.R
import com.example.submission3.databinding.ActivityDetailBinding
import com.example.submission3.db.DatabaseContract
import com.example.submission3.db.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.example.submission3.ui.favorite.FavoriteActivity
import com.example.submission3.ui.main.MainActivity
import com.example.submission3.ui.setting.SettingActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var isFavorite: Boolean = false
    private val apiKey: String = BuildConfig.API_KEY;
    var avatar_url: String? = null

    private lateinit var uriWithUsername: Uri

    companion object {
        private val ARG_USERNAME = "ARG_USERNAME"

        @StringRes

        private val TAB_TITLES = intArrayOf(
                R.string.following_tab,
                R.string.follower_tab
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var argUsername: String? = intent.getStringExtra(ARG_USERNAME)
        showLoading(true)
        setDetail(argUsername)

        uriWithUsername = Uri.parse("$CONTENT_URI/$argUsername")

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

        binding.imgbtnFavorite.setOnClickListener {
            val mIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(mIntent)
        }

        binding.imgbtnSetting.setOnClickListener {
            val mIntent = Intent(this, SettingActivity::class.java)
            startActivity(mIntent)
        }

        val resultQueryByUsername = contentResolver.query(uriWithUsername, null, null, null, null)

        isFavorite = resultQueryByUsername?.count!! > 0

        Log.d("resultQueryByUsername", "${resultQueryByUsername.count}")

        if(isFavorite) {
            binding.fabAdd.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            binding.fabAdd.setImageResource(R.drawable.baseline_favorite_border_24)
        }

        binding.fabAdd.setOnClickListener {
            if(isFavorite) {
                contentResolver.delete(uriWithUsername, null, null)

                binding.fabAdd.setImageResource(R.drawable.baseline_favorite_border_24)
                isFavorite = false
                Toast.makeText(this@DetailActivity, "Berhasil menghapus $argUsername dari daftar favorite", Toast.LENGTH_SHORT).show()

//                val result = userHelper.deleteByUsername(argUsername).toLong()
//                if (result > 0) {
//                    binding.fabAdd.setImageResource(R.drawable.baseline_favorite_border_24)
//                    isFavorite = false
//                    Toast.makeText(this@DetailActivity, "Berhasil menghapus $argUsername dari daftar favorite", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this@DetailActivity, "Gagal menghapus $argUsername dari daftar favorite", Toast.LENGTH_SHORT).show()
//                }
            } else {
                val values = ContentValues()
                values.put(DatabaseContract.UserColumns.USERNAME, argUsername)
                values.put(DatabaseContract.UserColumns.AVATAR_URL, avatar_url)

                contentResolver.insert(CONTENT_URI, values)
//                val result = userHelper.insert(values)
//                if (result > 0) {
//                    binding.fabAdd.setImageResource(R.drawable.baseline_favorite_24)
//                    isFavorite = true
//                    Toast.makeText(this@DetailActivity, "Berhasil menambah $argUsername ke daftar favorite", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this@DetailActivity, "Gagal menambah $argUsername ke daftar favorite", Toast.LENGTH_SHORT).show()
//                }

                binding.fabAdd.setImageResource(R.drawable.baseline_favorite_24)
                isFavorite = true
                Toast.makeText(this@DetailActivity, "Berhasil menambah $argUsername ke daftar favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setDetail(arg_username: String?) {

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
                    avatar_url = responseObject.getString("avatar_url")
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
                Toast.makeText(this@DetailActivity, "Gagal Memuat Data", Toast.LENGTH_SHORT).show()
                showLoading(false)
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