package com.example.myapplication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Shoe (

//    @SerialName("UID")
//    val uid: String,
//
//    @SerialName("created_At")
//    val date: String,

    val name: String,

    val price: Int,
)