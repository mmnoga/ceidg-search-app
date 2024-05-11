package pl.careaboutit.ceidgapp.data.repository

import kotlinx.coroutines.flow.Flow
import pl.careaboutit.ceidgapp.data.dao.PkdDao
import pl.careaboutit.ceidgapp.data.entity.Pkd

class PkdRepository(private val pkdDao: PkdDao) {

    suspend fun addPkds(pkds: List<Pkd>) {
        pkdDao.insertPkds(pkds)
    }

    fun getAllPkds(): Flow<List<Pkd>> {
        return pkdDao.getPkds()
    }
}