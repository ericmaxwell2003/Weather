package com.acme.recipes.database.entity

import com.acme.recipes.model.Ingredient
import io.realm.RealmObject
import java.util.*

open class IngredientEntity() : RealmObject(), Ingredient {

    override var name: String = ""
    override var quantity: Double = 0.0
    override var quantityType: String = ""

    constructor(name: String, quantity: Double, quantityType: String) : this() {
        this.name = name
        this.quantity = quantity
        this.quantityType = quantityType
    }

    override fun toString(): String {
        return String.format(Locale.US, "%.1f %s %s",
                quantity, quantityType, name)
    }
}