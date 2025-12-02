package com.example.pasteleriayy.model

data class RecetaResponse(
    val meals: List<Receta>?
)

data class Receta(
    val strMeal: String,
    val strInstructions: String?,
    val strMealThumb: String?
)
