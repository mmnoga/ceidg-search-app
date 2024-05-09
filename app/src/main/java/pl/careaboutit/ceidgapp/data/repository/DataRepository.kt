package pl.careaboutit.ceidgapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import pl.careaboutit.ceidgapp.data.dao.DataModelDao
import pl.careaboutit.ceidgapp.data.db.DataModelDatabase
import pl.careaboutit.ceidgapp.data.entity.DataModel

class DataRepository(application: Application) {

    private val _pkdDao: DataModelDao
    private val allPkds: LiveData<List<DataModel>>

    init {
        val db = DataModelDatabase.getDatabase(application)
        _pkdDao = db.dao
        allPkds = _pkdDao.getAllRecords()
    }

    fun getAllPkds(): LiveData<List<DataModel>> {
        return allPkds
    }

}