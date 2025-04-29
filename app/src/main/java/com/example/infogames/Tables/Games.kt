package com.example.infogames.Tables

import kotlinx.serialization.Serializable

@Serializable
data class Games(
    val id: String,
    val name: String,
    val description: String,
    val categoryId: String,
    val image: String,
    val developer: String,
    val creator: String
)
