package com.acme.recipes.model

interface Recipe {

    val id: String
    val name: String
    val description: String
    val calories: Int

    val ingredients : List<Ingredient>?
}
