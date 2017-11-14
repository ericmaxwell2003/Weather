package com.acme.recipes.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.acme.recipes.R;
import com.acme.recipes.databinding.IngredientItemBinding;
import com.acme.recipes.model.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<? extends Ingredient> ingredients;

    public void setIngredients(List<? extends Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        IngredientItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.ingredient_item,
                        parent, false);

        return new IngredientViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.binding.setIngredient(ingredients.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return ingredients == null ? 0 : ingredients.size();
    }

    static class IngredientViewHolder extends RecyclerView.ViewHolder {

        final IngredientItemBinding binding;

        IngredientViewHolder(IngredientItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
