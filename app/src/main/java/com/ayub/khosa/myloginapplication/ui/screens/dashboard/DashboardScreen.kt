package com.ayub.khosa.myloginapplication.ui.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ayub.khosa.myloginapplication.api.RetrofitBuilder
import com.ayub.khosa.myloginapplication.common.TitleText
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.productsScreen.PlaylistScreen
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.productsScreen.ProductsViewModel
import com.ayub.khosa.myloginapplication.ui.theme.MyLoginApplicationTheme
import com.ayub.khosa.myloginapplication.utils.NetworkHelper

@Composable
fun DashboardScreen(viewModel: DashboardViewModel, email_id: String, onNavigateBack: () -> Boolean) {
        TitleText(Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp), "WellCome Screen")
        Column(modifier = Modifier.padding(top = 80.dp, start = 10.dp, end = 10.dp)) {



        Text(
            text = "welcome   $email_id!",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier.clickable {
                viewModel.user_logout("$email_id")
                onNavigateBack.invoke()

            }
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

            MYPlayListScreen()

    }





}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(
        viewModel(), "a@a",
        onNavigateBack = {true},
    )
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun MYPlayListScreen(modifier: Modifier = Modifier) {
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
        val viewModel: ProductsViewModel = ProductsViewModel(repository, networkHelper)
        PlaylistScreen(viewModel)
    }
}


@Preview(showBackground = true, name = "")
@Composable
fun MYPlayListScreenPreview() {

    MyLoginApplicationTheme {
        MYPlayListScreen(modifier = Modifier.fillMaxSize())
    }
}
