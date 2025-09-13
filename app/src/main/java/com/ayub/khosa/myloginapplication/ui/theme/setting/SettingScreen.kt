package com.ayub.khosa.myloginapplication.ui.theme.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayub.khosa.myloginapplication.SettingViewModel
import com.ayub.khosa.myloginapplication.components.CustomDefaultBtn
import com.ayub.khosa.myloginapplication.components.CustomTextField


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingScreen(viewModel: SettingViewModel, modifier: Modifier = Modifier) {


//    viewModel.onsetting_user_loginClicked("ayub.khosa@gmail.com", "ayub")
//    viewModel.onsetting_user_is_logged_inClicked()

    var input_email = rememberSaveable { mutableStateOf("") }

    val textValue by viewModel.uiState.collectAsState()

    var input_password = rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp)) {


//        Row(
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = modifier
//        ) {
//            CustomDefaultBtn(
//                btnText = "is_logged_in",
//                onClick = { viewModel.onsetting_user_is_logged_inClicked() },
//            )
//        }

        CustomTextField(
            label = "email",
            keyboardType = KeyboardType.Text,
            visualTransformation = VisualTransformation.None,
            onChange = { newText ->
                input_email.value = newText.text
            }
        )



        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = if (isPasswordVisible) {

                VisualTransformation.None

            } else {

                PasswordVisualTransformation()

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = input_password.value,
            onValueChange = { newText ->
                input_password.value = newText
            },
            label = {
                Text(text = "Password")
            },
            trailingIcon = {
                if (isPasswordVisible) {
                    IconButton(onClick = { isPasswordVisible = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { isPasswordVisible = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            },
            placeholder = { Text(text = "Type password here") },
            shape = RoundedCornerShape(percent = 0),
        )


        CustomDefaultBtn(
            btnText = "Login",
            onClick = {
                viewModel.onsetting_user_loginClicked(
                    input_email.value,
                    input_password.value
                )
            },
        )
        Spacer(modifier = Modifier.height(20.dp))



        Text(
            text = "--> " + textValue,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )


    }

}


