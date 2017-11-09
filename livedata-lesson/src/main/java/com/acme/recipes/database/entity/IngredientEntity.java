package com.acme.recipes.database.entity;

import java.util.Locale;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class IngredientEntity implements RealmModel {

    private String name;
    private double quantity;
    private String quantityType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public IngredientEntity(String name, double quantity, String quantityType) {
        this.name = name;
        this.quantity = quantity;
        this.quantityType = quantityType;
    }

    public IngredientEntity() {
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%.1f %s of %s",
                getQuantity(), getQuantityType(), getName());
    }
}