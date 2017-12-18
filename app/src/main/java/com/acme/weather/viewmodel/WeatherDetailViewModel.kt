package com.acme.weather.viewmodel

import android.arch.lifecycle.ViewModel
import com.acme.weather.model.api.WeatherSummary

class WeatherDetailViewModel : ViewModel() {

    val forecast = "All rain all day!"

    val weatherSummary = WeatherSummary()

//    private val database = Realm.getDefaultInstance()
//    private val dao = database.recipeDao()
//
//    val recipe: LiveData<Recipe>
//
//    init {
//        recipe = Transformations.map(dao.findByIdAsync(recipeId)) {
//            // Map from LiveData<RecipeEntity> to LiveData<Recipe>
//            input -> input
//        }
//    }
//
//    override fun onCleared() {
//        database.close()
//    }
//
//
//    class Factory(private val recipeId: String) : ViewModelProvider.NewInstanceFactory() {
//
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//
//            @Suppress("UNCHECKED_CAST")
//            return RecipeViewModel(recipeId) as T
//        }
//    }


}
