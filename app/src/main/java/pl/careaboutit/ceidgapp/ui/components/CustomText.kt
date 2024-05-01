package pl.careaboutit.ceidgapp.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pl.careaboutit.ceidgapp.R

@Composable
fun CustomText(text: String) {
    Text(
        text = text,
        color = colorResource(id = R.color.teal_700),
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.displaySmall
    )
}