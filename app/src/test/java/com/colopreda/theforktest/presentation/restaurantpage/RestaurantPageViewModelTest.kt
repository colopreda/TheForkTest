package com.colopreda.theforktest.presentation.restaurantpage

import com.colopreda.theforktest.data.Ratings
import com.colopreda.theforktest.data.RestaurantModelsData
import com.colopreda.theforktest.data.YumsDetail
import com.colopreda.theforktest.domain.GetRestaurantUseCase
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RestaurantPageViewModelTest {

    private lateinit var viewModel: RestaurantPageViewModel

    private val getRestaurantUseCase = GetRestaurantUseCase(mockk())

    private fun getMockRestaurantData(): RestaurantModelsData {
        return RestaurantModelsData(
            address = "123 fake address",
            city = "fake city",
            country = "fake country"
        )
    }

    @Before
    fun setup() {
        viewModel = RestaurantPageViewModel(getRestaurantUseCase)
    }

    @Test
    fun checkRatingOk() {
        val rating = Ratings(91,91,91,91,90,88,70)
        assertEquals(viewModel.getRating(rating), "9.1")
    }

    @Test
    fun testAcceptsYums() {
        val isYums = YumsDetail(true, 15)
        assertEquals(viewModel.acceptsYums(isYums), true)
    }

    @Test
    fun testDoesNotAcceptYums() {
        val isYums = YumsDetail(false, 15)
        assertEquals(viewModel.acceptsYums(isYums), false)
    }

    @Test
    fun testParseAddressLine1() {
        val restaurantModelsData = getMockRestaurantData()
        assertEquals(viewModel.parseAddressLine1(restaurantModelsData), "123 fake address")
    }

    @Test
    fun testParseAddressLine2() {
        val restaurantModelsData = getMockRestaurantData()
        assertEquals(viewModel.parseAddressLine2(restaurantModelsData), "fake city, fake country")
    }

    @Test
    fun testParseStarters() {
        val starter1 = "starter1"
        val starter2 = "starter2"
        val starter3 = "starter3"
        assertEquals(viewModel.parseStarters(starter1, starter2, starter3), "- starter1 \n- starter2 \n- starter3")
    }

    @Test
    fun testParseMains() {
        val main1 = "main1"
        val main2 = "main2"
        val main3 = "main3"
        assertEquals(viewModel.parseStarters(main1, main2, main3), "- main1 \n- main2 \n- main3")
    }

    @Test
    fun testParseDesserts() {
        val dessert1 = "dessert1"
        val dessert2 = "dessert2"
        val dessert3 = "dessert3"
        assertEquals(viewModel.parseStarters(dessert1, dessert2, dessert3), "- dessert1 \n- dessert2 \n- dessert3")
    }
}