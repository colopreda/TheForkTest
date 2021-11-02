package com.colopreda.theforktest.domain

import com.colopreda.theforktest.presentation.restaurantpage.RestaurantPageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetRestaurantUseCase @Inject constructor(
    private val repository: RestaurantPageRepository
) {
    suspend operator fun invoke(): Flow<Restaurant> {
        return repository.getRestaurantData()
    }
}