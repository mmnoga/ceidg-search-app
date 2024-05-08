package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.careaboutit.ceidgapp.data.model.Company
import pl.careaboutit.ceidgapp.data.model.CompanyElementDisplayable
import pl.careaboutit.ceidgapp.data.model.CompanyListState
import pl.careaboutit.ceidgapp.data.model.DynamicRequest
import pl.careaboutit.ceidgapp.data.model.toDisplayable
import pl.careaboutit.ceidgapp.data.network.CeidgService
import pl.careaboutit.ceidgapp.data.repository.NetworkModule
import retrofit2.HttpException
import timber.log.Timber

class SearchByFiltersResultViewModel : ViewModel() {

    private val _stateFlow: MutableStateFlow<CompanyListState> = MutableStateFlow(
        CompanyListState(
            companyList = emptyList(),
            isLoading = true,
            error = null
        )
    )

    val stateFlow: StateFlow<CompanyListState>
        get() = _stateFlow

    private val ceidgService: CeidgService = NetworkModule().ceidgService

    fun getCompanyDataByFilter(dynamicRequest: DynamicRequest) {
        val filters = dynamicRequest.params.filterValues { it.isNotEmpty() }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ceidgService.getCompanyListByFilters(filters)
                val data: List<Company> = response.body()?.firmy ?: emptyList()
                val companyList: List<CompanyElementDisplayable> = data.map {
                    it.toDisplayable()
                }
                _stateFlow.value = _stateFlow.value.copy(
                    companyList = companyList,
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

}