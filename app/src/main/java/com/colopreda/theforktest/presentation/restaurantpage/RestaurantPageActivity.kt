package com.colopreda.theforktest.presentation.restaurantpage

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.colopreda.theforktest.R
import com.colopreda.theforktest.data.RestaurantModels
import com.colopreda.theforktest.presentation.State
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.accepts_yums_tv
import kotlinx.android.synthetic.main.activity_main.actual_rating_tv
import kotlinx.android.synthetic.main.activity_main.address_line_1_tv
import kotlinx.android.synthetic.main.activity_main.address_line_2_tv
import kotlinx.android.synthetic.main.activity_main.avg_rate_tv
import kotlinx.android.synthetic.main.activity_main.description_cv
import kotlinx.android.synthetic.main.activity_main.description_tv
import kotlinx.android.synthetic.main.activity_main.desserts_content_tv
import kotlinx.android.synthetic.main.activity_main.full_screen_progress
import kotlinx.android.synthetic.main.activity_main.location_cv
import kotlinx.android.synthetic.main.activity_main.location_group
import kotlinx.android.synthetic.main.activity_main.mains_content_tv
import kotlinx.android.synthetic.main.activity_main.menu_cv
import kotlinx.android.synthetic.main.activity_main.menu_group
import kotlinx.android.synthetic.main.activity_main.phone_tv
import kotlinx.android.synthetic.main.activity_main.rate_distinction_tv
import kotlinx.android.synthetic.main.activity_main.rating_ll
import kotlinx.android.synthetic.main.activity_main.rating_tv
import kotlinx.android.synthetic.main.activity_main.restaurant_image_iv
import kotlinx.android.synthetic.main.activity_main.restaurant_name_tv
import kotlinx.android.synthetic.main.activity_main.scroll_view_content
import kotlinx.android.synthetic.main.activity_main.specialty_tv
import kotlinx.android.synthetic.main.activity_main.starters_content_tv
import kotlinx.android.synthetic.main.activity_main.total_reviews_tv
import kotlinx.android.synthetic.main.activity_main.total_ta_review_tv
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
        setCollapsingDescription()
        setCollapsingLocation()
        setCollapsingMenu()
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

    private fun setCollapsingMenu() {
        menu_cv.setOnClickListener {
            menu_group.visibility =
                if (menu_group.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }

    private fun setCollapsingLocation() {
        location_cv.setOnClickListener {
            location_group.visibility =
                if (location_group.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }

    private fun setCollapsingDescription() {
        description_cv.setOnClickListener {
            description_tv.visibility =
                if (description_tv.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }

    private fun loadData(data: RestaurantModels) {
        setLoadedState()
        Picasso.get().load(data.data.picsMain.large).centerCrop().fit().into(restaurant_image_iv)
        restaurant_name_tv.text = data.data.name
        specialty_tv.text = data.data.speciality
        actual_rating_tv.text = viewModel.getRating(data.data.ratings)
        accepts_yums_tv.text =
            if (viewModel.acceptsYums(data.data.yumsDetail)) {
                getString(R.string.accepts_yums, getString(R.string.accepts))
            } else {
                getString(R.string.accepts_yums, getString(R.string.does_not_accepts))
            }
        description_tv.text = data.data.description
        address_line_1_tv.text = viewModel.parseAddressLine1(data.data)
        address_line_2_tv.text = viewModel.parseAddressLine2(data.data)
        phone_tv.text = data.data.phone
        starters_content_tv.text = viewModel.parseStarters(
            data.data.cardStart1,
            data.data.cardStart2,
            data.data.cardStart3
        )
        mains_content_tv.text =
            viewModel.parseMains(data.data.cardMain1, data.data.cardMain2, data.data.cardMain3)
        desserts_content_tv.text = viewModel.parseDesserts(
            data.data.cardDessert1,
            data.data.cardDessert2,
            data.data.cardDessert3
        )
        avg_rate_tv.text = data.data.avgRate.toString()
        rate_distinction_tv.text = data.data.rateDistinction
        total_reviews_tv.text = getString(R.string.based_on_x_reviews, data.data.rateCount)
        total_ta_review_tv.text =
            getString(R.string.number_reviews, data.data.tripAdvisorReviewCount)
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