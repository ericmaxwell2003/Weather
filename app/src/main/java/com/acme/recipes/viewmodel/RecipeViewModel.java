package com.acme.recipes.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.acme.recipes.database.dao.RecipeDao;
import com.acme.recipes.database.entity.RecipeEntity;
import com.acme.recipes.model.Recipe;

import io.realm.Realm;

public class RecipeViewModel extends ViewModel {

    private final Realm database;
    private final RecipeDao dao;

    private LiveData<Recipe> recipe;

    public RecipeViewModel(String recipeId) {
        database = Realm.getDefaultInstance();
        dao = new RecipeDao(database);
        recipe = Transformations.map(dao.findByIdAsync(recipeId),
                new Function<RecipeEntity, Recipe>() {
            @Override
            public Recipe apply(RecipeEntity input) {
                return input;
            }
        });
    }

    public LiveData<Recipe> getRecipe() {
        return recipe;
    }

    @Override
    protected void onCleared() {
        database.close();
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private final String recipeId;

        public Factory(String recipeId) {
            this.recipeId = recipeId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new RecipeViewModel(recipeId);
        }
    }


}
