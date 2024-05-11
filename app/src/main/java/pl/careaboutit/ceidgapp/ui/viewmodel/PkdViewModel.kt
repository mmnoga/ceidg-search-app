package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import pl.careaboutit.ceidgapp.data.entity.Pkd
import pl.careaboutit.ceidgapp.data.repository.PkdRepository
import pl.careaboutit.ceidgapp.di.Graph

class PkdViewModel(
    private val pkdRepository: PkdRepository = Graph.pkdRepository
) : ViewModel() {
    lateinit var getAllPkd: Flow<List<Pkd>>

    init {
        viewModelScope.launch {
            getAllPkd = pkdRepository.getAllPkds()
        }
    }
}