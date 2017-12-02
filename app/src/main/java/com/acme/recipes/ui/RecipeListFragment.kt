package com.acme.recipes.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.acme.recipes.R
import com.acme.recipes.model.Recipe
import com.acme.recipes.viewmodel.RecipeListViewModel

class RecipeListFragment : Fragment() {

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeListViewModel: RecipeListViewModel
    private lateinit var recipeTotalCountLabel: TextView

    private val recipeClickListener = ItemClickListener<Recipe> { recipe ->
        recipeListViewModel.stopStreaming()
        (activity as? MainActivity)?.show(recipe)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = LayoutInflater
                .from(context)
                .inflate(R.layout.recipes_list_fragment, container, false)

        recipeAdapter = RecipeAdapter(recipeClickListener)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recipes_list_recycler_view)
        recyclerView.adapter = recipeAdapter

        recipeTotalCountLabel = root.findViewById(R.id.totalCountLabel)
        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recipeListViewModel = ViewModelProviders
                .of(this)
                .get(RecipeListViewModel::class.java)

        recipeListViewModel.recipes.observe(this, Observer { recipes ->
            if(recipes != null) {
                recipeAdapter.setRecipeList(recipes)
            }
        })

        recipeListViewModel.recipeCount.observe(this, Observer { updatedCount ->
            recipeTotalCountLabel.text = getString(R.string.recipe_count_msg, updatedCount.toString())
        })

        initializeStreamButton()
    }

    private fun initializeStreamButton() {

        val streamButton = activity?.findViewById<FloatingActionButton>(R.id.fabStreamRecipes)

        streamButton?.setOnClickListener { v ->
            val fab = v as FloatingActionButton
            if (fab.isSelected) {
                fab.isSelected = false
                recipeListViewModel.stopStreaming()
            } else {
                fab.isSelected = true
                recipeListViewModel.startStreaming()
            }
        }

        streamButton?.setOnLongClickListener {
            recipeListViewModel.deleteAllRecipes()
            true
        }
    }

}
