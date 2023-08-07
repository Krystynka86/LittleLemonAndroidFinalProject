package com.example.littlelemonandroidfinalproject.ui.theme

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.littlelemonandroidfinalproject.R

val KarlaRegular = FontFamily(
    Font(R.font.karla_regular)
)

val MarkaziRegular = FontFamily(
    Font(R.font.markazi_text_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = MarkaziRegular,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.charcoal
    ),
    titleLarge = TextStyle(
        fontFamily = MarkaziRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = KarlaRegular,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

)

   val  h1 = TextStyle(
    fontFamily = MarkaziRegular,
    fontSize = 26.sp,
    fontWeight = FontWeight.Bold,
    color = LittleLemonColor.charcoal
)

   val h2 = TextStyle(
    fontFamily = KarlaRegular,
    color = LittleLemonColor.charcoal,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
   )

   val body1 = TextStyle(
    fontFamily = KarlaRegular,
    color = LittleLemonColor.green
   )

   val body2 = TextStyle(
    fontFamily = MarkaziRegular,
    fontWeight = FontWeight.Bold,
    color = LittleLemonColor.green
   )

   val caption = TextStyle(
    fontFamily = MarkaziRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
    )

   val button = TextStyle(
    fontFamily = KarlaRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
       letterSpacing = 0.1.em
   )

// Usage of the custom text styles in your Compose UI
@Composable
fun MyComposable() {
    // You can use the custom text styles like this:
    Text("This is body text", style = body1)
    Text("This is another body text", style = body2)
    Text("This is a display title", style = h1)
    Text("This is a subtitle", style = h2)
    Text("This is a button", style = button)
    Text("This is a cation", style = caption)
}