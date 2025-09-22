package com.ayub.khosa.myloginapplication.ui.screens.dashboard.productsScreen

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.myloginapplication.api.RetrofitBuilder
import com.ayub.khosa.myloginapplication.common.CustomTextField
import com.ayub.khosa.myloginapplication.common.Loading
import com.ayub.khosa.myloginapplication.components.CustomDefaultBtn
import com.ayub.khosa.myloginapplication.model.PRODUCT
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.ui.theme.MyLoginApplicationTheme
import com.ayub.khosa.myloginapplication.utils.NetworkHelper


@Composable
fun PlaylistScreen(
    viewModel: ProductsViewModel,
    modifier: Modifier = Modifier
) {
    var myinputdata = rememberSaveable { mutableStateOf("") }
    var mydatalist = rememberMutableStateListOf<PRODUCT>()
    if (mydatalist.size == 0) {
        viewModel.onClickCallgetAllProducts()
        viewModel.getproductsItems().forEach { it ->
            LaunchedEffect(Unit) {
                mydatalist.add(it)
            }
        }
    }


    Column(modifier = modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp)) {


        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
        ) {
            CustomDefaultBtn(
                btnText = "Products",
                onClick = { viewModel.onClickCallgetAllProducts() },
            )

            CustomDefaultBtn(
                btnText = "Products_DB",
                onClick = { viewModel.onClickCallgetProducts_DB() },
            )


            CustomDefaultBtn(
                btnText = "Error",
                onClick = { viewModel.seterrorMessage(myinputdata.value) })


        }

        DoneButton(viewModel.geterrorMessage())

        Text(text = "You entered: ${myinputdata.value}")


        CustomTextField(
            label = "Name",
            keyboardType = KeyboardType.Text,
            visualTransformation = VisualTransformation.None,
            onChange = { newText ->
                myinputdata.value = newText.text
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Error --> " + viewModel.geterrorMessage(),
            color = Color.White,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .background(Color(0xFFAED581))
                .padding(4.dp)
        )
        Text(
            "Is busy --> " + viewModel.get_is_busy(),
            color = Color.Red,
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier
                .background(Color(0xFF9CCC65))
                .padding(4.dp)
        )
        Text(
            "Data --> " + mydatalist.size,
            color = Color.Yellow,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .background(Color(0xFF8BC34A))
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (!viewModel.get_is_busy()) {


            LazyColumn(
                modifier = Modifier.align(Alignment.Start)
            ) {
                items(
                    items = mydatalist,
                    key = { product -> product.product_id }

                ) { product ->




                    ProductItem(
                        product_id = product.product_id,
                        name = product.name,
                        price = product.price,
                        img = product.img,
                        category = product.category,
                        description = product.description,
                        modifier = modifier


                    )
                }

            }
        } else {
            Loading()

        }


    }


}

@Composable
fun <T : Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(saver = snapshotStateListSaver()) {
        elements.toList().toMutableStateList()
    }
}

private fun <T : Any> snapshotStateListSaver() = listSaver<SnapshotStateList<T>, T>(
    save = { stateList -> stateList.toList() },
    restore = { it.toMutableStateList() },
)


@Composable
fun DoneButton(msg: String) {
    val context = LocalContext.current
    Button(shape = RectangleShape, onClick = { showToast(context, "Button clicked " + msg) }) {
        Text(
            text = "Done",
            color = Color.Yellow,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .background(Color(0xFF8BC34A))
                .padding(4.dp)
        )
    }
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, name = "")
@Composable
fun MYPlayListScreenPreview() {

    MyLoginApplicationTheme {

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