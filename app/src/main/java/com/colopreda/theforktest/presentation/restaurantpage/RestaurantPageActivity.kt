package com.colopreda.theforktest.presentation.restaurantpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.colopreda.theforktest.R
import com.colopreda.theforktest.domain.Restaurant
import com.colopreda.theforktest.presentation.State
import com.colopreda.theforktest.utils.toFormattedEuro
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.accepts_yums_tv
import kotlinx.android.synthetic.main.activity_main.actual_rating_tv
import kotlinx.android.synthetic.main.activity_main.address_line_1_tv
import kotlinx.android.synthetic.main.activity_main.address_line_2_tv
import kotlinx.android.synthetic.main.activity_main.avg_rate_tv
import kotlinx.android.synthetic.main.activity_main.description_tv
import kotlinx.android.synthetic.main.activity_main.full_screen_progress
import kotlinx.android.synthetic.main.activity_main.menu_elements_ll
import kotlinx.android.synthetic.main.activity_main.phone_tv
import kotlinx.android.synthetic.main.activity_main.rate_distinction_tv
import kotlinx.android.synthetic.main.activity_main.rating_ll
import kotlinx.android.synthetic.main.activity_main.restaurant_image_iv
import kotlinx.android.synthetic.main.activity_main.restaurant_name_tv
import kotlinx.android.synthetic.main.activity_main.scroll_view_content
import kotlinx.android.synthetic.main.activity_main.specialty_tv
import kotlinx.android.synthetic.main.activity_main.total_reviews_tv
import kotlinx.android.synthetic.main.activity_main.total_ta_review_tv
import kotlinx.android.synthetic.main.menu_item.view.menu_item
import kotlinx.android.synthetic.main.menu_item.view.menu_price
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantPageActivity : AppCompatActivity() {
    private val viewModel: RestaurantPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadRestaurantData()
        hideToolbar()
    }

    private fun loadRestaurantData() {
        lifecycleScope.launch {
            viewModel.getRestaurantResponse().collect { restaurantModelsState ->
                when (restaurantModelsState) {
                    is State.DataState -> loadData(restaurantModelsState.data)
                    is State.LoadingState -> setLoadingState()
                    is State.ErrorState -> showErrorSnackbar()

                }
            }
        }
    }

    private fun showErrorSnackbar() {
        val mySnackbar = Snackbar.make(findViewById(R.id.scroll_view_content),
            R.string.there_was_an_error, Snackbar.LENGTH_INDEFINITE)
        mySnackbar.setAction(R.string.retry) {
            loadRestaurantData()
        }
        mySnackbar.show()
    }

    private fun setLoadingState() {
        full_screen_progress.visibility = View.VISIBLE
        scroll_view_content.visibility = View.GONE
    }

    private fun setLoadedState() {
        full_screen_progress.visibility = View.GONE
        scroll_view_content.visibility = View.VISIBLE
    }

    private fun hideToolbar() {
        supportActionBar?.hide()
    }

    private fun loadData(data: Restaurant) {
        setLoadedState()
        Picasso.get().load(data.mainPic).centerCrop().fit().into(restaurant_image_iv)
        restaurant_name_tv.text = data.name
        specialty_tv.text = data.speciality
        actual_rating_tv.text = data.avgRate
        accepts_yums_tv.text =
            if (data.isYums) {
                getString(R.string.accepts_yums, getString(R.string.accepts))
            } else {
                getString(R.string.accepts_yums, getString(R.string.does_not_accepts))
            }
        description_tv.text = data.description
        address_line_1_tv.text = data.addressLine1
        address_line_2_tv.text = data.addressLine2
        phone_tv.text = data.phone

        for (item in data.menu) {
            val menuView: ConstraintLayout =
                LayoutInflater.from(this).inflate(R.layout.menu_item, null) as ConstraintLayout
            menuView.menu_item.text = item.title
            menuView.menu_price.text = item.price.toFormattedEuro()
            menu_elements_ll.addView(menuView)
        }

        avg_rate_tv.text = data.avgRate
        rate_distinction_tv.text = data.rateDistinction
        total_reviews_tv.text = getString(R.string.based_on_x_reviews, data.rateCount)
        total_ta_review_tv.text =
            getString(R.string.number_reviews, data.tripAdvisorReviewCount)
        loadTripAdvisorRatings(3.50f)
    }

    private fun loadTripAdvisorRatings(tripAdvisorAvgRating: Float) {
        for (i in 1..tripAdvisorAvgRating.toInt()) {
            // Draw full circles
            rating_ll.addView(
                ImageView(applicationContext).apply {
                    setBackgroundResource(R.drawable.ta_bubbles_full)
                }
            )
        }
        val remainingInt = ((tripAdvisorAvgRating - tripAdvisorAvgRating.toInt()) * 100).toInt()
        when {
            (0..25).contains(remainingInt) -> {
                // Draw empty circle (round down)
                rating_ll.addView(
                    ImageView(applicationContext).apply {
                        setBackgroundResource(R.drawable.ta_bubbles_empty)
                    }
                )
            }
            (26..74).contains(remainingInt) -> {
                // Add half rating
                rating_ll.addView(
                    ImageView(applicationContext).apply {
                        setBackgroundResource(R.drawable.ta_bubbles_half)
                    }
                )
            }
            else -> {
                // Add full circle (round up)
                rating_ll.addView(
                    ImageView(applicationContext).apply {
                        setBackgroundResource(R.drawable.ta_bubbles_full)
                    }
                )
            }
        }
        for (j in tripAdvisorAvgRating.toInt()+1 until 5) {
            // Add empty circles
            rating_ll.addView(
                ImageView(applicationContext).apply {
                    setBackgroundResource(R.drawable.ta_bubbles_empty)
                }
            )
        }
    }

}