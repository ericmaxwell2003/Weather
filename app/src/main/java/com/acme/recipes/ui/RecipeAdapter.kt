package com.acme.recipes.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.acme.recipes.R
import com.acme.recipes.databinding.RecipeItemBinding
import com.acme.recipes.model.Recipe

class RecipeAdapter(private val clickListener: ItemClickListener<Recipe>) :
        RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var recipes = emptyList<Recipe>()

    fun setRecipeList(recipeList: List<Recipe>) {
        recipes = recipeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = DataBindingUtil
                .inflate<RecipeItemBinding>(LayoutInflater.from(parent.context),
                                            R.layout.recipe_item,
                                            parent, false)

        binding.itemClickListener = clickListener
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binding.recipe = recipes[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = recipes.size

    class RecipeViewHolder(val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root)
}
