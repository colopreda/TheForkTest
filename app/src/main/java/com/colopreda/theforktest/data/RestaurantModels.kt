package com.colopreda.theforktest.data

import com.google.gson.annotations.SerializedName

data class RestaurantModels(
    val data: RestaurantModelsData
)

data class RestaurantModelsData(
    val picsMain: PicsMain = PicsMain(),
    val ratings: Ratings = Ratings(),
    val yumsDetail: YumsDetail = YumsDetail(),
    val name: String = "",
    val address: String = "",
    val city: String = "",
    val country: String = "",
    @SerializedName("public_phone")
    val phone: String = "",
    val speciality: String = "",
    val avgRate: Float = 0.0f,
    val description: String = "",
    val rateDistinction: String = "",
    val rateCount: String = "",
    val hourOpen: String = "",
    @SerializedName("card_start_1")
    val cardStart1: String = "",
    @SerializedName("card_start_2")
    val cardStart2: String = "",
    @SerializedName("card_start_3")
    val cardStart3: String = "",
    @SerializedName("card_main_1")
    val cardMain1: String = "",
    @SerializedName("card_main_2")
    val cardMain2: String = "",
    @SerializedName("card_main_3")
    val cardMain3: String = "",
    @SerializedName("card_dessert_1")
    val cardDessert1: String = "",
    @SerializedName("card_dessert_2")
    val cardDessert2: String = "",
    @SerializedName("card_dessert_3")
    val cardDessert3: String = "",
    val tripAdvisorAvgRating: Float = 0.0f,
    val tripAdvisorReviewCount: Int = 0
)

data class YumsDetail(
    val isSuperYums: Boolean = false,
    val yumsCount: Int = 0
)

data class Ratings(
    val globalRate: Int = 0,
    val foodRate: Int = 0,
    val serviceRate: Int = 0,
    val ambienceRate: Int = 0,
    val priceRate: Int = 0,
    val noiseRate: Int = 0,
    val waitingRate: Int = 0
)

data class PicsMain(
    @SerializedName("612x344")
    val large: String = ""
)
