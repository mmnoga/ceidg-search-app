package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.navigation.NavigationScreen
import pl.careaboutit.ceidgapp.ui.screens.components.CustomButton
import pl.careaboutit.ceidgapp.ui.screens.components.CustomText
import pl.careaboutit.ceidgapp.ui.screens.components.CustomTextField
import pl.careaboutit.ceidgapp.ui.viewmodel.SearchFormByNipViewModel
import pl.careaboutit.ceidgapp.utils.buildQueryParamsFromObject

@Composable
fun SearchFormByNipScreen(
    navController: NavHostController,
    viewModel: SearchFormByNipViewModel = viewModel()
) {
    val searchState = viewModel.searchByNipState.value

    val isNipValid = viewModel.isNipValid()

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
            value = searchState.nip,
            onValueChange = {
                viewModel.updateNip(it)
            },
            keyboardType = KeyboardType.Number,
            isError = !isNipValid && searchState.nip.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomButton(
            text = stringResource(id = R.string.search_btn),
            onClick = {
                val queryParams = buildQueryParamsFromObject(searchState)
                navController.navigate("${NavigationScreen.ListResult.route}/$queryParams")
            },
            enabled = isNipValid
        )
    }

}