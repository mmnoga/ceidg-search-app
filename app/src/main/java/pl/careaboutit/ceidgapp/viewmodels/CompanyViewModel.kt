package pl.careaboutit.ceidgapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.careaboutit.ceidgapp.api.ApiService
import pl.careaboutit.ceidgapp.api.NetworkModule
import pl.careaboutit.ceidgapp.api.model.CompanyDataResponse
import retrofit2.HttpException
import timber.log.Timber

data class CompanyState(
    val companyData: CompanyDataResponse,
    val isLoading: Boolean,
    val error: String?
)

class CompanyViewModel : ViewModel() {
    private val _stateFlow: MutableStateFlow<CompanyState> = MutableStateFlow(
        CompanyState(
            companyData = CompanyDataResponse(),
            isLoading = true,
            error = null
        )
    )

    val stateFlow: StateFlow<CompanyState>
        get() = _stateFlow.asStateFlow()

    private val apiService: ApiService = NetworkModule().apiService

    fun searchCompanyByNip(nip: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = apiService.getCompanyData(nip)
                Timber.i(data.toString())
                _stateFlow.value = _stateFlow.value.copy(
                    companyData = data,
                    isLoading = false
                )
            } catch (exception: HttpException) {
                Timber.e(exception)
                _stateFlow.value = _stateFlow.value.copy(
                    isLoading = false,
                    error = exception.code().toString()
                )
            }
        }
    }

    fun searchCompanyByPkd(pkd: String) {

    }
}