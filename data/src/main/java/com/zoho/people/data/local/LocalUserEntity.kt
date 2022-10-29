package com.zoho.people.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class LocalUserEntity(
    @PrimaryKey val email: String,
    val firstName: String,
    val lastName: String,
    val profileUri: String,
    val profileThumbnailUri: String,
    val phone: String,
    val dob: String,
    val age: Int,
    val streetName: String,
    val streetNo: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val latitude: String,
    val longitude: String,
)