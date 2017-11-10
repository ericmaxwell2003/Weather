package com.acme.recipes.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.acme.recipes.database.dao.RecipeDao;
import com.acme.recipes.database.entity.RecipeEntity;
import com.acme.recipes.service.RecipeStreamingService;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecipeListViewModel extends ViewModel {

    private Realm database;
    private RecipeDao dao;
    private RecipeStreamingService recipeStreamingService;

    private LiveData<RealmResults<RecipeEntity>> recipes;
    private LiveData<Integer> recipeCount;

    public RecipeListViewModel() {
        database = Realm.getDefaultInstance();
        recipeStreamingService = new RecipeStreamingService();
        dao = new RecipeDao(database);
        recipes = dao.findAllAsync();

        recipeCount = Transformations.map(recipes, new Function<RealmResults<RecipeEntity>, Integer>() {
            @Override
            public Integer apply(RealmResults<RecipeEntity> input) {
                return input.size();
            }
        });

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
        database.close();
    }

    public LiveData<RealmResults<RecipeEntity>> getRecipes() {
        return recipes;
    }

    public LiveData<Integer> getRecipeCount() {
        return recipeCount;
    }
}
