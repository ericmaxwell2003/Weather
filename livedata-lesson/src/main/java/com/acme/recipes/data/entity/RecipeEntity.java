package com.acme.recipes.data.entity;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
public class RecipeEntity implements RealmModel {

    @Required
    @PrimaryKey
    private String id;
    private String name;
    private String description;
    private int calories;
    private RealmList<IngredientEntity> ingredients;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public RealmList<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(RealmList<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public RecipeEntity(String id, String name, String description, int calories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calories = calories;
    }

    // Empty constructor required by Realm.
    public RecipeEntity() {}

}