package com.acme.recipes.database.util

import com.acme.recipes.database.dao.RecipeDao
import com.acme.recipes.database.entity.IngredientEntity
import com.acme.recipes.database.entity.RecipeEntity
import io.realm.Realm
import java.util.*

class DatabaseInitTransaction : Realm.Transaction {

    override fun execute(db: Realm) {

        val recipeDao = RecipeDao(db)

        db.deleteAll()

        val eclair = recipeDao.copyOrUpdate(
                RecipeEntity(UUID.randomUUID().toString(),
                        "Eclair",
                        "Pastry filled with cream",
                        800)
        )
        eclair.ingredientEntities.addAll(
                listOf(
                        IngredientEntity("Sugar", 1.0, "cup"),
                        IngredientEntity("Flour", 1.5, "cup"),
                        IngredientEntity("Cocoa", 1.0, "tbsp"),
                        IngredientEntity("Milk", 1.0, "quart")
                )
        )

        val oreo = recipeDao.copyOrUpdate(
                RecipeEntity(UUID.randomUUID().toString(),
                        "Oreo",
                        "Chocolate cookie",
                        650)
        )
        oreo.ingredientEntities.addAll(
                listOf(
                        IngredientEntity("Sugar", 1.0, "cup"),
                        IngredientEntity("Flour", 2.0, "cup")
                )
        )

    }

}
