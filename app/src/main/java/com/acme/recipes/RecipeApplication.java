package com.acme.recipes;

import android.app.Application;

import com.acme.recipes.database.util.DatabaseInitTransaction;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RecipeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        Realm.setDefaultConfiguration(
                new RealmConfiguration.Builder()
                        .schemaVersion(1)
                        .deleteRealmIfMigrationNeeded()
                        .initialData(new DatabaseInitTransaction())
                        .build());

    }
}
