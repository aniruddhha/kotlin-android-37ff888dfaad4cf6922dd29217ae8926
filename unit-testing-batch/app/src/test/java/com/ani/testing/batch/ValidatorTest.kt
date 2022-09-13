package com.ani.testing.batch

import com.google.common.truth.Truth.*
import org.junit.Test

class ValidatorTest {

    @Test
    fun `test return type`() {
        val actual = Validator.validateEmail("abc")
        assertThat(actual).isIn(arrayListOf(true, false))
    }

    @Test
    fun `test email not empty`() {
        val actual =  Validator.validateEmail("")
        assertThat(actual).isFalse()
    }

    @Test
    fun `test not only white spaces`() {
        val actual =  Validator.validateEmail("        ")
        assertThat(actual).isFalse()
    }

    @Test
    fun `test for invalid pattern`() {
        val actual1 =  Validator.validateEmail("aaa@")
        assertThat(actual1).isFalse()

        val actual2 =  Validator.validateEmail("aaa.com")
        assertThat(actual2).isFalse()

        val actual3 =  Validator.validateEmail("aaa")
        assertThat(actual3).isFalse()

        val actual4 =  Validator.validateEmail("aa    a@dd.com")
        assertThat(actual4).isFalse()
    }
}