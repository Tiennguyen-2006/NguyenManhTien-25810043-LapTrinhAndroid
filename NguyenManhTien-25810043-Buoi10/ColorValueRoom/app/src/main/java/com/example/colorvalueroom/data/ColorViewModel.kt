package com.example.colorvalueroom.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ColorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ColorRepository(
        ColorDatabase.getDatabase(application).colorDao()
    )

    val colors: StateFlow<List<ColorItem>> = repository.colors.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch {
            repository.addStarterColors()
        }
    }

    fun addColor(name: String, hex: String) {
        val cleanName = name.trim()
        val cleanHex = normalizeHex(hex) ?: return
        if (cleanName.isEmpty()) return

        viewModelScope.launch {
            repository.save(ColorItem(name = cleanName, hex = cleanHex))
        }
    }

    fun deleteColor(color: ColorItem) {
        viewModelScope.launch {
            repository.remove(color)
        }
    }

    private fun normalizeHex(value: String): String? {
        val cleanValue = value.trim().uppercase()
        return if (Regex("#[0-9A-F]{6}").matches(cleanValue)) cleanValue else null
    }
}
