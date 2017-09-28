package com.acme.recipes.data.util;

import com.acme.recipes.data.dao.RecipeDao;
import com.acme.recipes.data.entity.IngredientEntity;
import com.acme.recipes.data.entity.RecipeEntity;

import java.util.Arrays;
import java.util.UUID;

import javax.annotation.Nonnull;

import io.realm.Realm;

public class DatabaseInitTransaction implements Realm.Transaction {

    @Override
    public void execute(@Nonnull Realm db) {

        RecipeDao recipeDao = new RecipeDao(db);

        db.deleteAll();

        RecipeEntity eclair = recipeDao.copyOrUpdate(
            new RecipeEntity(UUID.randomUUID().toString(),
                    "Eclair",
                    "Pastry filled with cream",
                    800)
        );
        eclair.getIngredients().addAll(
            Arrays.asList(
                new IngredientEntity("Sugar",1,  "cup"),
                new IngredientEntity("Flour",1.5,"cup"),
                new IngredientEntity("Cocoa",1,  "tbsp"),
                new IngredientEntity("Milk", 1,  "quart")
            )
        );

        RecipeEntity oreo = recipeDao.copyOrUpdate(
            new RecipeEntity(UUID.randomUUID().toString(),
                    "Oreo",
                    "Chocolate cookie",
                    650)
        );
        oreo.getIngredients().addAll(
            Arrays.asList(
                new IngredientEntity("Sugar",1,"cup"),
                new IngredientEntity("Flour",2,"cup")
            )
        );

    }

}
