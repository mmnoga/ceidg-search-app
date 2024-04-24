package pl.careaboutit.ceidgapp.utils

fun isPkdValid(pkdNumber: String): Boolean {
    val sanitizedPKD = pkdNumber
        .replace(".", "")
        .replace(" ", "")

    if (sanitizedPKD.length != 5) {
        return false
    }

    if (!sanitizedPKD.take(4).all { it.isDigit() }) {
        return false
    }

    if (!sanitizedPKD.last().isLetter()) {
        return false
    }

    return true
}