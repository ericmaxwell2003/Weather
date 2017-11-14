package com.acme.recipes.model;

import java.util.List;

public interface Recipe {

    String getId();
    String getName();
    String getDescription();
    int getCalories();

    List<? extends Ingredient> getIngredients();

}
