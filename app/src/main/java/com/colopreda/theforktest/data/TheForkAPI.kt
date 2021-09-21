package com.colopreda.theforktest.data

import com.colopreda.theforktest.BuildConfig
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query

interface TheForkAPI {
    @GET("api")
    suspend fun getRestaurantData(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("method") method: String = "restaurant_get_info",
        @Query("id_restaurant") restaurantId: String = "6861"
    ): RestaurantModels
}