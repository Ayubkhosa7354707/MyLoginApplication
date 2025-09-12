package com.ayub.khosa.myloginapplication

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
import com.ayub.khosa.myloginapplication.api.RetrofitBuilder
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.ui.theme.MyLoginApplicationTheme
import com.ayub.khosa.myloginapplication.ui.theme.setting.SettingScreen
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

                    MYSettingScreen( )
                }
            }
        }
    }


    @Composable
    fun MYSettingScreen(modifier: Modifier = Modifier) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val context = LocalContext.current
            val networkHelper: NetworkHelper = NetworkHelper(context.applicationContext)
            val repository: MainActivityRepository by lazy {
                val apiService = RetrofitBuilder.provideRestApiService(
                    RetrofitBuilder.getRetrofit(
                        RetrofitBuilder.provideOkHttpClient(RetrofitBuilder.providesLoggingInterceptor()),
                        RetrofitBuilder.providesBaseUrl()
                    )
                )
                MainActivityRepository(context.applicationContext, apiService)


            }

            val viewModel: SettingViewModel = SettingViewModel(repository, networkHelper)
            SettingScreen(viewModel)

        }


    }

}