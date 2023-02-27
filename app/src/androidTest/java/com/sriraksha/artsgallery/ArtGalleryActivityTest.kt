package com.sriraksha.artsgallery

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.sriraksha.artsgallery.presentation.ui.ArtsGalleryActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArtGalleryActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(ArtsGalleryActivity::class.java)

    @Test
    fun isProgressBarDisplayed() {
        activityRule.activity.showProgressBar()
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun isProgressBarHidden() {
        activityRule.activity.hideProgressBar()
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }
}
