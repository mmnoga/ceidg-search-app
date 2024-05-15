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
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.navigation.NavigationScreen
import pl.careaboutit.ceidgapp.ui.screens.components.CustomButton
import pl.careaboutit.ceidgapp.ui.screens.components.CustomDatePicker
import pl.careaboutit.ceidgapp.ui.screens.components.CustomDropdownMenu
import pl.careaboutit.ceidgapp.ui.screens.components.CustomText
import pl.careaboutit.ceidgapp.ui.screens.components.CustomTextField
import pl.careaboutit.ceidgapp.ui.viewmodel.FormState
import pl.careaboutit.ceidgapp.ui.viewmodel.FormViewModel
import pl.careaboutit.ceidgapp.utils.Status
import pl.careaboutit.ceidgapp.utils.Voivodeship
import pl.careaboutit.ceidgapp.utils.buildQueryParamsFromObject
import timber.log.Timber
import java.time.LocalDate

private val initialFormState = FormState(
    fields = mutableMapOf(
        "nip" to "",
        "regon" to "",
        "nazwa" to "",
        "imie" to "",
        "nazwisko" to "",
        "ulica" to "",
        "budynek" to "",
        "lokal" to "",
        "miasto" to "",
        "wojewodztwo" to Voivodeship.BRAK,
        "powiat" to "",
        "gmina" to "",
        "kod" to "",
        "pkd" to "",
        "dataod" to "",
        "status" to emptyList<Status>(),
    )
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchAdvancedForm(
    navController: NavHostController,
    viewModel: FormViewModel = koinViewModel { parametersOf(initialFormState) }
) {
    val formState = viewModel.state

    var includeArchived by rememberSaveable { mutableStateOf(false) }

    var includeRequiringVerification by rememberSaveable { mutableStateOf(false) }

    var statusList by remember { mutableStateOf<List<Status>>(emptyList()) }
    DisposableEffect(includeArchived, includeRequiringVerification) {
        statusList = mutableListOf<Status>().apply {
            if (includeArchived) add(Status.WYKRESLONY)
            if (includeRequiringVerification) add(Status.OCZEKUJE_NA_ROZPOCZECIE_DZIALANOSCI)
        }
        onDispose { }
    }

    LaunchedEffect(statusList) {
        viewModel.updateField("status", statusList)
    }

    val date = remember { mutableStateOf(LocalDate.now().minusYears(100)) }

    LaunchedEffect(formState) {
        viewModel.updateField("dataod", date.value.toString())
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
                        value = formState.fields["nip"] ?: "",
                        onValueChange = {
                            viewModel.updateField("nip", it)
                        }
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CustomTextField(
                        label = stringResource(id = R.string.regon),
                        value = formState.fields["regon"] ?: "",
                        onValueChange = {
                            viewModel.updateField("regon", it)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(4.dp))

        CustomTextField(
            label = stringResource(id = R.string.firm_name),
            value = formState.fields["nazwa"] ?: "",
            onValueChange = {
                viewModel.updateField("nazwa", it)
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
                        value = formState.fields["imie"] ?: "",
                        onValueChange = {
                            viewModel.updateField("imie", it)
                        }
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CustomTextField(
                        label = stringResource(id = R.string.surname),
                        value = formState.fields["nazwisko"] ?: "",
                        onValueChange = {
                            viewModel.updateField("nazwisko", it)
                        }
                    )
                }
            }
        }

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

        CustomDropdownMenu(
            label = stringResource(id = R.string.voivodeship),
            items = Voivodeship.list(),
            selectedIndex = formState.fields["wojewodztwo"]?.let { selectedWojewodztwo ->
                Voivodeship.entries.toTypedArray()
                    .indexOfFirst { it.displayName == selectedWojewodztwo }
            } ?: -1,
            onItemSelected = { index, item ->
                viewModel.updateField("wojewodztwo", item)
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
                    value = formState.fields["powiat"] ?: "",
                    onValueChange = {
                        viewModel.updateField("powiat", it)
                    }
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.commune),
                    value = formState.fields["gmina"] ?: "",
                    onValueChange = {
                        viewModel.updateField("gmina", it)
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
                    value = formState.fields["kod"] ?: "",
                    onValueChange = {
                        viewModel.updateField("kod", it)
                    }
                )
            }

            Column(
                modifier = Modifier.weight(0.6f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.city),
                    value = formState.fields["miasto"] ?: "",
                    onValueChange = {
                        viewModel.updateField("miasto", it)
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
                    value = formState.fields["ulica"] ?: "",
                    onValueChange = {
                        viewModel.updateField("ulica", it)
                    }
                )
            }

            Column(
                modifier = Modifier.weight(0.28f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.building_number),
                    value = formState.fields["budynek"] ?: "",
                    onValueChange = {
                        viewModel.updateField("budynek", it)
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
                    value = formState.fields["lokal"] ?: "",
                    onValueChange = {
                        viewModel.updateField("lokal", it)
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
                val queryParams = buildQueryParamsFromObject(formState)

                Timber.d("queryParams: $queryParams")

                navController.navigate("${NavigationScreen.ListResult.route}/$queryParams")
            })

        Spacer(modifier = Modifier.padding(4.dp))
    }

}