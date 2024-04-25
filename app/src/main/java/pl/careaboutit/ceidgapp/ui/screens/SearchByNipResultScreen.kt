package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import pl.careaboutit.ceidgapp.CeidgScreen
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.api.model.CompanyDetails
import pl.careaboutit.ceidgapp.ui.components.CustomErrorMessage
import pl.careaboutit.ceidgapp.ui.components.CustomText
import pl.careaboutit.ceidgapp.viewmodels.CompanyViewModel

@Composable
fun SearchByNipResultScreen(
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
        when {
            companyState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(90.dp)
                )
            }

            companyState.error != null -> {
                CustomErrorMessage(errorCode = "${companyState.error}")
            }

            else -> {
                if (companyState.companyData.firma.isNotEmpty()) {
                    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                        item() {
                            Spacer(modifier = Modifier.height(15.dp))
                            CustomText(text = stringResource(id = R.string.text_result))
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                        itemsIndexed(companyState.companyData.firma) { index, company ->
                            key(index) {
                                CompanyItemDetails(company = company) {
                                    navController.navigate(
                                        CeidgScreen.CompanyDetails.name
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                } else {
                    CustomText(text = stringResource(id = R.string.text_no_results))
                }
            }
        }
    }
}

@Composable
private fun CompanyItemDetails(
    company: CompanyDetails,
    onClickInfo: () -> Unit
) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = company.nazwa,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                IconButton(
                    onClick = onClickInfo,
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.enterpreneur), fontWeight = FontWeight.Bold)
            Text(text = "${company.wlasciciel?.imie ?: "-"} ${company.wlasciciel?.nazwisko ?: "-"}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = R.string.nip), fontWeight = FontWeight.Bold)
            Text(text = company.wlasciciel?.nip ?: "-")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = R.string.regon), fontWeight = FontWeight.Bold)
            Text(text = company.wlasciciel?.regon ?: "-")
            Text(text = stringResource(id = R.string.city), fontWeight = FontWeight.Bold)
            Text(text = company.adresDzialalnosci?.miasto ?: "-")
        }
    }
}