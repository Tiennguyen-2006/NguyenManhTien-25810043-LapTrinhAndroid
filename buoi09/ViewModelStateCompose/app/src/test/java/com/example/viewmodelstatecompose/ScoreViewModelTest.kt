package com.example.viewmodelstatecompose

import com.example.viewmodelstatecompose.ui.ScoreViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreViewModelTest {
    @Test
    fun addPointsUpdatesBothTeams() {
        val viewModel = ScoreViewModel()

        viewModel.addPoints(isBlueTeam = true, points = 2)
        viewModel.addPoints(isBlueTeam = false, points = 1)

        assertEquals(2, viewModel.uiState.value.blueScore)
        assertEquals(1, viewModel.uiState.value.orangeScore)
    }

    @Test
    fun resetScoreReturnsToZero() {
        val viewModel = ScoreViewModel()
        viewModel.addPoints(isBlueTeam = true, points = 2)

        viewModel.resetScore()

        assertEquals(0, viewModel.uiState.value.blueScore)
        assertEquals(0, viewModel.uiState.value.orangeScore)
    }
}
