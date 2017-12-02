package com.acme.recipes.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.acme.recipes.R
import com.acme.recipes.databinding.IngredientItemBinding
import com.acme.recipes.model.Ingredient

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    private var ingredients = emptyList<Ingredient>()

    fun setIngredients(ingredients: List<Ingredient>) {
        this.ingredients = ingredients
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {

        val binding = DataBindingUtil
                .inflate<IngredientItemBinding>(LayoutInflater.from(parent.context),
                                                R.layout.ingredient_item,
                                                parent, false)

        return IngredientViewHolder(binding)

    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.binding.ingredient = ingredients[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = ingredients.size

    class IngredientViewHolder(val binding: IngredientItemBinding) :
            RecyclerView.ViewHolder(binding.root)
}
