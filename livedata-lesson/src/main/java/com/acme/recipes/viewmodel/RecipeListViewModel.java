package com.acme.recipes.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.acme.recipes.database.dao.RecipeDao;
import com.acme.recipes.database.entity.RecipeEntity;
import com.acme.recipes.model.Recipe;
import com.acme.recipes.service.RecipeStreamingService;

import java.util.List;

import io.realm.Realm;

public class RecipeListViewModel extends ViewModel {

    private Realm database;
    private RecipeDao dao;
    private RecipeStreamingService recipeStreamingService;

    private LiveData<List<? extends Recipe>> recipes;
    private LiveData<Integer> recipeCount;

    public RecipeListViewModel() {
        database = Realm.getDefaultInstance();
        recipeStreamingService = new RecipeStreamingService();
        dao = new RecipeDao(database);

        recipes = Transformations.map(dao.findAllAsync(), new Function<List<RecipeEntity>, List<? extends Recipe>>() {
            @Override
            public List<? extends Recipe> apply(List<RecipeEntity> input) {
                return input;
            }
        });

        recipeCount = Transformations.map(recipes, new Function<List<? extends Recipe>, Integer>() {
            @Override
            public Integer apply(List<? extends Recipe> input) {
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

    public LiveData<List<? extends Recipe>> getRecipes() {
        return recipes;
    }

    public LiveData<Integer> getRecipeCount() {
        return recipeCount;
    }
}
