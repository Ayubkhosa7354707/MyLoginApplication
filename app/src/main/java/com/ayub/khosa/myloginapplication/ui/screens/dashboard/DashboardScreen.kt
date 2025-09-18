package com.ayub.khosa.myloginapplication.ui.screens.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ayub.khosa.myloginapplication.common.TitleText

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