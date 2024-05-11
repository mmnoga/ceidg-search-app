package pl.careaboutit.ceidgapp.utils

import android.content.Context
import pl.careaboutit.ceidgapp.data.entity.Pkd

object ImportData {
    private const val PKD_FILE_NAME = "pkd_file.csv"

    fun getPdkListFromFile(context: Context): List<Pkd> {
        val csvData = mutableListOf<Pkd>()
        try {
            val inputStream = context.assets.open(PKD_FILE_NAME)
            inputStream.bufferedReader().useLines { lines ->
                var firstLineSkipped = false
                lines.forEach { line ->
                    if (!firstLineSkipped) {
                        firstLineSkipped = true
                        return@forEach
                    }
                    val row = line.split(";")
                    if (row.size >= 4) {
                        val id = row[0].replace("\"", "").toInt()
                        val code = row[1].replace("\"", "").trim()
                        val name = row[2].replace("\"", "").trim()
                        val description = row[3].replace("\"", "").trim()
                        val pkd =
                            Pkd(id = id, code = code, name = name, description = description)
                        csvData.add(pkd)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return csvData
    }
}