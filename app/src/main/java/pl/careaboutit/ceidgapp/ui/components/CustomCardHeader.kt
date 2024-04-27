package pl.careaboutit.ceidgapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomCardHeader(text: String) {
    Row {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.titleLarge
        )
    }
}