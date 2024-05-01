package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.careaboutit.ceidgapp.NavigationScreens
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.components.CustomButton
import pl.careaboutit.ceidgapp.ui.components.CustomText
import pl.careaboutit.ceidgapp.ui.components.CustomTextField
import pl.careaboutit.ceidgapp.utils.isNipValid
import pl.careaboutit.ceidgapp.viewmodels.CompanyViewModel

@Composable
fun SearchByNipScreen(
    navController: NavController,
    viewModel: CompanyViewModel
) {
    var nipValue by remember { mutableStateOf("") }
    var isNipValid by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height((LocalConfiguration.current.screenHeightDp / 5).dp)
        )
        CustomText(stringResource(id = R.string.text_search))
        Spacer(modifier = Modifier.height(15.dp))
        CustomTextField(
            label = stringResource(id = R.string.nip),
            value = nipValue,
            onValueChange = {
                nipValue = it
                isNipValid = isNipValid(it)
            },
            keyboardType = KeyboardType.Number,
            isError = !isNipValid && nipValue.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomButton(
            text = stringResource(id = R.string.search_btn),
            onClick = {
                if (isNipValid) {
                    viewModel.getCompanyDetails(nipValue)
                    navController.navigate(NavigationScreens.SearchByNipResult.route)
                }
            },
            enabled = isNipValid && nipValue.isNotEmpty()
        )
    }
}