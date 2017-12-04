@file:JvmName("RealmUtils")

package com.acme.recipes.database.util

import com.acme.recipes.database.dao.RecipeDao
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults

// Returns a new RecipeDao Instance passing, this realm instance into the constructor
fun Realm.recipeDao() = RecipeDao(this)

// Add asLiveData extension to RealmResults
fun <E : RealmModel> RealmResults<E>.asLiveData() = RealmResultsLiveData(this)

// Add asLiveData extension to RealmModels
fun <E : RealmModel> E.asLiveData() = RealmLiveData(this)