package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.screens.components.CustomErrorMessage
import pl.careaboutit.ceidgapp.ui.screens.components.CustomText
import pl.careaboutit.ceidgapp.ui.navigation.NavigationScreen
import pl.careaboutit.ceidgapp.ui.viewmodel.SearchByFiltersResultViewModel
import pl.careaboutit.ceidgapp.utils.mapQueryRequestToDynamicRequest
import pl.careaboutit.ceidgapp.utils.parseDynamicQueryParams

@Composable
fun SearchByFiltersResultScreen(
    viewModel: SearchByFiltersResultViewModel = viewModel(),
    queryParams: String,
    navController: NavHostController
) {
    val state by viewModel.stateFlow.collectAsState()
    val dynamicRequest = remember { parseDynamicQueryParams(queryParams) }

    LaunchedEffect(key1 = dynamicRequest.toString()) {
        val dynamicRequestConverted = mapQueryRequestToDynamicRequest(dynamicRequest)
        viewModel.getCompanyDataByFilter(dynamicRequestConverted)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(90.dp)
                    )
                }
            }

            state.error != null -> {
                CustomErrorMessage(errorCode = "${state.error}")
            }

            else -> {
                if (state.companyList.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyColumn {
                        itemsIndexed(state.companyList) { index, company ->
                            CompanyListItem(
                                company = company,
                                clicked = { companyId ->
                                    navController.navigate("${NavigationScreen.Details.route}/$companyId")
                                }
                            )
                            if (index < state.companyList.size - 1) {
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