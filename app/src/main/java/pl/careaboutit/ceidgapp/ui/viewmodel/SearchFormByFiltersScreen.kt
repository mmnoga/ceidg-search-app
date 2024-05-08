package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import pl.careaboutit.ceidgapp.utils.buildQueryParamsFromObject

@Composable
fun SearchFormByFiltersScreen(
    viewModel: SearchFormByFiltersViewModel = viewModel(),
    navController: NavHostController
) {
    val searchState = viewModel.searchState.value

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchState.nip ?: "",
            label = { Text(text = "NIP") },
            onValueChange = { newNip ->
                viewModel.updateNip(newNip)
            },
            maxLines = 1
        )
        OutlinedTextField(
            value = searchState.regon ?: "",
            label = { Text(text = "REGON") },
            onValueChange = { newRegon ->
                viewModel.updateRegon(newRegon)
            },
            maxLines = 1
        )
        OutlinedTextField(
            value = searchState.pkd ?: "",
            label = { Text(text = "PKD") },
            onValueChange = { newPkd ->
                viewModel.updatePkd(newPkd)
            },
            maxLines = 1
        )
        OutlinedTextField(
            value = searchState.ulica ?: "",
            label = { Text(text = "Ulica") },
            onValueChange = { newUlica ->
                viewModel.updateUlica(newUlica)
            },
            maxLines = 1
        )
        OutlinedTextField(
            value = searchState.miasto ?: "",
            label = { Text(text = "Miasto") },
            onValueChange = { newMiasto ->
                viewModel.updateMiasto(newMiasto)
            },
            maxLines = 1
        )
        Button(onClick = {
            val queryParams = buildQueryParamsFromObject(searchState)

            // navController.navigate("${Routes.listResult}/$queryParams")
        }) {
            Text(text = "Szukaj")
        }
    }

}
