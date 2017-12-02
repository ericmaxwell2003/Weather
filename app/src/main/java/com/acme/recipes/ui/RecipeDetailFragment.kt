package com.acme.recipes.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acme.recipes.R
import com.acme.recipes.database.entity.RecipeEntity
import com.acme.recipes.databinding.RecipeDetailFragmentBinding
import com.acme.recipes.viewmodel.RecipeViewModel

class RecipeDetailFragment : Fragment() {
    private lateinit var binding: RecipeDetailFragmentBinding
    private lateinit var ingredientListAdapter: IngredientAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.recipe_detail_fragment,
                container, false)

        ingredientListAdapter = IngredientAdapter()
        binding.ingredientsList.adapter = ingredientListAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = RecipeViewModel.Factory(arguments?.getString(RECIPE_ID_KEY) ?: "0")

        val viewModel = ViewModelProviders
                .of(this, factory)
                .get(RecipeViewModel::class.java)

        viewModel.recipe.observe(this, Observer { recipe ->
            binding.recipe = recipe
            val ingredients = (recipe as? RecipeEntity)?.ingredients
            if (ingredients != null) {
                ingredientListAdapter.setIngredients(ingredients)
            }
        })

    }

    companion object {

        private val RECIPE_ID_KEY = "recipe_id"

        fun forRecipe(recipeId: String): RecipeDetailFragment {
            val fragment = RecipeDetailFragment()
            val args = Bundle()
            args.putString(RECIPE_ID_KEY, recipeId)
            fragment.arguments = args
            return fragment
        }
    }
}
