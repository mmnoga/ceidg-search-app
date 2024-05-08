package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.careaboutit.ceidgapp.BuildConfig
import pl.careaboutit.ceidgapp.data.network.GeocodingService
import pl.careaboutit.ceidgapp.data.repository.GeocodingModule
import pl.careaboutit.ceidgapp.data.model.GeocodingResponse
import retrofit2.HttpException
import timber.log.Timber

data class LocationState(
    val locationData: GeocodingResponse,
    val isLoading: Boolean,
    val error: String?
)

class LocationViewModel : ViewModel() {
    private val _stateLocationFlow: MutableStateFlow<LocationState> = MutableStateFlow(
        LocationState(
            locationData = GeocodingResponse(),
            isLoading = true,
            error = null
        )
    )

    val stateLocationFlow: StateFlow<LocationState>
        get() = _stateLocationFlow.asStateFlow()

    private val geocodingService: GeocodingService = GeocodingModule().geocodingService

    private val key: String = BuildConfig.MAPS_API_KEY

    fun getCoordinatesFromAddress(address: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = geocodingService.getCoordinatesFromAddress(address, key)
                if (response.isSuccessful) {
                    val data = response.body()
                    _stateLocationFlow.value = _stateLocationFlow.value.copy(
                        locationData = data ?: GeocodingResponse(),
                        isLoading = false
                    )
                } else {
                    Timber.e("Error: ${response.code()}")
                    _stateLocationFlow.value = _stateLocationFlow.value.copy(
                        isLoading = false,
                        error = response.code().toString()
                    )
                }
            } catch (exception: HttpException) {
                Timber.e(exception)
                _stateLocationFlow.value = _stateLocationFlow.value.copy(
                    isLoading = false,
                    error = exception.code().toString()
                )
            }
        }
    }
}