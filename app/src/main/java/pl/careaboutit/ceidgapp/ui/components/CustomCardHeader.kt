package pl.careaboutit.ceidgapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CustomCardHeader(text: String) {
    Text(
        text = text.uppercase(),
        style = TextStyle(fontWeight = FontWeight.Bold)
    )
}