package com.ayub.khosa.myloginapplication.ui.screens.dashboard.productsScreen


import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.myloginapplication.common.TextExample
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.stripe.MyStripeScreen
import com.ayub.khosa.myloginapplication.ui.theme.MyLoginApplicationTheme
import com.ayub.khosa.myloginapplication.ui.theme.Purple80
import com.ayub.khosa.myloginapplication.ui.theme.PurpleGrey40

@OptIn(ExperimentalMaterial3Api::class)
@JvmOverloads
@Composable
fun ProductItem(
    product_id: String,
    name: String,
    price: String,
    img: String,
    category: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var myproduct_id = rememberSaveable { mutableStateOf(product_id) }
    var myname = rememberSaveable { mutableStateOf(name) }
    var myprice = rememberSaveable { mutableStateOf(price) }
    var myimg = rememberSaveable { mutableStateOf(img) }
    var mycategory = rememberSaveable { mutableStateOf(category) }

    var mydescription = rememberSaveable { mutableStateOf(description) }


    var show_stripe = rememberSaveable { mutableStateOf(false) }









        Row(
            modifier = Modifier
                .border(1.dp, Color.Black),
            verticalAlignment = Alignment.CenterVertically,


            ) {



            Image(
                painter = rememberAsyncImagePainter(myimg.value),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(2.dp)
                    .border(2.dp, Color.Black)
            )
            Column(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                TextExample(
                    "Product id :" + myproduct_id.value ,

                    )
                TextExample(
                    "Name :" +
                            myname.value
                )
//                TextExample(
//                    "Price :" +
//                            myprice.value + "PKR"
//                )


                if(!show_stripe.value){
                Text(
                    modifier = Modifier
                        .clickable {
                            show_stripe.value=true
                        }
                        .padding(10.dp)
                        .fillMaxWidth(),
                    text = myprice.value + "PKR",
                    color = Color.Blue, fontSize = 18.sp,
                )}else{
                    MyStripeScreen(myprice.value)
                }

                TextExample(
                    "Category :" +
                            mycategory.value
                )
                TextExample(
                    "Description :" +
                            mydescription.value
                )

            }





    }
}




@Preview(showBackground = true, name = "")
@Composable
fun ProductItemPreview() {

    MyLoginApplicationTheme {
        val context = LocalContext.current
        ProductItem(
            "1",
            "name",
            "10",
            "img",
            "category",
            "description",
            modifier = Modifier.fillMaxSize()
        )
    }
}


