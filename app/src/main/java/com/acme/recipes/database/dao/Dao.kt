package com.acme.recipes.database.dao

import io.realm.Realm
import io.realm.RealmModel

abstract class Dao<T : RealmModel>(protected var db: Realm) {

    fun copyOrUpdate(entity: T): T {

        val returnEntity: T

        if (db.isInTransaction) {
            returnEntity = db.copyToRealmOrUpdate(entity)

        } else {
            try {
                db.beginTransaction()
                returnEntity = db.copyToRealmOrUpdate(entity)
                db.commitTransaction()
            } catch (e: Exception) {
                db.cancelTransaction()
                throw e
            }

        }

        return returnEntity
    }
}
