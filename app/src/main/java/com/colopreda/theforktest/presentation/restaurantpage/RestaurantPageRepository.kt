package com.colopreda.theforktest.presentation.restaurantpage

import com.colopreda.theforktest.data.TheForkAPI
import com.colopreda.theforktest.domain.Restaurant
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@Singleton
class RestaurantPageRepository @Inject constructor(
    private val theForkAPI: TheForkAPI
    ) {
    suspend fun getRestaurantData(): Flow<Restaurant> {
        return flow {
            emit(theForkAPI.getRestaurantData().data.toRestaurant())
        }.flowOn(Dispatchers.IO)
    }
}