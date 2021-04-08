package com.example.submission3.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission3.BuildConfig
import com.example.submission3.db.model.UserModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel : ViewModel() {
    private val apiKey: String = BuildConfig.API_KEY;

    val listUsers = MutableLiveData<ArrayList<UserModel>>()

    fun setUser(userInputData: String) {
        val dataUsers = ArrayList<UserModel>()

        val url = "https://api.github.com/search/users?q=${userInputData}"

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
                    val listUsersAPI = responseObject.getJSONArray("items")

                    for (i in 0 until listUsersAPI.length()) {
                        val user = listUsersAPI.getJSONObject(i)
                        val userModel = UserModel()
                        userModel.username = user.getString("login")
                        userModel.avatar_url = user.getString("avatar_url")
                        dataUsers.add(userModel)
                    }

                    listUsers.postValue(dataUsers)
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

    fun getUsers(): LiveData<ArrayList<UserModel>> {
        return listUsers
    }
}