package pl.careaboutit.ceidgapp.utils

enum class Voivodeship(val displayName: String) {
    DOLNOSLASKIE("Dolnośląskie"),
    KUJAWSKO_POMORSKIE("Kujawsko-Pomorskie"),
    LODZKIE("Łódzkie"),
    LUBELSKIE("Lubelskie"),
    LUBUSKIE("Lubuskie"),
    MALOPOLSKIE("Małopolskie"),
    MAZOWIECKIE("Mazowieckie"),
    OPOLSKIE("Opolskie"),
    PODKARPACKIE("Podkarpackie"),
    PODLASKIE("Podlaskie"),
    POMORSKIE("Pomorskie"),
    SLASKIE("Śląskie"),
    SWIETOKRZYSKIE("Świętokrzyskie"),
    WARMINSKO_MAZURSKIE("Warmińsko-Mazurskie"),
    WIELKOPOLSKIE("Wielkopolskie"),
    ZACHODNIOPOMORSKIE("Zachodniopomorskie");

    companion object {
        fun list(): Array<String> {
            return entries.map { it.displayName }.toTypedArray()
        }
    }
}