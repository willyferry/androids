package com.example.submissions2githubuserapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<UserModel>>()

    fun setUser(userInputData: String) {
        val dataUsers = ArrayList<UserModel>()

        val apiKey = "5671f9322292d05986a2461ffcc3cdc9e2e75d9b"
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