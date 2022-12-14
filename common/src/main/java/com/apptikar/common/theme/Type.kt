package com.apptikar.common.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.apptikar.common.R

// Set of Material typography styles to start with

val MyCustomFont = FontFamily(
    Font(R.font.regular,FontWeight.Normal),
    Font(R.font.meduim, FontWeight.Medium),
    Font(R.font.messiribold, FontWeight.Bold),
    Font(R.font.semibold, FontWeight.SemiBold),
)



val Typography = Typography(
    defaultFontFamily = MyCustomFont,
    body1 = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),


            h3 = TextStyle(
            fontFamily = MyCustomFont,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp
),

    h4 = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )



    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)