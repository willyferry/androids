package com.example.consumerapp.db.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    var id: Int? = 0,
    var username: String? = "",
    var avatar_url: String? = "",
) : Parcelable
