package com.acme.recipes.data.util;

import android.arch.lifecycle.LiveData;

import javax.annotation.Nonnull;

import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class RealmResultsLiveData<T extends RealmModel> extends LiveData<RealmResults<T>> {

    private RealmResults<T> results;

    private RealmChangeListener<RealmResults<T>> listener;

    public RealmResultsLiveData(RealmResults<T> results) {
        this.results = results;

        listener = new RealmChangeListener<RealmResults<T>>() {
            @Override
            public void onChange(@Nonnull  RealmResults<T> updates) {
                setValue(updates);
            }
        };

    }

    @Override
    protected void onActive() {
        super.onActive();
        results.addChangeListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        results.removeChangeListener(listener);
    }
}
