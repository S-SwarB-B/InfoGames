package com.example.infogames.State

import kotlinx.serialization.Serializable

@Serializable
data class GamesDataClass(
     val name: String = "",
     val description: String = "",
     val categoryId: String = "",
     val image: String = "",
     val developer: String = "",
     val creator: String? = ""
)



