package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.navigation.NavigationScreen

data class CustomMainButtonData(
    val painter: Painter,
    val text: String,
    val onClickAction: () -> Unit
)

@Composable
fun HomeMenuScreen(navController: NavController) {
    val buttonDataList = listOf(
        CustomMainButtonData(
            painterResource(id = R.drawable.company1),
            stringResource(id = R.string.main_btn_nip_search)
        ) { navController.navigate(NavigationScreen.SearchByNip.route) },
        CustomMainButtonData(
            painterResource(id = R.drawable.company2),
            stringResource(id = R.string.main_btn_pkd_search)
        ) { navController.navigate(NavigationScreen.SearchByPkd.route) },
        CustomMainButtonData(
            painterResource(id = R.drawable.company3),
            stringResource(id = R.string.main_btn_advance_search)
        ) { navController.navigate(NavigationScreen.SearchForm.route) },
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(buttonDataList) { buttonData ->
                CustomMainButton(buttonData)
            }
        }
    }
}

@Composable
fun CustomMainButton(buttonData: CustomMainButtonData) {
    Box(
        modifier = Modifier
            .clickable(onClick = buttonData.onClickAction)
            .fillMaxWidth()
            .background(Color.LightGray)
            .border(
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = buttonData.painter,
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(0.3f)
            )
            Text(text = buttonData.text)
        }
    }
}