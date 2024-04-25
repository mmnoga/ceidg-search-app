package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import pl.careaboutit.ceidgapp.CeidgScreen
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.components.CustomButton
import pl.careaboutit.ceidgapp.ui.components.CustomText
import pl.careaboutit.ceidgapp.ui.components.CustomTextField
import pl.careaboutit.ceidgapp.utils.isPkdValid
import pl.careaboutit.ceidgapp.viewmodels.CompanyViewModel

@Composable
fun SearchByPkdScreen(
    navController: NavController,
    viewModel: CompanyViewModel
) {
    var pkdValue by remember { mutableStateOf("") }
    var cityValue by remember { mutableStateOf("") }
    var isPkdValid by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height((LocalConfiguration.current.screenHeightDp / 5).dp)
        )
        CustomText(stringResource(id = R.string.text_search))
        Spacer(modifier = Modifier.height(15.dp))
        CustomTextField(
            label = stringResource(id = R.string.pkd),
            value = pkdValue,
            onValueChange = {
                pkdValue = it
                isPkdValid = isPkdValid(it)
            },
            keyboardType = KeyboardType.Text,
            isError = !isPkdValid && pkdValue.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomTextField(
            label = stringResource(id = R.string.city),
            value = cityValue,
            onValueChange = {
                cityValue = it
            },
            keyboardType = KeyboardType.Text
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomButton(
            text = stringResource(id = R.string.search_btn),
            onClick = {
                if (isPkdValid) {
                    viewModel.getCompaniesByPkd(
                        pkdValue
                            .replace(".", "")
                            .replace(" ", ""), cityValue
                    )
                    navController.navigate(CeidgScreen.SearchByPkdResult.name)
                }
            }
        )
    }
}