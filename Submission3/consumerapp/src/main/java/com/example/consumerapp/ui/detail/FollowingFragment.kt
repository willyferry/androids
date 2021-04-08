package com.example.consumerapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.consumerapp.BuildConfig
import com.example.consumerapp.R
import com.example.consumerapp.adapter.UserAdapter
import com.example.consumerapp.db.model.UserModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class FollowingFragment : Fragment() {
    private lateinit var adapter: UserAdapter
    private val apiKey: String = BuildConfig.API_KEY;

    companion object {
        private var ARG_USERNAME = "username"

        fun newInstance(username: String?): FollowingFragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        val rvUser: RecyclerView = view.findViewById(R.id.rv_users)
        rvUser.layoutManager = LinearLayoutManager(activity)
        rvUser.adapter = adapter

        val argUsername: String? = arguments?.getString(ARG_USERNAME)
        setUserFollowing(argUsername)
    }
    private fun setUserFollowing(arg_username: String?) {
        val dataUsers = ArrayList<UserModel>()

        val url = "https://api.github.com/users/${arg_username}/following"

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
                    val responseObject = JSONArray(result)

                    for (i in 0 until responseObject.length()) {
                        val user = responseObject.getJSONObject(i)
                        val userModel = UserModel()
                        userModel.username = user.getString("login")
                        userModel.avatar_url = user.getString("avatar_url")
                        dataUsers.add(userModel)
                    }

//                     set data ke adapter
                    adapter.setData(dataUsers)
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
}