package pl.careaboutit.ceidgapp.utils

enum class Status(val displayName: String) {
    AKTYWNY("Aktywny"),
    WYKRESLONY("Wykreślony"),
    ZAWIESZONY("Zawieszony"),
    OCZEKUJE_NA_ROZPOCZECIE_DZIALANOSCI("Oczekuje na rozpoczęcie działalności"),
    WYLACZNIE_W_FORMIE_SPOLKI("Wyłącznie w formie spółki");

    companion object {
        fun list(): Array<String> {
            return entries.map { it.displayName }.toTypedArray()
        }
    }
}