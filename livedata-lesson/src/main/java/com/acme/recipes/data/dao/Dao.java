package com.acme.recipes.data.dao;

import io.realm.Realm;
import io.realm.RealmModel;

public abstract class Dao<T extends RealmModel> {

    protected Realm db;

    public Dao(Realm db) {
        this.db = db;
    }

    public T copyOrUpdate(T entity) {

        if(db.isInTransaction()) {
            entity = db.copyToRealmOrUpdate(entity);
            
        } else {
            try {
                db.beginTransaction();
                entity = db.copyToRealmOrUpdate(entity);
                db.commitTransaction();
            } catch (Exception e) {
                db.cancelTransaction();
                throw e;
            }
        }

        return entity;
    }
}
