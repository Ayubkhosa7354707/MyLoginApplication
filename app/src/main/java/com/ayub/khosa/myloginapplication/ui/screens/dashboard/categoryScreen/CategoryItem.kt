package com.ayub.khosa.myloginapplication.ui.screens.dashboard.categoryScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.myloginapplication.common.TextExample

@OptIn(ExperimentalMaterial3Api::class)
@JvmOverloads
@Composable
fun CategoryItem(
    viewModel: CategorysViewModel,
    category_id: String,
    name: String,
    img: String, modifier: Modifier = Modifier,
) {


    LocalContext.current
    var mycategory_id = rememberSaveable { mutableStateOf(category_id) }
    var myname = rememberSaveable { mutableStateOf(name) }
    var myimg = rememberSaveable { mutableStateOf(img) }
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
                " id :" + mycategory_id.value,

                )
            TextExample(
                "Name :" +
                        myname.value
            )
        }
    }


}