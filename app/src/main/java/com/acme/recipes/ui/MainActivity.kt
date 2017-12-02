package com.acme.recipes.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.acme.recipes.R
import com.acme.recipes.model.Recipe

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = RecipeListFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, "recipeList").commit()
        }
    }

    fun show(recipe: Recipe) {

        val recipeFragment = RecipeDetailFragment.forRecipe(recipe.id)

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("recipe")
                .replace(R.id.fragment_container,
                        recipeFragment, null).commit()
    }

}