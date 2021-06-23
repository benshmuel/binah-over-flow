package com.example.binahoverflow.ui.activity


import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches


import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.binahoverflow.R
import com.example.binahoverflow.adapters.BinahAdapter
import com.example.binahoverflow.data.QuestionAdapterItem.Companion.ANSWERED
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class BinahOverFlowActivityTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(BinahOverFlowActivity::class.java)


    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {

        val appCompatToggleButton = onView(
            allOf(
                withId(R.id.filterToggle),
                withText("not answered"),
                isDisplayed()
            )
        )


        appCompatToggleButton.perform(click())

        onView(withId(R.id.binahRecycler))
            .perform(RecyclerViewActions.scrollToHolder(IsItemAnswered()));


        onView(withText(ANSWERED)).check(matches(isDisplayed()));

    }

    private fun IsItemAnswered(): Matcher<BinahAdapter.QuestionViewHolder?> {
        return object : TypeSafeMatcher<BinahAdapter.QuestionViewHolder>() {
            override fun matchesSafely(customHolder: BinahAdapter.QuestionViewHolder): Boolean {
                return customHolder.itemView.findViewById<TextView>(R.id.isAnswered).text == ANSWERED
            }

            override fun describeTo(description: Description) {
                description.appendText("this question is answered.")
            }
        }
    }
}