package com.colopreda.theforktest.presentation.restaurantpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colopreda.theforktest.domain.GetRestaurantUseCase
import com.colopreda.theforktest.domain.Restaurant
import com.colopreda.theforktest.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class RestaurantPageViewModel @Inject constructor(
    private val getRestaurantUseCase: GetRestaurantUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<State<Restaurant>>(State.LoadingState)

    val uiState: StateFlow<State<Restaurant>> = _uiState

    fun getRestaurant() {
        viewModelScope.launch {
            getRestaurantUseCase()
                .catch { error ->
                    _uiState.value = State.ErrorState(error)
                }
                .collect { restaurant ->
                    _uiState.value = State.DataState(restaurant)
                }
        }
    }
}