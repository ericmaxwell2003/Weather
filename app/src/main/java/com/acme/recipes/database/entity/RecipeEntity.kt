package com.acme.recipes.database.entity

import com.acme.recipes.model.Ingredient
import com.acme.recipes.model.Recipe
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class RecipeEntity() : RealmObject(), Recipe {

    @Required
    @PrimaryKey
    override var id: String = ""
    override var name: String = ""
    override var description: String = ""
    override var calories: Int = 0
    var ingredientEntities: RealmList<IngredientEntity> = RealmList()

    override val ingredients: List<Ingredient>?
        get() = ingredientEntities

    constructor(id: String, name: String, description: String, calories: Int) : this() {
        this.id = id
        this.name = name
        this.description = description
        this.calories = calories
    }

}