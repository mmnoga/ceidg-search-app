package pl.careaboutit.ceidgapp.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.utils.Voivodeship
import pl.careaboutit.ceidgapp.ui.components.CustomButton
import pl.careaboutit.ceidgapp.ui.components.CustomDropdownMenu
import pl.careaboutit.ceidgapp.ui.components.CustomText
import pl.careaboutit.ceidgapp.ui.components.CustomTextField

@Composable
fun AdvancedSearchScreen() {
    var firmName by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var surname by rememberSaveable { mutableStateOf("") }
    var poviat by rememberSaveable { mutableStateOf("") }
    var commune by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var street by rememberSaveable { mutableStateOf("") }
    var buildingNumber by rememberSaveable { mutableStateOf("") }
    var doorNumber by rememberSaveable { mutableStateOf("") }
    var includeArchived by rememberSaveable { mutableStateOf(false) }
    var includeRequiringVerification by rememberSaveable { mutableStateOf(false) }
    var address by rememberSaveable { mutableStateOf(AddressType.MAIN_BUSINESS_PLACE) }
    var voivodeship by rememberSaveable { mutableStateOf(Voivodeship.MALOPOLSKIE) }

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

        CustomTextField(
            label = stringResource(id = R.string.firm_name),
            value = firmName,
            onValueChange = {
                firmName = it
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
                        value = name,
                        onValueChange = {
                            name = it
                        }
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CustomTextField(
                        label = stringResource(id = R.string.surname),
                        value = surname,
                        onValueChange = {
                            surname = it
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
            label = stringResource(id = R.string.address_type),
            itemList = AddressType.entries.map { stringResource(it.resourceId) }.toTypedArray(),
            onSelectItem = { selected ->
                val selectedItem =
                    AddressType.entries.firstOrNull { (it.description) == selected }
                if (selectedItem != null) {
                    address = selectedItem
                }
            }
        )

        Spacer(modifier = Modifier.padding(4.dp))

        CustomDropdownMenu(
            label = stringResource(id = R.string.voivodeship),
            itemList = Voivodeship.list(),
            onSelectItem = { selected ->
                voivodeship = Voivodeship.valueOf(selected)
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
                    value = poviat,
                    onValueChange = {
                        poviat = it
                    }
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.commune),
                    value = commune,
                    onValueChange = {
                        commune = it
                    }
                )
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CustomTextField(
                label = stringResource(id = R.string.city),
                value = city,
                onValueChange = {
                    city = it
                }
            )
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
                    value = street,
                    onValueChange = {
                        street = it
                    }
                )
            }
            Column(
                modifier = Modifier.weight(0.28f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTextField(
                    label = stringResource(id = R.string.building_number),
                    value = buildingNumber,
                    onValueChange = {
                        buildingNumber = it
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
                    value = doorNumber,
                    onValueChange = {
                        doorNumber = it
                    },
                    keyboardType = KeyboardType.Number
                )
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        CustomButton(
            text = stringResource(id = R.string.search_btn),
            onClick = { /*TODO*/ })

        Spacer(modifier = Modifier.padding(4.dp))
    }
}

private enum class AddressType(val description: String, val resourceId: Int) {
    MAIN_BUSINESS_PLACE("MAIN_BUSINESS_PLACE", R.string.main_business_place),
    DELIVERY_ADDRESS("DELIVERY_ADDRESS", R.string.delivery_address),
    ADDITIONAL_BUSINESS_PLACE("ADDITIONAL_BUSINESS_PLACE", R.string.additional_business_place)
}

@Preview(showBackground = true)
@Composable
fun AdvancedSearchScreenPreview() {
    AdvancedSearchScreen()
}