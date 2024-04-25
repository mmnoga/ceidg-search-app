package pl.careaboutit.ceidgapp.ui.screens.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.api.model.CompanyDetails
import pl.careaboutit.ceidgapp.ui.components.CustomCardHeader
import pl.careaboutit.ceidgapp.ui.components.CustomCardText
import pl.careaboutit.ceidgapp.ui.components.CustomText
import pl.careaboutit.ceidgapp.viewmodels.CompanyViewModel

@Composable
fun CompanyDetailsScreen(
    navController: NavController,
    viewModel: CompanyViewModel = viewModel()
) {

    val state = viewModel.stateCompanyFlow.collectAsState()
    val companyState = state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (companyState.companyData.firma.isNotEmpty()) {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomText(text = stringResource(id = R.string.text_details))
                    Spacer(modifier = Modifier.height(15.dp))
                }
                itemsIndexed(companyState.companyData.firma) { index, company ->
                    key(index) {
                        CompanyItemDetails(company = company)
                    }
                }
            }
        } else {
            CustomText(text = stringResource(id = R.string.text_no_results))
        }
    }
}

@Composable
fun CompanyItemDetails(company: CompanyDetails) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            CustomCardHeader(
                text = stringResource(id = R.string.text_basic_information)
            )
            CustomCardText(
                key = stringResource(id = R.string.name),
                value = company.wlasciciel?.imie ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.surname),
                value = company.wlasciciel?.nazwisko ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.nip),
                value = company.wlasciciel?.nip ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.regon),
                value = company.wlasciciel?.regon ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.company),
                value = company.nazwa ?: "-"
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomCardHeader(
                text = stringResource(id = R.string.text_contact_information)
            )
            CustomCardText(
                key = stringResource(id = R.string.email),
                value = company.email ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.www),
                value = company.www ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.telephone),
                value = company.telefon ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.additional_contact),
                value = company.innaFormaKonaktu ?: "-"
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomCardHeader(
                text = stringResource(id = R.string.text_address_details)
            )
            CustomCardText(
                key = stringResource(id = R.string.address_principal),
                value = "woj. ${company.adresDzialalnosci?.wojewodztwo ?: "-"}, " +
                        "pow. ${company.adresDzialalnosci?.powiat ?: "-"}, " +
                        "gm. ${company.adresDzialalnosci?.gmina ?: "-"}, " +
                        "miejsc. ${company.adresDzialalnosci?.miasto ?: "-"}, " +
                        "${company.adresDzialalnosci?.ulica ?: "-"}, " +
                        "${company.adresDzialalnosci?.budynek ?: "-"}, " +
                        "${company.adresDzialalnosci?.kod ?: "-"}"
            )
            val addresses =
                company.adresyDzialalnosciDodatkowe?.joinToString(separator = "\n") { address ->
                    buildString {
                        append("woj. ${address.wojewodztwo ?: "-"}, ")
                        append("pow. ${address.powiat ?: "-"}, ")
                        append("gm. ${address.gmina ?: "-"}, ")
                        append("miejsc. ${address.miasto ?: "-"}, ")
                        append("${address.ulica ?: "-"}, ")
                        append("${address.budynek ?: "-"}, ")
                        append("${address.kod ?: "-"}")
                    }
                } ?: "-"
            CustomCardText(
                key = stringResource(id = R.string.addresses_additional),
                value = addresses
            )
            CustomCardText(
                key = stringResource(id = R.string.postal_address),
                value = "woj. ${company.adresKorespondencyjny?.wojewodztwo ?: "-"}, " +
                        "pow. ${company.adresKorespondencyjny?.powiat ?: "-"}, " +
                        "gm. ${company.adresKorespondencyjny?.gmina ?: "-"}, " +
                        "miejsc. ${company.adresKorespondencyjny?.miasto ?: "-"}, " +
                        "${company.adresKorespondencyjny?.ulica ?: "-"}, " +
                        "${company.adresKorespondencyjny?.budynek ?: "-"}, " +
                        "${company.adresKorespondencyjny?.kod ?: "-"}"
            )
            CustomCardText(
                key = stringResource(id = R.string.citenship),
                value = company.obywatelstwa?.joinToString(separator = ", ") { it.symbol } ?: ""
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomCardHeader(
                text = stringResource(id = R.string.text_additional_information)
            )
            CustomCardText(
                key = stringResource(id = R.string.date_start),
                value = company.dataRozpoczecia ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.date_cancel),
                value = company.dataZawieszenia ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.date_resume),
                value = company.dataWznowienia ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.date_stop),
                value = company.dataZakonczenia ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.date_removal),
                value = company.dataWykreslenia ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.pkd_major),
                value = company.pkdGlowny ?: "-"
            )
            CustomCardText(
                key = stringResource(id = R.string.pkd_other_activity),
                value = company.pkd?.joinToString(separator = ", ") ?: ""
            )
            CustomCardText(
                key = stringResource(id = R.string.conjugal_joint),
                value = when (company.wspolnoscMajatkowa) {
                    0 -> stringResource(id = R.string.no)
                    1 -> stringResource(id = R.string.yes)
                    else -> stringResource(id = R.string.not_applicable)
                }
            )
            CustomCardText(
                key = stringResource(id = R.string.status),
                value = company.status ?: "-"
            )
        }
    }
}