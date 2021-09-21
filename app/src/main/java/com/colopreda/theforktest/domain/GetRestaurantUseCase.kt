package com.colopreda.theforktest.domain

import com.colopreda.theforktest.data.RestaurantModels
import com.colopreda.theforktest.data.TheForkAPI
import com.google.gson.JsonObject
import javax.inject.Inject

class GetRestaurantUseCase @Inject constructor(
    private val theForkAPI: TheForkAPI
) {
    suspend operator fun invoke(): RestaurantModels {
        return theForkAPI.getRestaurantData()
    }
}