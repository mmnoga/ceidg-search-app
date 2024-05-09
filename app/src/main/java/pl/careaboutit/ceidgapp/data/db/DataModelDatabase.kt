package pl.careaboutit.ceidgapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.careaboutit.ceidgapp.data.dao.DataModelDao
import pl.careaboutit.ceidgapp.data.entity.DataModel
import pl.careaboutit.ceidgapp.utils.ImportData

@Database(entities = [DataModel::class], version = 1)
abstract class DataModelDatabase : RoomDatabase() {
    abstract val dao: DataModelDao

    companion object {
        @Volatile
        private var INSTANCE: DataModelDatabase? = null

        fun getDatabase(context: Context): DataModelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataModelDatabase::class.java,
                    "data_model_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomDatabaseCallback(context))
                    .build()

                INSTANCE = instance

                instance
            }
        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        INSTANCE?.dao?.insertAll(getPkdListData(context))
                    }
                }
            }
        }

        private fun getPkdListData(context: Context): List<DataModel> {
            return ImportData.getPdkListFromFile(context)
        }
    }

}