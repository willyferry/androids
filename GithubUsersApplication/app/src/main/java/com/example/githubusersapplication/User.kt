package com.example.githubusersapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var username: String = "",
    var name: String = "",
    var location: String = "",
    var repository: String = "",
    var company: String = "",
    var followers: Int = 0,
    var following: Int = 0,
    var avatar: Int = 0,
) : Parcelable
