package com.acme.recipes.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.acme.recipes.database.dao.RecipeDao;
import com.acme.recipes.database.entity.RecipeEntity;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecipeListViewModel extends ViewModel {

    private Realm database;
    private RecipeDao dao;
    private RealmResults<RecipeEntity> recipes;

    public RecipeListViewModel() {

        database = Realm.getDefaultInstance();

        dao = new RecipeDao(database);

        recipes = dao.findAllAsync();
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
