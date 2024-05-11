package pl.careaboutit.ceidgapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pkd_table")
data class Pkd(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val code: String,
    val name: String,
    val description: String
)