package com.example.littlelemonandroid

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController)  {

    val context = LocalContext.current
    val startDestination = when(context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE).getBoolean("onboarded", false))   {
        true -> "Home"
        else -> "Onboarding"
    }

    NavHost(navController = navController, startDestination = startDestination)    {
        composable(Onboarding.route)    {
            Onboarding(navController)
        }
        composable(Home.route)  {
            Home()
        }
        composable(Profile.route)   {
            Profile()
        }
    }
}