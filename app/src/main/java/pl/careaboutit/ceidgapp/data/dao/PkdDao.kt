package pl.careaboutit.ceidgapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import pl.careaboutit.ceidgapp.data.entity.Pkd

@Dao
interface PkdDao {
    @Query("SELECT * FROM pkd_table")
    fun getPkds(): Flow<List<Pkd>>

    @Upsert
    suspend fun insertPkds(pkds: List<Pkd>)
}