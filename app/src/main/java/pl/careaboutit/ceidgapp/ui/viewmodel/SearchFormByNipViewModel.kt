package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pl.careaboutit.ceidgapp.utils.Utils

data class SearchByNipState(
    val nip: String = ""
)

class SearchFormByNipViewModel : ViewModel() {

    private var _searchByNipState = mutableStateOf(SearchByNipState())

    val searchByNipState: State<SearchByNipState> = _searchByNipState

    fun updateNip(newNip: String) {
        _searchByNipState.value = _searchByNipState.value.copy(nip = newNip)
    }

    fun isNipValid(): Boolean {
        return Utils.isNipValid(_searchByNipState.value.nip)
    }

}