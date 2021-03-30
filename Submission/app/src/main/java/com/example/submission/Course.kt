package com.example.submission

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    var title: String = "",
    var category: String = "",
    var detail: String = "",
    var thumbnail: Int = 0,
    var modul: String = "",
    var time: String = "",
    var level: String = "",
    var rate: String = "",
) : Parcelable
