package com.example.flowexample.ui
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowexample.data.TestFlowContRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repo = TestFlowContRepository()

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.NotStarted)
    val uiState: StateFlow<MainUiState> = _uiState

    fun exampleFlowStart() {
        viewModelScope.launch {
            repo.counter
                .catch { _uiState.value = MainUiState.Error(it.message.orEmpty()) }
                .flowOn(Dispatchers.Main)
                .collect {
                    _uiState.value = MainUiState.Success(it)
                    Log.i("flow", it.toString())
                }
        }
    }
}