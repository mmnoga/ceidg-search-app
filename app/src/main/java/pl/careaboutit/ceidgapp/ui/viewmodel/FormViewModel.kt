package pl.careaboutit.ceidgapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pl.careaboutit.ceidgapp.utils.Voivodeship

data class FormState(
    val fields: MutableMap<String, Any> = mutableMapOf()
)

class FormViewModel(initialState: FormState) : ViewModel() {
    var state: FormState by mutableStateOf(initialState)
        private set

    fun updateField(fieldName: String, value: Any) {
        val updatedValue = when {
            fieldName == "wojewodztwo" && value is Voivodeship -> value.displayName
            else -> value
        }
        state = state.copy(
            fields = state.fields.toMutableMap().apply { this[fieldName] = updatedValue })
    }

    fun getField(fieldName: String): Any? {
        return state.fields[fieldName]
    }

}