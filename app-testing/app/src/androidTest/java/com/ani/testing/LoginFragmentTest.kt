package com.ani.testing

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    lateinit var scenario : FragmentScenario<LoginFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testFragmentNotNull() {
         with(scenario){
            onFragment {
                Assert.assertNotNull(it)
            }
        }
    }

    @Test
    fun testState() {
        onView(withId(R.id.etEm)).perform(typeText("abc"))
        onView(withId(R.id.etPs)).perform(typeText("abc"))
        onView(withId(R.id.btLg)).perform(click())
        onView(withId(R.id.txtSts)).check(matches(withText("success")))
    }
}