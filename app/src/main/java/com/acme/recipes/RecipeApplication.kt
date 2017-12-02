package com.acme.recipes

import android.app.Application

import com.acme.recipes.database.util.DatabaseInitTransaction

import io.realm.Realm
import io.realm.RealmConfiguration

class RecipeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        Realm.setDefaultConfiguration(
                RealmConfiguration.Builder()
                        .schemaVersion(1)
                        .deleteRealmIfMigrationNeeded()
                        .initialData(DatabaseInitTransaction())
                        .build())

    }
}
