package com.colopreda.theforktest.presentation.restaurantpage

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import com.colopreda.theforktest.data.Ratings
import com.colopreda.theforktest.data.RestaurantModelsData
import com.colopreda.theforktest.data.YumsDetail
import com.colopreda.theforktest.domain.GetRestaurantUseCase
import com.colopreda.theforktest.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

@HiltViewModel
class RestaurantPageViewModel @Inject constructor(
    private val getRestaurantUseCase: GetRestaurantUseCase
) : ViewModel() {

    fun getRestaurantResponse() = flow {
        emit(State.LoadingState)
        try {
            emit(State.DataState(getRestaurantUseCase()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(State.ErrorState(NetworkErrorException("connection error!")))
        }
    }

    fun getRating(ratings: Ratings): String {
        return "${ratings.globalRate.toFloat()/10}"
    }

    fun acceptsYums(yumsDetail: YumsDetail): Boolean {
        return yumsDetail.isSuperYums
    }

    fun parseAddressLine1(data: RestaurantModelsData): String {
        return data.address
    }

    fun parseAddressLine2(data: RestaurantModelsData): String {
        return "${data.city}, ${data.country}"
    }

    fun parseStarters(cardStart1: String, cardStart2: String, cardStart3: String): String {
        return "- $cardStart1 \n- $cardStart2 \n- $cardStart3"
    }

    fun parseMains(cardMain1: String, cardMain2: String, cardMain3: String): String {
        return "- $cardMain1 \n- $cardMain2 \n- $cardMain3"
    }

    fun parseDesserts(cardDessert1: String, cardDessert2: String, cardDessert3: String): String {
        return "- $cardDessert1 \n- $cardDessert2 \n- $cardDessert3"
    }
}