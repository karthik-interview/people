package com.zoho.people.models.presentation

data class UserEntity(
    val firstName: String,
    val lastName: String,
    val email: String,
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
)