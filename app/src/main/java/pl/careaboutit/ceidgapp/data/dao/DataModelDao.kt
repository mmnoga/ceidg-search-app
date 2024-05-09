package pl.careaboutit.ceidgapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import pl.careaboutit.ceidgapp.data.entity.DataModel

@Dao
interface DataModelDao {
    @Query("SELECT * FROM pkd_table")
    fun getAllRecords(): LiveData<List<DataModel>>

    @Upsert
    suspend fun insertAll(dataModels: List<DataModel>)

}