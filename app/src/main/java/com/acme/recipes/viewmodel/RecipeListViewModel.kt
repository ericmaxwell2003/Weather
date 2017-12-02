package com.acme.recipes.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.acme.recipes.database.dao.RecipeDao
import com.acme.recipes.model.Recipe
import com.acme.recipes.service.RecipeStreamingService
import io.realm.Realm

class RecipeListViewModel : ViewModel() {

    private val database = Realm.getDefaultInstance()
    private val dao = RecipeDao(database)
    private val recipeStreamingService = RecipeStreamingService()

    val recipes: LiveData<List<Recipe>>
    val recipeCount: LiveData<Int>

    init {
        recipes = Transformations.map(dao.findAllAsync()) {
            // Map from LiveData<List<RecipeEntity>> to LiveData<List<Recipe>>
            input -> input
        }

        recipeCount = Transformations.map(recipes) {
            // Map from LiveData<List<RecipeEntity>> to LiveData<Integer>
            input -> input.size
        }
    }

    fun startStreaming() {
        recipeStreamingService.startStreamingRecipes()
    }

    fun stopStreaming() {
        recipeStreamingService.stopStreamingRecipes()
    }

    fun deleteAllRecipes() {
        dao.deleteAll()
    }

    override fun onCleared() {
        database.close()
    }
}
