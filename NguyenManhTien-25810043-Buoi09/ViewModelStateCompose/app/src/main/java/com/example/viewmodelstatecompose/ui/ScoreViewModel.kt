package com.example.viewmodelstatecompose.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScoreViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ScoreUiState())
    val uiState: StateFlow<ScoreUiState> = _uiState.asStateFlow()

    fun addPoints(isBlueTeam: Boolean, points: Int) {
        _uiState.update { current ->
            if (isBlueTeam) {
                current.copy(blueScore = current.blueScore + points)
            } else {
                current.copy(orangeScore = current.orangeScore + points)
            }
        }
    }

    fun resetScore() {
        _uiState.value = ScoreUiState()
    }
}
