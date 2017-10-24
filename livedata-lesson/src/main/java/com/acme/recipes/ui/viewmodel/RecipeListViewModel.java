package com.acme.recipes.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.acme.recipes.data.RecipeStreamingService;
import com.acme.recipes.data.dao.RecipeDao;
import com.acme.recipes.data.entity.RecipeEntity;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecipeListViewModel extends ViewModel {

    private Realm database;
    private RecipeDao dao;
    private RecipeStreamingService recipeStreamingService;

    private RealmResults<RecipeEntity> recipes;

    public RecipeListViewModel() {
        database = Realm.getDefaultInstance();
        recipeStreamingService = new RecipeStreamingService();
        dao = new RecipeDao(database);
        recipes = dao.findAllAsync();
    }

    public void startStreaming() {
        recipeStreamingService.startStreamingRecipes();
    }

    public void stopStreaming() {
        recipeStreamingService.stopStreamingRecipes();
    }

    public void deleteAllRecipes() {
        dao.deleteAll();
    }

    @Override
    protected void onCleared() {
        recipeStreamingService.stopStreamingRecipes();
        recipes.removeAllChangeListeners();
        database.close();
    }

    public RealmResults<RecipeEntity> getRecipes() {
        return recipes;
    }

}
