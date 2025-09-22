package com.ayub.khosa.myloginapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ayub.khosa.myloginapplication.api.RetrofitBuilder
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.ui.NavigationRoutes
import com.ayub.khosa.myloginapplication.ui.authenticatedGraph
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.MYPlayListScreen
import com.ayub.khosa.myloginapplication.ui.theme.MyLoginApplicationTheme
import com.ayub.khosa.myloginapplication.ui.unauthenticatedGraph
import com.ayub.khosa.myloginapplication.utils.NetworkHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyLoginApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //  MYPlayListScreen()
                      MainScreenApp()
                }
            }
        }
    }


    @Composable
    fun MainScreenApp() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainAppNavHost()
        }

    }

    @Composable
    fun MainAppNavHost(  modifier: Modifier = Modifier,
                         navController: NavHostController = rememberNavController(),) {

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = NavigationRoutes.Unauthenticated.NavigationRoute.route
        ) {
            // Unauthenticated user flow screens
            unauthenticatedGraph(navController = navController)

            // Authenticated user flow screens
            authenticatedGraph(navController = navController)
        }
    }



}