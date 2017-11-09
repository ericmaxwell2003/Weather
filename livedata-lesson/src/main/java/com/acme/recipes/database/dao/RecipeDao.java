package com.acme.recipes.database.dao;

import android.arch.lifecycle.LiveData;

import com.acme.recipes.database.entity.RecipeEntity;
import com.acme.recipes.database.entity.RecipeEntityFields;
import com.acme.recipes.database.util.RealmLiveData;
import com.acme.recipes.database.util.RealmResultsLiveData;

import javax.annotation.Nonnull;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RecipeDao extends Dao<RecipeEntity> {

    public RecipeDao(Realm db) {
        super(db);
    }

    public LiveData<RealmResults<RecipeEntity>> findAllAsync() {
        return new RealmResultsLiveData<>(where().findAllAsync());
    }

    public LiveData<RecipeEntity> findByIdAsync(final String id) {
        return new RealmLiveData<>(
                where()
                .equalTo(RecipeEntityFields.ID, id)
                .findFirstAsync());
    }

    private RealmQuery<RecipeEntity> where() {
        return db.where(RecipeEntity.class);
    }

    public void deleteAll() {
        db.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@Nonnull Realm bgRealm) {
                bgRealm.delete(RecipeEntity.class);
            }
        });
    }
}
