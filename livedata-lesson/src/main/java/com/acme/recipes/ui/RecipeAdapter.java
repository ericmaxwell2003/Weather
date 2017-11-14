package com.acme.recipes.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.acme.recipes.R;
import com.acme.recipes.databinding.RecipeItemBinding;
import com.acme.recipes.model.Recipe;

import java.util.List;

import javax.annotation.Nonnull;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<? extends Recipe> recipes;
    private ItemClickListener<? extends Recipe> clickListener;

    public RecipeAdapter(@Nonnull ItemClickListener<? extends Recipe> clickListener) {
        this.clickListener = clickListener;
    }

    public void setRecipeList(final List<? extends Recipe> recipeList) {
        recipes = recipeList;
        notifyDataSetChanged();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecipeItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.recipe_item,
                        parent, false);
        binding.setItemClickListener(clickListener);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.binding.setRecipe(recipes.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return recipes == null ? 0 : recipes.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {

        final RecipeItemBinding binding;

        RecipeViewHolder(RecipeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
