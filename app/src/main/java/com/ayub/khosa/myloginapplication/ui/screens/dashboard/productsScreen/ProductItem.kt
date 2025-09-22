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
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.myloginapplication.common.TextExample
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






    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.clickable {
            showToast(context, myname.value + " clicked ")
            // AlertDialog.Builder(context).setMessage("test").show()

            val builder = AlertDialog.Builder(context)
            builder.setTitle(".."+myname.value+".."+myprice.value)
            builder.setMessage("Confurm you want to buy  "+myname.value)
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(context,
                    "yes I like it.."+myname.value+".."+myprice.value , Toast.LENGTH_SHORT).show()



            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(context,
                    "No it is costly .."+myname.value+".."+myprice.value, Toast.LENGTH_SHORT).show()
            }

//            builder.setNeutralButton("Maybe") { dialog, which ->
//                Toast.makeText(context,
//                    "Maybe", Toast.LENGTH_SHORT).show()
//            }
            builder.show()
        }
    ) {
//          LoadingDialogView()

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
                TextExample(
                    "Price :" +
                            myprice.value + "PKR"
                )
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


@Composable
fun LoadingDialogView( ) {


    var openDialog = true

    if (openDialog) {


        Dialog(onDismissRequest = { openDialog = false }) {


            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Purple80),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                androidx.compose.material3.TextButton(onClick = {
                    openDialog = false
                }) {

                    androidx.compose.material3.Text(
                        "Not Now",
                        fontWeight = FontWeight.Bold,
                        color = PurpleGrey40,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                androidx.compose.material3.TextButton(onClick = {
                    openDialog = false
                }) {
                    androidx.compose.material3.Text(
                        "Allow",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )


                }
            }
        }
    }
}