package com.example.mypreloaddata.database

import android.provider.BaseColumns

object DatabaseContract {

    var TABLE_NAME = "table_mahasiwa"

    class MahasiswaColumns: BaseColumns {
        companion object {
            const val NAMA = "nama"
            const val NIM = "nim"
        }
    }

}