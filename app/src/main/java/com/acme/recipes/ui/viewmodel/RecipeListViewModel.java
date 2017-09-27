package com.acme.recipes.ui.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.acme.recipes.data.entity.RecipeEntity;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecipeListViewModel extends ViewModel {

    private Realm database;
    private RealmResults<RecipeEntity> recipes;

    public RecipeListViewModel() {

        database = Realm.getDefaultInstance();

        recipes = database
                .where(RecipeEntity.class)
                .findAllAsync();
    }

    @Override
    protected void onCleared() {
        recipes.removeAllChangeListeners();
        database.close();
    }

    public RealmResults<RecipeEntity> getRecipes() {
        return recipes;
    }
}
