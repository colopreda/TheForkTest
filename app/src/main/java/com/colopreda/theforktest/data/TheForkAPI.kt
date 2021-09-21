package com.colopreda.theforktest.data

import com.google.gson.JsonObject
import retrofit2.http.GET

interface TheForkAPI {
    @GET("api?%20key=IPHONEPRODEDCRFV&method=restaurant_get_info&id_restaurant=6861")
    suspend fun getRestaurantData(): RestaurantModels
}