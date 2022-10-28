package com.zoho.people.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class LocalUserEntity(
    @PrimaryKey val email: String,
    val first: String,
    val last: String,
    val profileUri: String,
    val profileThumbnailUri: String,
)