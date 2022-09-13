package com.ani.testing.batch

import java.util.regex.Pattern

object Validator {

    fun validateEmail(eml : String) : Boolean {

//        val pattern = """
//            [a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+
//        """.trimIndent()

        return when {
            eml.isEmpty() -> false
            eml.isBlank() -> false
            else -> eml.length > 3
        }
    }

    fun validatePassword(pas : String) = pas.length > 3
}