package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pl.careaboutit.ceidgapp.utils.Utils

data class SearchByPkdState(
    val miasto: String = "",
    val pkd: String = ""
)

class SearchFormByPkdViewModel : ViewModel() {

    private var _searchByPkdState = mutableStateOf(SearchByPkdState())

    val searchByPkdState: State<SearchByPkdState> = _searchByPkdState

    fun updateMiasto(newMiasto: String) {
        _searchByPkdState.value = _searchByPkdState.value.copy(miasto = newMiasto)
    }

    fun updatePkd(newPkd: String) {
        _searchByPkdState.value = _searchByPkdState.value.copy(pkd = newPkd)
    }

    fun isPkdValid(): Boolean {
        return Utils.isPkdValid(_searchByPkdState.value.pkd)
    }

}