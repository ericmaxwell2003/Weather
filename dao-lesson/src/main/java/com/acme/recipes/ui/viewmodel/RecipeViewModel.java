package com.acme.recipes.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.acme.recipes.data.dao.RecipeDao;
import com.acme.recipes.data.entity.RecipeEntity;

import io.realm.Realm;
import io.realm.RealmObject;

public class RecipeViewModel extends ViewModel {

    private final Realm database;
    private final RecipeDao dao;

    private RecipeEntity recipe;

    public RecipeViewModel(String recipeId) {
        database = Realm.getDefaultInstance();
        dao = new RecipeDao(database);
        recipe = dao.findById(recipeId);
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    @Override
    protected void onCleared() {
        RealmObject.removeAllChangeListeners(recipe);
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
