package com.colopreda.theforktest.domain

data class Restaurant(
    val mainPic: String,
    val avgRate: String,
    val isYums: Boolean,
    val name: String,
    val address: String,
    val city: String,
    val country: String,
    val phone: String,
    val speciality: String,
    val avgRating: Float,
    val description: String,
    val rateDistinction: String?,
    val rateCount: String,
    val hourOpen: String,
    val tripAdvisorAvgRating: Float,
    val tripAdvisorReviewCount: Int,
    val cardStart1: String? = null,
    val cardStart2: String? = null,
    val cardStart3: String? = null,
    val cardMain1: String? = null,
    val cardMain2: String? = null,
    val cardMain3: String? = null,
    val cardDessert1: String? = null,
    val cardDessert2: String? = null,
    val cardDessert3: String? = null,
    val priceCardStart1: Float? = null,
    val priceCardStart2: Float? = null,
    val priceCardStart3: Float? = null,
    val priceCardMain1: Float? = null,
    val priceCardMain2: Float? = null,
    val priceCardMain3: Float? = null,
    val priceCardDessert1: Float? = null,
    val priceCardDessert2: Float? = null,
    val priceCardDessert3: Float? = null,
) {
    val menu: List<ItemWithPrice> by lazy {
        val mutableMenus = mutableListOf<ItemWithPrice>()

        fun checkNullability(menuItem: String?, price: Float?) {
            if (menuItem.isNullOrBlank().not().and(price != null)) {
                mutableMenus.add(ItemWithPrice(menuItem!!, price!!))
            }
        }

        checkNullability(cardMain1, priceCardMain1)
        checkNullability(cardMain2, priceCardMain2)
        checkNullability(cardMain3, priceCardMain3)

        checkNullability(cardStart1, priceCardStart1)
        checkNullability(cardStart2, priceCardStart2)
        checkNullability(cardStart3, priceCardStart3)

        checkNullability(cardDessert1, priceCardDessert1)
        checkNullability(cardDessert2, priceCardDessert2)
        checkNullability(cardDessert3, priceCardDessert3)

        mutableMenus
    }

    val addressLine1 = address
    val addressLine2 = "${city}, $country"

    data class ItemWithPrice(val title: String, val price: Float)
}
