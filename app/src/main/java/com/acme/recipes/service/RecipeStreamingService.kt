package com.acme.recipes.service

import android.support.annotation.WorkerThread
import com.acme.recipes.database.dao.RecipeDao
import com.acme.recipes.database.entity.IngredientEntity
import com.acme.recipes.database.entity.RecipeEntity
import io.realm.Realm
import java.util.*

class RecipeStreamingService {

    private val random = Random(System.currentTimeMillis())
    private var timer: Timer? = null

    private val adjectives = arrayOf("Tasty", "Yummy", "Delicious")
    private val deserts = arrayOf("Baked Alaska", "Baked Apple", "Baklava", "Banana Split",
            "Belgian Waffle", "Biscotti", "Black Forest Cake", "Blueberry Muffin",
            "Boston Cream Pie", "Bread Pudding", "Brownie", "Buttercream Frosting", "Butterscotch")

    fun startStreamingRecipes() {
        startTimer(EMIT_EVERY_NUM_SECONDS)
    }

    fun stopStreamingRecipes() {
        cancelTimer()
    }

    private fun cancelTimer() {
        timer?.cancel()
        timer?.purge()
        timer = null
    }

    private fun startTimer(emitEveryNumSeconds: Int) {

        cancelTimer()

        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                generateNewRecipe()
            }
        }, 500, (emitEveryNumSeconds * 1000).toLong())

    }

    @WorkerThread
    private fun generateNewRecipe() {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()

        val recipeDao = RecipeDao(realm)

        val id = UUID.randomUUID().toString()
        val dessertName = deserts[random.nextInt(deserts.size - 1)]
        val dessertDesc = adjectives[random.nextInt(adjectives.size - 1)] + " " + dessertName
        val calorieCount = random.nextInt(1000)

        val recipeEntity = recipeDao.copyOrUpdate(
                RecipeEntity(id, dessertName, dessertDesc, calorieCount)
        )

        recipeEntity.ingredientEntities.add(
                IngredientEntity(dessertName, 1.0, "Store Bought")
        )

        realm.commitTransaction()
    }

    companion object {
        private val EMIT_EVERY_NUM_SECONDS = 1
    }
}
