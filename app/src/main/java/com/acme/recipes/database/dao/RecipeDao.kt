package com.acme.recipes.database.dao

import android.arch.lifecycle.LiveData
import com.acme.recipes.database.entity.RecipeEntity
import com.acme.recipes.database.entity.RecipeEntityFields
import com.acme.recipes.database.util.asLiveData
import io.realm.Realm
import io.realm.RealmQuery

class RecipeDao(db: Realm) : Dao<RecipeEntity>(db) {

    fun findAllAsync(): LiveData<List<RecipeEntity>> {
        return where().findAllAsync().asLiveData()
    }

    fun findByIdAsync(id: String): LiveData<RecipeEntity> {
        return  where()
                .equalTo(RecipeEntityFields.ID, id)
                .findFirstAsync().asLiveData()
    }

    private fun where(): RealmQuery<RecipeEntity> {
        return db.where(RecipeEntity::class.java)
    }

    fun deleteAll() {
        db.executeTransactionAsync { bgRealm -> bgRealm.delete(RecipeEntity::class.java) }
    }
}
