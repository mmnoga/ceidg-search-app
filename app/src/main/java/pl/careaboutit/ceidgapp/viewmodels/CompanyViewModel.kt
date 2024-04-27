package pl.careaboutit.ceidgapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.careaboutit.ceidgapp.api.ApiCeidgService
import pl.careaboutit.ceidgapp.api.NetworkModule
import pl.careaboutit.ceidgapp.api.model.CompaniesDataResponse
import pl.careaboutit.ceidgapp.api.model.CompanyDataResponse
import retrofit2.HttpException
import timber.log.Timber

data class CompaniesState(
    val companiesData: CompaniesDataResponse,
    val isLoading: Boolean,
    val error: String?
)

data class CompanyState(
    val companyData: CompanyDataResponse,
    val isLoading: Boolean,
    val error: String?
)

class CompanyViewModel : ViewModel() {
    private val _stateCompaniesFlow: MutableStateFlow<CompaniesState> = MutableStateFlow(
        CompaniesState(
            companiesData = CompaniesDataResponse(),
            isLoading = true,
            error = null
        )
    )

    private val _stateCompanyFlow: MutableStateFlow<CompanyState> = MutableStateFlow(
        CompanyState(
            companyData = CompanyDataResponse(),
            isLoading = true,
            error = null
        )
    )

    val stateCompaniesFlow: StateFlow<CompaniesState>
        get() = _stateCompaniesFlow.asStateFlow()

    val stateCompanyFlow: StateFlow<CompanyState>
        get() = _stateCompanyFlow.asStateFlow()

    private val apiCeidgService: ApiCeidgService = NetworkModule().apiCeidgService

    fun getCompaniesByNip(nip: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiCeidgService.getCompanyData(nip)
                if (response.isSuccessful) {
                    val data = response.body()
                    _stateCompaniesFlow.value = _stateCompaniesFlow.value.copy(
                        companiesData = data ?: CompaniesDataResponse(),
                        isLoading = false
                    )
                } else {
                    Timber.e("Error: ${response.code()}")
                    _stateCompaniesFlow.value = _stateCompaniesFlow.value.copy(
                        isLoading = false,
                        error = response.code().toString()
                    )
                }
            } catch (exception: HttpException) {
                Timber.e(exception)
                _stateCompaniesFlow.value = _stateCompaniesFlow.value.copy(
                    isLoading = false,
                    error = exception.code().toString()
                )
            }
        }
    }

    fun getCompaniesByPkd(pkd: String, city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiCeidgService.getCompaniesByPkdAndCity(pkd, city)
                if (response.isSuccessful) {
                    val data = response.body()
                    _stateCompaniesFlow.value = _stateCompaniesFlow.value.copy(
                        companiesData = data ?: CompaniesDataResponse(),
                        isLoading = false
                    )
                } else {
                    Timber.e("Error: ${response.code()}")
                    _stateCompaniesFlow.value = _stateCompaniesFlow.value.copy(
                        isLoading = false,
                        error = response.code().toString()
                    )
                }
            } catch (exception: HttpException) {
                Timber.e(exception)
                _stateCompaniesFlow.value = _stateCompaniesFlow.value.copy(
                    isLoading = false,
                    error = exception.code().toString()
                )
            }
        }
    }

    fun getCompanyDetails(nip: String? = null, regon: String? = null, ids: List<String>? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiCeidgService.getCompanyDetailsData(nip, regon, ids)
                if (response.isSuccessful) {
                    val data = response.body()
                    _stateCompanyFlow.value = _stateCompanyFlow.value.copy(
                        companyData = data ?: CompanyDataResponse(),
                        isLoading = false
                    )
                } else {
                    Timber.e("Error: ${response.errorBody()}")
                    _stateCompanyFlow.value = _stateCompanyFlow.value.copy(
                        isLoading = false,
                        error = response.errorBody().toString()
                    )
                }
            } catch (exception: Exception) {
                Timber.e(exception)
                _stateCompanyFlow.value = _stateCompanyFlow.value.copy(
                    isLoading = false,
                    error = exception.message
                )
            }
        }
    }
}