package pl.careaboutit.ceidgapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.navigation.NavigationScreen
import pl.careaboutit.ceidgapp.ui.screens.components.CustomButton
import pl.careaboutit.ceidgapp.ui.screens.components.CustomDatePicker
import pl.careaboutit.ceidgapp.ui.screens.components.CustomDropdownMenu
import pl.careaboutit.ceidgapp.ui.screens.components.CustomText
import pl.careaboutit.ceidgapp.ui.screens.components.CustomTextField
import pl.careaboutit.ceidgapp.ui.viewmodel.AdvancedSearchViewModel
import pl.careaboutit.ceidgapp.utils.Status
import pl.careaboutit.ceidgapp.utils.Voivodeship
import pl.careaboutit.ceidgapp.utils.buildQueryParamsFromObject
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdvancedSearchScreen(
    navController: NavHostController,
    viewModel: AdvancedSearchViewModel = viewModel()
) {
    val state = viewModel.advancedSearchState.value

    var includeActive by rememberSaveable { mutableStateOf(true) }
    var includeArchived by rememberSaveable { mutableStateOf(false) }
    var includeRequiringVerification by rememberSaveable { mutableStateOf(false) }

    var statusList by remember { mutableStateOf<List<Status>>(emptyList()) }
    DisposableEffect(includeActive, includeArchived, includeRequiringVerification) {
        statusList = mutableListOf<Status>().apply {
            if (includeActive) add(Status.AKTYWNY)
            if (includeArchived) add(Status.WYKRESLONY)
            if (includeRequiringVerification) add(Status.OCZEKUJE_NA_ROZPOCZECIE_DZIALANOSCI)
        }
        onDispose { }
    }

    LaunchedEffect(statusList) {
        viewModel.updateStatus(statusList)
    }

    val date = remember { mutableStateOf(LocalDate.now().minusYears(100)) }

    LaunchedEffect(date) {
        viewModel.updateDataod(date.toString())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CustomText(
            text = stringResource(id = R.string.text_search)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CustomTextField(
                        label = stringResource(id = R.string.nip),
                        value = state.nip ?: "",
                        onValueChange = {
                            viewModel.updateNip(it)
                        }
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CustomTextField(
                        label = stringResource(id = R.string.regon),
                        value = state.regon ?: "",
                        onValueChange = {
                            viewModel.updateRegon(it)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(4.dp))

        CustomTextField(
            label = stringResource(id = R.string.firm_name),
            value = state.nazwa ?: "",
            onValueChange = {
                viewModel.updateNazwa(it)
            }
        )

        Spacer(modifier = Modifier.width(4.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CustomTextField(
                        label = stringResource(id = R.string.name),
                        value = state.imie ?: "",
                        onValueChange = {
                            viewModel.updateImie(it)
                        }
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CustomTextField(
                        label = stringResource(id = R.string.surname),
                        value = state.nazwisko ?: "",
                        onValueChange = {
                            viewModel.updateNazwisko(it)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(4.dp))

        FilterChip(
            onClick = { includeActive = !includeActive },
            label = {
                Text(stringResource(id = R.string.include_active))
            },
            selected = includeActive,
            leadingIcon = if (includeActive) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
        )

        Spacer(modifier = Modifier.width(4.dp))

        FilterChip(
            onClick = { includeArchived = !includeArchived },
            label = {
                Text(stringResource(id = R.string.include_archived))
            },
            selected = includeArchived,
            leadingIcon = if (includeArchived) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
        )

        FilterChip(
            onClick = { includeRequiringVerification = !includeRequiringVerification },
            label = {
                Text(stringResource(id = R.string.include_requiring_verification))
            },
            selected = includeRequiringVerification,
            leadingIcon = if (includeRequiringVerification) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
        )

        Spacer(modifier = Modifier.padding(4.dp))

        val firstVoivodeshipListElement = stringResource(id = R.string.choose)

        CustomDropdownMenu(
            label = stringResource(id = R.string.voivodeship),
            itemList = arrayOf(firstVoivodeshipListElement) + Voivodeship.list(),
            onSelectItem = { selectedIndex ->
                if (selectedIndex != 0) {
                    viewModel.updateWojewodztwo(Voivodeship.entries[selectedIndex - 1])
                } else {
                    viewModel.updateWojewodztwo(null)
                }
            }
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.poviat),
                    value = state.powiat ?: "",
                    onValueChange = {
                        viewModel.updatePowiat(it)
                    }
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.commune),
                    value = state.gmina ?: "",
                    onValueChange = {
                        viewModel.updateGmina(it)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.4f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.postal_code),
                    value = state.kod ?: "",
                    onValueChange = {
                        viewModel.updateKod(it)
                    }
                )
            }
            Column(
                modifier = Modifier.weight(0.6f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.city),
                    value = state.miasto ?: "",
                    onValueChange = {
                        viewModel.updateMiasto(it)
                    }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.48f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.street),
                    value = state.ulica ?: "",
                    onValueChange = {
                        viewModel.updateUlica(it)
                    }
                )
            }
            Column(
                modifier = Modifier.weight(0.28f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.building_number),
                    value = state.budynek ?: "",
                    onValueChange = {
                        viewModel.updateBudynek(it)
                    },
                    keyboardType = KeyboardType.Number
                )
            }
            Column(
                modifier = Modifier.weight(0.24f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.door_number),
                    value = state.lokal ?: "",
                    onValueChange = {
                        viewModel.updateLokal(it)
                    },
                    keyboardType = KeyboardType.Number
                )
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        CustomDatePicker(
            name = stringResource(id = R.string.date_start),
            date = date
        )

        Spacer(modifier = Modifier.padding(4.dp))

        CustomButton(
            text = stringResource(id = R.string.search_btn),
            onClick = {
                val queryParams = buildQueryParamsFromObject(state)
                navController.navigate("${NavigationScreen.ListResult.route}/$queryParams")
            })

        Spacer(modifier = Modifier.padding(4.dp))
    }

}