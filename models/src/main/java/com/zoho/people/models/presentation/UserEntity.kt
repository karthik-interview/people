package com.zoho.people.models.presentation

data class UserEntity(
    val firstName: String,
    val lastName: String,
    val email: String,
    val profileUri: String,
    val profileThumbnailUri: String,
)