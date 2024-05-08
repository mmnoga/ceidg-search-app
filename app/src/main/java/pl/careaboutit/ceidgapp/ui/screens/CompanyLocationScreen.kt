package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import pl.careaboutit.ceidgapp.ui.screens.components.CustomErrorMessage
import pl.careaboutit.ceidgapp.ui.viewmodel.CompanyDetailsViewModel
import pl.careaboutit.ceidgapp.ui.viewmodel.LocationViewModel

@Composable
fun CompanyLocationScreen(
    companyId: String,
    locationViewModel: LocationViewModel = viewModel(),
    companyDetailsViewModel: CompanyDetailsViewModel = viewModel()
) {
    val stateLocationViewModel = locationViewModel.stateLocationFlow.collectAsState()
    val locationState = stateLocationViewModel.value
    val stateCompanyDetailsViewModel = companyDetailsViewModel.stateFlow.collectAsState()
    val companyDetailsState = stateCompanyDetailsViewModel.value

    LaunchedEffect(key1 = companyId) {
        companyDetailsViewModel.getCompanyDataById(companyId)
    }

    when {
        companyDetailsState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        companyDetailsState.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error: ${companyDetailsState.error}")
            }
        }

        else -> {
            val company = companyDetailsState.companyList[0]

            val address = "${company.adresDzialalnosci.kod} " +
                    "${company.adresDzialalnosci.miasto} " +
                    "${company.adresDzialalnosci.ulica} " +
                    "${company.adresDzialalnosci.budynek}"
            locationViewModel.getCoordinatesFromAddress(address)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            locationState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(90.dp)
                )
            }

            locationState.error != null -> {
                CustomErrorMessage(errorCode = "${locationState.error}")
            }

            else -> {
                val lat =
                    locationState.locationData.results
                        .getOrNull(0)?.geometry?.location?.lat ?: 0.0
                val lng =
                    locationState.locationData.results
                        .getOrNull(0)?.geometry?.location?.lng ?: 0.0

                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(LatLng(lat, lng), 15f)
                }

                GoogleMap(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 16.dp),
                    cameraPositionState = cameraPositionState,
                ) {
                    Marker(state = MarkerState(position = LatLng(lat, lng)))
                }
            }
        }
    }
}