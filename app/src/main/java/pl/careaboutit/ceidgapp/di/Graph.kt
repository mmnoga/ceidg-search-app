package pl.careaboutit.ceidgapp.di

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import pl.careaboutit.ceidgapp.data.db.PkdDatabase
import pl.careaboutit.ceidgapp.data.entity.Pkd
import pl.careaboutit.ceidgapp.data.repository.PkdRepository
import pl.careaboutit.ceidgapp.utils.ImportData

object Graph {
    private lateinit var database: PkdDatabase

    val pkdRepository by lazy {
        PkdRepository(pkdDao = database.pkdDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, PkdDatabase::class.java, "pkdlist.db").build()

        val pkdList = ImportData.getPdkListFromFile(context)

        if (pkdList.isNotEmpty()) {
            importDataToDatabase(pkdList)
        }
    }

    private fun importDataToDatabase(
        pkdList: List<Pkd>
    ) {
        val applicationScope = CoroutineScope(SupervisorJob())
        applicationScope.launch {
            pkdRepository.addPkds(pkdList)
        }
    }
}