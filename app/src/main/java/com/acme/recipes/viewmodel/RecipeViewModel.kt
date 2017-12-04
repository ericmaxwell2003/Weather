package com.acme.recipes.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.acme.recipes.database.dao.RecipeDao
import com.acme.recipes.database.util.recipeDao
import com.acme.recipes.model.Recipe
import io.realm.Realm

class RecipeViewModel(recipeId: String) : ViewModel() {

    private val database = Realm.getDefaultInstance()
    private val dao = database.recipeDao()

    val recipe: LiveData<Recipe>

    init {
        recipe = Transformations.map(dao.findByIdAsync(recipeId)) {
            // Map from LiveData<RecipeEntity> to LiveData<Recipe>
            input -> input
        }
    }

    override fun onCleared() {
        database.close()
    }


    class Factory(private val recipeId: String) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            @Suppress("UNCHECKED_CAST")
            return RecipeViewModel(recipeId) as T
        }
    }


}
