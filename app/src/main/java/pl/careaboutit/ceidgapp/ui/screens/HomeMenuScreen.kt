package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.careaboutit.ceidgapp.CeidgScreen
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.components.CustomMainButton

@Composable
fun HomeMenuScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomMainButton(
            painterResource(id = R.drawable.company1),
            text = stringResource(id = R.string.main_btn_nip_search)
        ) { navController.navigate(CeidgScreen.SearchByNip.name) }
        Spacer(modifier = Modifier.height(15.dp))
        CustomMainButton(
            painterResource(id = R.drawable.company2),
            text = stringResource(id = R.string.main_btn_pkd_search)
        ) { navController.navigate(CeidgScreen.SearchByPkd.name) }
    }
}