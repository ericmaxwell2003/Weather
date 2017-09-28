package com.acme.recipes.data.dao;

import com.acme.recipes.data.entity.RecipeEntity;
import com.acme.recipes.data.entity.RecipeEntityFields;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RecipeDao extends Dao<RecipeEntity> {

    public RecipeDao(Realm db) {
        super(db);
    }

    public RealmResults<RecipeEntity> findAllAsync() {
        return where().findAllAsync();
    }

    public RecipeEntity findById(final String id) {
        return where()
                .equalTo(RecipeEntityFields.ID, id)
                .findFirst();
    }

    public RealmResults<RecipeEntity> findAllContainingSugar() {
        return whereContainingSugar().findAll();
    }

    public RealmResults<RecipeEntity> findAllContainingSugarAsync() {
        return whereContainingSugar().findAllAsync();
    }

    private RealmQuery<RecipeEntity> whereContainingSugar() {
        return where().equalTo(RecipeEntityFields.INGREDIENTS.NAME, "sugar", Case.INSENSITIVE);
    }

    private RealmQuery<RecipeEntity> where() {
        return db.where(RecipeEntity.class);
    }

}
