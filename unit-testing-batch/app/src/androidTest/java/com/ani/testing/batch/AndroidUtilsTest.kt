package com.ani.testing.batch

import org.junit.Assert
import org.junit.Test

class AndroidUtilsTest {

    @Test
    fun testIfReturnNull() {
        val inp = mapOf<String?, String>(
            "A" to "1",
            null to "2"
        )
        val actual = AndroidUtils.mapToBundle(inp)
        Assert.assertNull(actual)
    }
}