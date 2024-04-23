package pl.careaboutit.ceidgapp.ui.components

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick
    ) {
        Text(text = text)
    }
}