package pl.careaboutit.ceidgapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.careaboutit.ceidgapp.data.entity.DataModel
import pl.careaboutit.ceidgapp.data.repository.DataRepository

class DataModelViewModel(application: Application) : AndroidViewModel(application) {

    private val _repository: DataRepository
    private val allPkds: LiveData<List<DataModel>>

    init {
        _repository = DataRepository(application)
        allPkds = _repository.getAllPkds()
    }

    fun getAllPkds(): LiveData<List<DataModel>> {
        return allPkds
    }

}