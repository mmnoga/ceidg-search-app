package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import pl.careaboutit.ceidgapp.ui.navigation.NavigationScreen
import pl.careaboutit.ceidgapp.ui.viewmodel.CompanyDetailsViewModel
import timber.log.Timber

@Composable
fun CompanyDetailsScreen(
    viewModel: CompanyDetailsViewModel = viewModel(),
    companyId: String,
    navController: NavHostController
) {
    val state by viewModel.stateFlow.collectAsState()

    LaunchedEffect(key1 = companyId) {
        viewModel.getCompanyDataById(companyId)
    }

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${state.error}")
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                LazyColumn {
                    itemsIndexed(state.companyList) { index, company ->
                        CompanyDetailsItem(
                            company = company,
                            clicked = { companyId ->
                                Timber.d("COMPANY ID: $companyId")
                                navController.navigate("${NavigationScreen.CompanyLocation.route}/${companyId}")
                            }
                        )
                        if (index < state.companyList.size - 1) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }

}