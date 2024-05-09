package pl.careaboutit.ceidgapp.utils

object Utils {
    fun isNipValid(nip: String): Boolean {
        if (nip.isBlank()) {
            return false
        }

        val digits = nip
            .replace(" ", "")
            .replace("-", "")
            .toCharArray()

        if (digits.size != 10) {
            return false
        }

        var sum = 0
        val weights = intArrayOf(6, 5, 7, 2, 3, 4, 5, 6, 7)
        for (i in 0 until 9) {
            sum += Character.getNumericValue(digits[i]) * weights[i]
        }

        val controlNumber = sum % 11
        val expectedControlNumber = Character.getNumericValue(digits[9])

        return controlNumber == expectedControlNumber
    }

}