package com.example.myapplication

object TextValidator {

    fun validateEmail(email: String): Boolean {
        if (email.isEmpty() && !email.contains("@") && !email.contains(".")) return false

        val parse1 = email.split("@")
        if (parse1.size != 2) return false
        val parse2 = parse1[1].split(".")
        println(parse2)
        if (parse2.size != 2) return false

        if (email != email.lowercase() || parse2[1].length < 2) return false

        return true
    }
}