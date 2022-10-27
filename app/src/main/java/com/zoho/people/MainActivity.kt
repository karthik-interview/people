package com.zoho.people

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zoho.people.data.UserUi
import com.zoho.people.ui.home.HomeScreen

class MainActivity : ComponentActivity() {

    private val users: List<UserUi> =
        List(25) { UserUi("John Doe $it", profileUri = "https://picsum.photos/200") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(users)
        }
    }
}