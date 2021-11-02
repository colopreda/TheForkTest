package com.colopreda.theforktest.presentation.restaurantpage

import app.cash.turbine.test
import com.colopreda.theforktest.data.RestaurantModels
import com.colopreda.theforktest.data.RestaurantModelsData
import com.colopreda.theforktest.domain.GetRestaurantUseCase
import com.colopreda.theforktest.presentation.State
import com.colopreda.theforktest.utils.MainCoroutineScopeRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RestaurantPageViewModelTest {

    private lateinit var viewModel: RestaurantPageViewModel

    private val getRestaurantUseCase : GetRestaurantUseCase = mockk()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineScope = MainCoroutineScopeRule()

    @Before
    fun setup() {
        viewModel = RestaurantPageViewModel(getRestaurantUseCase)
    }

    @Test
    fun `flow emits success`() = runBlocking {
        val mockResponse = RestaurantModels(RestaurantModelsData())
        val mockRestaurant = mockResponse.data.toRestaurant()
        coEvery { getRestaurantUseCase() } returns flow {
            emit(mockRestaurant)
        }

        viewModel.getRestaurant()

        viewModel.uiState.test {
            assertEquals(awaitItem(), State.DataState(mockRestaurant))
        }
    }

    @Test
    fun `flow emits failure`() = runBlocking {
        val runtimeException = RuntimeException()
        coEvery { getRestaurantUseCase() } returns flow { throw runtimeException }

        viewModel.getRestaurant()

        viewModel.uiState.test {
            assertEquals(awaitItem(), State.ErrorState(runtimeException))
        }
    }

}