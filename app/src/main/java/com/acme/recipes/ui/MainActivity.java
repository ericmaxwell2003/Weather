package com.acme.recipes.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.acme.recipes.R;
import com.acme.recipes.database.entity.RecipeEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            RecipeListFragment fragment = new RecipeListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, "recipeList").commit();
        }
    }

    public void show(RecipeEntity recipe) {

        RecipeDetailFragment recipeFragment = RecipeDetailFragment.forRecipe(recipe.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("recipe")
                .replace(R.id.fragment_container,
                        recipeFragment, null).commit();
    }

}