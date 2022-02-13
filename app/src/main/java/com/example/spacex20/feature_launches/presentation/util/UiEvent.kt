package com.example.spacex20.feature_launches.presentation.util

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
}
