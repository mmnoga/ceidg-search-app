package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.components.CustomErrorMessage
import pl.careaboutit.ceidgapp.ui.components.CustomText
import pl.careaboutit.ceidgapp.ui.screens.common.CompanyItem
import pl.careaboutit.ceidgapp.viewmodels.CompaniesState
import pl.careaboutit.ceidgapp.viewmodels.CompanyViewModel

@Composable
fun SearchByPkdResultScreen(
    navController: NavController,
    viewModel: CompanyViewModel = viewModel()
) {
    val state = viewModel.stateCompaniesFlow.collectAsState()
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
                if (companyState.companiesData.firmy.isNotEmpty()) {
                    LazyColumn {
                        item {
                            SummaryHeader(companyState)
                        }
                        itemsIndexed(companyState.companiesData.firmy) { index, company ->
                            key(index) {
                                CompanyItem(company = company)
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
private fun SummaryHeader(companiesState: CompaniesState) {
    Spacer(modifier = Modifier.height(15.dp))
    CustomText(text = stringResource(id = R.string.text_result))
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = stringResource(
            id = R.string.search_by_pkd_header,
            companiesState.companiesData.firmy.size
        )
    )
    Spacer(modifier = Modifier.height(15.dp))
}