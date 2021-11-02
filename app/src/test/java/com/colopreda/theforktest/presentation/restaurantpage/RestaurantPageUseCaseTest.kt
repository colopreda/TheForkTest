package com.colopreda.theforktest.presentation.restaurantpage

import com.colopreda.theforktest.data.RestaurantModelsData
import com.colopreda.theforktest.domain.GetRestaurantUseCase
import com.colopreda.theforktest.utils.MainCoroutineScopeRule
import com.colopreda.theforktest.utils.runBlocking
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class RestaurantPageUseCaseTest {

    private val repository: RestaurantPageRepository = mockk()
    private val getRestaurantUseCase = GetRestaurantUseCase(repository)

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineScope = MainCoroutineScopeRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `when call get, return restaurant`() = coroutineScope.runBlocking {
        val mockRestaurant = RestaurantModelsData().toRestaurant()
        val mockResponse = flow { emit(mockRestaurant) }
        coEvery { repository.getRestaurantData() } returns mockResponse

        val useCaseResponse = getRestaurantUseCase()

        Assert.assertNotNull(mockRestaurant)
        Assert.assertEquals(useCaseResponse, mockResponse)
    }
}