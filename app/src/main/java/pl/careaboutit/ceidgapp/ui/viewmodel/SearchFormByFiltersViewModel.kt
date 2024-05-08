package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class SearchByFiltersState(
    val nip: String? = "",
    val regon: String? = "",
    val imie: String? = "",
    val nazwisko: String? = "",
    val nazwa: String? = "",
    val ulica: String? ="",
    val budynek: String? = "",
    val lokal: String? = "",
    val miasto: String? = "",
    val wojewodztwo: String? = "",
    val powiat: String? = "",
    val gmina: String? = "",
    val kod: String? = "",
    val pkd: String? = ""
)

class SearchFormByFiltersViewModel : ViewModel() {

    private var _searchState = mutableStateOf(SearchByFiltersState())

    val searchState: State<SearchByFiltersState> = _searchState

    fun updateNip(newNip: String) {
        _searchState.value = _searchState.value.copy(nip = newNip)
    }

    fun updateRegon(newRegon: String) {
        _searchState.value = _searchState.value.copy(regon = newRegon)
    }

    fun updateImie(newImie: String) {
        _searchState.value = _searchState.value.copy(imie = newImie)
    }

    fun updateNazwisko(newNazwisko: String) {
        _searchState.value = _searchState.value.copy(nazwisko = newNazwisko)
    }

    fun updateNazwa(newNazwa: String) {
        _searchState.value = _searchState.value.copy(nazwa = newNazwa)
    }

    fun updateUlica(newUlica: String) {
        _searchState.value = _searchState.value.copy(ulica = newUlica)
    }

    fun updateBudynek(newBudynek: String) {
        _searchState.value = _searchState.value.copy(budynek = newBudynek)
    }

    fun updateLokal(newLokal: String) {
        _searchState.value = _searchState.value.copy(lokal = newLokal)
    }

    fun updateMiasto(newMiasto: String) {
        _searchState.value = _searchState.value.copy(miasto = newMiasto)
    }

    fun updateWojewodztwo(newWojewodztwo: String) {
        _searchState.value = _searchState.value.copy(wojewodztwo = newWojewodztwo)
    }

    fun updatePowiat(newPowiat: String) {
        _searchState.value = _searchState.value.copy(powiat = newPowiat)
    }

    fun updateGmina(newGmina: String) {
        _searchState.value = _searchState.value.copy(gmina = newGmina)
    }

    fun updateKod(newKod: String) {
        _searchState.value = _searchState.value.copy(kod = newKod)
    }

    fun updatePkd(newPkd: String) {
        _searchState.value = _searchState.value.copy(pkd = newPkd)
    }

}