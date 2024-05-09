package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

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
        val cleanedPkd = newPkd.replace(".", "")

        _searchByPkdState.value = _searchByPkdState.value.copy(pkd = cleanedPkd)
    }

}