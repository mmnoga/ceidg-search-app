package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import pl.careaboutit.ceidgapp.ui.screens.components.CustomDropdownMenu
import pl.careaboutit.ceidgapp.ui.screens.components.CustomText
import pl.careaboutit.ceidgapp.ui.screens.components.CustomTextField
import pl.careaboutit.ceidgapp.ui.viewmodel.DataModelViewModel
import pl.careaboutit.ceidgapp.ui.viewmodel.SearchFormByPkdViewModel
import pl.careaboutit.ceidgapp.utils.buildQueryParamsFromObject

@Composable
fun SearchFormByPkdScreen(
    navController: NavHostController,
    searchFormViewModel: SearchFormByPkdViewModel = viewModel(),
    dataModelViewModel: DataModelViewModel = viewModel()
) {
    val searchState = searchFormViewModel.searchByPkdState.value

    val pkdList by dataModelViewModel.getAllPkds().observeAsState(emptyList())

    var selectedIndex by remember { mutableStateOf(-1) }

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

        val codeNameList = ArrayList(pkdList.map { "${it.code} - ${it.name}" })

        CustomDropdownMenu(
            label = "PKD",
            items = codeNameList,
            selectedIndex = selectedIndex,
            onItemSelected = { index, _ ->
                selectedIndex = index
                val selectedPkd = if (index >= 0) pkdList[index].code else ""
                searchFormViewModel.updatePkd(selectedPkd)
            },
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            label = stringResource(id = R.string.city),
            value = searchState.miasto,
            onValueChange = {
                searchFormViewModel.updateMiasto(it)
            },
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomButton(
            text = stringResource(id = R.string.search_btn),
            onClick = {
                val queryParams = buildQueryParamsFromObject(searchState)
                navController.navigate("${NavigationScreen.ListResult.route}/$queryParams")
            },
            enabled = selectedIndex >= 0
        )
    }

}