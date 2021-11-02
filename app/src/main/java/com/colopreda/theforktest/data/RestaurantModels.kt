package com.colopreda.theforktest.data

import com.colopreda.theforktest.domain.Restaurant
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
    val rateDistinction: String? = "",
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
    @SerializedName("price_card_start_1")
    val priceCardStart1: Float? = 0.0f,
    @SerializedName("price_card_start_2")
    val priceCardStart2: Float? = 0.0f,
    @SerializedName("price_card_start_3")
    val priceCardStart3: Float? = 0.0f,
    @SerializedName("price_card_main_1")
    val priceCardMain1: Float? = 0.0f,
    @SerializedName("price_card_main_2")
    val priceCardMain2: Float? = 0.0f,
    @SerializedName("price_card_main_3")
    val priceCardMain3: Float? = 0.0f,
    @SerializedName("price_card_dessert_1")
    val priceCardDessert1: Float? = 0.0f,
    @SerializedName("price_card_dessert_2")
    val priceCardDessert2: Float? = 0.0f,
    @SerializedName("price_card_dessert_3")
    val priceCardDessert3: Float? = 0.0f,
    val tripAdvisorAvgRating: Float = 0.0f,
    val tripAdvisorReviewCount: Int = 0
) {
    fun toRestaurant() = Restaurant(
        mainPic = picsMain.large,
        avgRate = (ratings.globalRate.toFloat() / 10).toString(),
        isYums = yumsDetail.isSuperYums,
        name = name,
        address = address,
        city = city,
        country = country,
        phone = phone,
        speciality = speciality,
        avgRating = avgRate,
        description = description,
        rateDistinction = rateDistinction,
        rateCount = rateCount,
        hourOpen = hourOpen,
        tripAdvisorAvgRating = tripAdvisorAvgRating,
        tripAdvisorReviewCount = tripAdvisorReviewCount,
        cardStart1 = cardStart1,
        cardStart2 = cardStart2,
        cardStart3 = cardStart3,
        cardMain1 = cardMain1,
        cardMain2 = cardMain2,
        cardMain3 = cardMain3,
        cardDessert1 = cardDessert1,
        cardDessert2 = cardDessert2,
        cardDessert3 = cardDessert3,
        priceCardStart1 = priceCardStart1,
        priceCardStart2 = priceCardStart2,
        priceCardStart3 = priceCardStart3,
        priceCardMain1 = priceCardMain1,
        priceCardMain2 = priceCardMain2,
        priceCardMain3 = priceCardMain3,
        priceCardDessert1 = priceCardDessert1,
        priceCardDessert2 = priceCardDessert2,
        priceCardDessert3 = priceCardDessert3,
    )
}

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
