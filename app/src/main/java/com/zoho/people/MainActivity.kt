package com.zoho.people

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zoho.people.models.presentation.UserEntity
import com.zoho.people.ui.home.HomeScreen

class MainActivity : ComponentActivity() {

    private val users: List<UserEntity> = List(25) {
        UserEntity(
            firstName = "John",
            lastName = "Doe",
            profileUri = "https://picsum.photos/200",
            email = "john.doe@somecompany.com",
            profileThumbnailUri = "https://picsum.photos/200"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(users)
        }
    }
}