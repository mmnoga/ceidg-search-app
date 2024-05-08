package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import pl.careaboutit.ceidgapp.utils.Status
import pl.careaboutit.ceidgapp.utils.Voivodeship

data class AdvancedSearchState(
    val nip: String? = null,
    val regon: String? = null,
    val nazwa: String? = null,
    val imie: String? = null,
    val nazwisko: String? = null,
    val ulica: String? = null,
    val budynek: String? = null,
    val lokal: String? = null,
    val miasto: String? = null,
    val wojewodztwo: Voivodeship? = null,
    val powiat: String? = null,
    val gmina: String? = null,
    val kod: String? = null,
    val pkd: String? = null,
    val dataod: String? = null,
    val status: List<Status> = emptyList()
)

class AdvancedSearchViewModel : ViewModel() {

    private var _advancedSearchState = mutableStateOf(AdvancedSearchState())

    val advancedSearchState: State<AdvancedSearchState> = _advancedSearchState

    fun updateNip(newNip: String) {
        _advancedSearchState.value = _advancedSearchState.value.copy(nip = newNip)
    }

    fun updateRegon(newRegon: String) {
        _advancedSearchState.value = _advancedSearchState.value.copy(regon = newRegon)
    }

    fun updateNazwa(newNazwa: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(nazwa = newNazwa)
    }

    fun updateImie(newImie: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(imie = newImie)
    }

    fun updateNazwisko(newNazwisko: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(nazwisko = newNazwisko)
    }

    fun updateUlica(newUlica: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(ulica = newUlica)
    }

    fun updateBudynek(newBudynek: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(budynek = newBudynek)
    }

    fun updateLokal(newLokal: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(lokal = newLokal)
    }

    fun updateMiasto(newMiasto: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(miasto = newMiasto)
    }

    fun updateWojewodztwo(newWojewodztwo: Voivodeship?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(wojewodztwo = newWojewodztwo)
    }

    fun updatePowiat(newPowiat: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(powiat = newPowiat)
    }

    fun updateGmina(newGmina: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(gmina = newGmina)
    }

    fun updateKod(newKod: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(kod = newKod)
    }

    fun updatePkd(newPkd: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(pkd = newPkd)
    }

    fun updateDataod(newDataod: String?) {
        _advancedSearchState.value = _advancedSearchState.value.copy(dataod = newDataod)
    }

    fun updateStatus(newStatus: List<Status>) {
        _advancedSearchState.value = _advancedSearchState.value.copy(status = newStatus)
    }

}