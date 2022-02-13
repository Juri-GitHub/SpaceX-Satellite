package com.example.spacex20.feature_launches.presentation.launch_list

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacex20.feature_launches.core.util.Resource
import com.example.spacex20.feature_launches.domain.use_case.get_launches.GetLaunchesUseCase
import com.example.spacex20.feature_launches.domain.utils.makeNotification
import com.example.spacex20.feature_launches.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
    private val context: Context,
    private val getLaunchesUseCase: GetLaunchesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(LaunchListState())
    val state: State<LaunchListState> = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        makeNotification("Syncing with network", context.applicationContext)
        getLaunches()
    }

    private fun getLaunches() {
        getLaunchesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = LaunchListState(
                        launches = result.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _state.value = LaunchListState(
                        launches = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = LaunchListState(
                        error = result.message ?: "Something went wrong",
                        launches = result.data ?: emptyList()
                    )
                    viewModelScope.launch {
                        sendUiEvent(UiEvent.ShowSnackBar(
                            message = "No internet connection"
                        ))
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}