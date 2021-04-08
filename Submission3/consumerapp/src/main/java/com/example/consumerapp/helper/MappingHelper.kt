package com.example.consumerapp.helper

import android.database.Cursor
import com.example.consumerapp.db.DatabaseContract
import com.example.consumerapp.db.model.UserModel

object MappingHelper {

    fun mapCursorToArrayList(userCursor: Cursor?) : ArrayList<UserModel> {
        val userList = ArrayList<UserModel>()

        userCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns._ID))
                val username = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.USERNAME))
                val avatar = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.AVATAR_URL))
                userList.add(UserModel(id, username, avatar))
            }
        }

        return userList
    }

    fun mapCursorToObject(userCursor: Cursor?) : UserModel {
        var user = UserModel()
        userCursor?.apply {
            moveToFirst()
            val id = getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns._ID))
            val username = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.USERNAME))
            val avatar = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.AVATAR_URL))
            user = UserModel(id, username, avatar)
        }

        return user
    }
}