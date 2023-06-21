package com.example.demologin.presentation.ui.activities


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.demologin.presentation.ui.screens.LoginScreen
import com.example.demologin.presentation.ui.screens.ModalDrawerSample
import com.example.demologin.presentation.ui.screens.RegisterScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //ModalDrawerSample()
            LoginScreen()
            //RegisterScreen()
        }
    }
}

