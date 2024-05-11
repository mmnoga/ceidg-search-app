package pl.careaboutit.ceidgapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.careaboutit.ceidgapp.data.dao.PkdDao
import pl.careaboutit.ceidgapp.data.entity.Pkd

@Database(
    entities = [Pkd::class],
    version = 1,
    exportSchema = false
)
abstract class PkdDatabase : RoomDatabase() {
    abstract fun pkdDao(): PkdDao
}