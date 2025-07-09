package com.menna.caffeine.ui.screen.take_away
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menna.caffeine.data_source.DummyDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init

class OrderViewModel(
    private val dummyDataSource: DummyDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    private val coffeeId: Int = 2

    init {
        loadInitialCoffee()
    }

    private fun loadInitialCoffee() {
        viewModelScope.launch {
            val coffee = dummyDataSource.getCoffeeList().find { it.id == coffeeId }
            _uiState.update { currentState ->
                currentState.copy(selectedCoffee = coffee)
            }
        }
    }

    fun onTakeAwayToggled() {
        _uiState.update { currentState ->
            currentState.copy(isTakeAway = !currentState.isTakeAway)
        }
    }

    fun onTakeSnackClicked() {
        println("Take Snack button clicked!")
    }

    fun onCloseClicked() {
        println("Close button clicked!")
    }
}
