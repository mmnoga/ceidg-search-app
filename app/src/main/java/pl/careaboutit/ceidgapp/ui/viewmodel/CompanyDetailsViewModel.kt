package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.careaboutit.ceidgapp.data.model.CompanyDetails
import pl.careaboutit.ceidgapp.data.model.CompanyDetailsElementDisplayable
import pl.careaboutit.ceidgapp.data.model.CompanyDetailsState
import pl.careaboutit.ceidgapp.data.model.toDisplayable
import pl.careaboutit.ceidgapp.data.network.CeidgService
import pl.careaboutit.ceidgapp.data.repository.NetworkModule
import retrofit2.HttpException
import timber.log.Timber

class CompanyDetailsViewModel : ViewModel() {

    private val _stateFlow: MutableStateFlow<CompanyDetailsState> = MutableStateFlow(
        CompanyDetailsState(
            companyList = emptyList(),
            isLoading = true,
            error = null
        )
    )

    val stateFlow: StateFlow<CompanyDetailsState>
        get() = _stateFlow

    private val ceidgService: CeidgService = NetworkModule().ceidgService

    fun getCompanyDataById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ceidgService.getCompanyDetailsById(id)
                val data: List<CompanyDetails> = response.body()?.firma ?: emptyList()
                val companyList: List<CompanyDetailsElementDisplayable> = data.map {
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
