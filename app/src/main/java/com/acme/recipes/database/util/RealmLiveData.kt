package com.acme.recipes.database.util

import android.arch.lifecycle.LiveData

import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmObject

class RealmLiveData<T : RealmModel>(private val model: T) : LiveData<T>() {

    private val listener = RealmChangeListener<T> { update -> value = update }

    override fun onActive() {
        super.onActive()
        RealmObject.addChangeListener(model, listener)
    }

    override fun onInactive() {
        super.onInactive()
        RealmObject.removeChangeListener(model, listener)
    }

}