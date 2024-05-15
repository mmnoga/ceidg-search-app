package pl.careaboutit.ceidgapp.utils

enum class Voivodeship(val displayName: String) {
    BRAK("Brak"),
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
        fun list(): List<String> {
            return entries.map { it.displayName }
        }
    }
}