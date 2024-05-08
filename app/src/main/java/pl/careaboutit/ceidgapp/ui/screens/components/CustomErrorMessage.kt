package pl.careaboutit.ceidgapp.ui.screens.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pl.careaboutit.ceidgapp.R

@Composable
fun CustomErrorMessage(errorCode: String) {
    Text(
        text = "Error Code: $errorCode",
        color = colorResource(id = R.color.red),
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
}