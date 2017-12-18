package com.acme.weather.viewmodel

import android.arch.lifecycle.ViewModel

class WeatherListViewModel : ViewModel() {

//    private val dao = database.recipeDao()
//    private val recipeStreamingService = RecipeStreamingService()
//
//    val recipes: LiveData<List<Recipe>>
//    val recipeCount: LiveData<Int>
//
//    init {
//        recipes = Transformations.map(dao.findAllAsync()) {
//            // Map from LiveData<List<RecipeEntity>> to LiveData<List<Recipe>>
//            input -> input
//        }
//
//        recipeCount = Transformations.map(recipes) {
//            // Map from LiveData<List<RecipeEntity>> to LiveData<Integer>
//            input -> input.size
//        }
//    }
//
//    fun startStreaming() {
//        recipeStreamingService.startStreamingRecipes()
//    }
//
//    fun stopStreaming() {
//        recipeStreamingService.stopStreamingRecipes()
//    }
//
//    fun deleteAllRecipes() {
//        dao.deleteAll()
//    }
//
//    override fun onCleared() {
//        database.close()
//    }
}
