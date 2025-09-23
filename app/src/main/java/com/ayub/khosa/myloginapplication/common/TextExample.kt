package com.ayub.khosa.myloginapplication.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayub.khosa.myloginapplication.ui.theme.MyCustomFont

// below is the Composable function
// which we have created for our Text.
@Composable
fun TextExample(data_string: String) {
    Column(
        // we are using column to align
        // our textview to center of the screen.

        modifier = Modifier
            .wrapContentSize(),

        // below line is used for specifying
        // horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,

        // below line is used for specifying
        // vertical arrangement.
        verticalArrangement = Arrangement.Center
    ) {
        // Text Composable
        Text(
            // below line is for displaying
            // text in our text.
            text = data_string,

            // modifier is use to give
            // padding to our text.
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),

            // color is used to provide white
            // color to our text.
            color = Color.Black,

            // font size to change the
            // size of our text.
            fontSize = 20.sp,

            // font style is use to change style
            // of our text to italic and normal
            fontStyle = FontStyle.Italic,
            // font weight to bold such as light bold,
            // extra bold to our text.
            fontWeight = FontWeight.Bold,

            // font family is use to change
            // our font family to SansSerif.
            fontFamily = MyCustomFont,

            // letter spacing is use to
            // provide between letters.
            //   letterSpacing = 1.sp,

            // Decorations applied to the text (Underline)
            //   textDecoration = TextDecoration.None,

            // textAlign to align our text view
            // to center position.
            //   textAlign = TextAlign.Center,

            // The height of each line of text (24sp).
            //    lineHeight = 1.sp,

            // Used to handle overflowed text (Ellipsis).
            //    overflow = TextOverflow.Ellipsis,

            // Whether the text should wrap if it exceeds the width of its container (true).
            //  softWrap = true,

//            // The maximum number of lines for the text (2).
//            maxLines = 2,
//
//            // The minimum number of lines for the text (1).
//            minLines = 1,

//            // A callback that is invoked when the text is laid out, here used to print the line count.
//            onTextLayout = { textLayoutResult: TextLayoutResult ->
//                // Example usage of onTextLayout callback
//                val lineCount = textLayoutResult.lineCount
//                println("Line Count: $lineCount")
//            },

            // below line is used to add
            // style to our text view.
//            style = TextStyle(
//
//                // background is use to specify background
//                // color to our text view.
//                background = Color.Green,
//
//                // shadow to make our
//                // text view elevated.
//                shadow = Shadow(color = Color.Gray, blurRadius = 40f)
//            )
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        // we are passing our composable
        // function to display its preview.
        TextExample("Product Itemssdsdsd dfasfsg fgsdfgdfg fagsfgsd sdafsfdf asfsdfadf asfd")
    }
}