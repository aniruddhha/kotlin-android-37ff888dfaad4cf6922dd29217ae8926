package com.ani.testing.batch

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    lateinit var scenario: FragmentScenario<LoginFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun testFragmentNotNull() {
        with(scenario) {
            onFragment {
                Assert.assertNotNull((it))
            }
        }
    }

    @Test
    fun testInvalidEmail() {

       onView(
           withId(R.id.etEm)
       ).perform(
           typeText("abc")
       )

        onView(
            withId(R.id.btLgn)
        ).perform(
            click()
        )

        onView(
            withId(R.id.txtSts)
        ).check(
            matches(
                withText("fail")
            )
        )
    }
}