package com.acme.recipes.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acme.recipes.R;
import com.acme.recipes.databinding.RecipeDetailFragmentBinding;
import com.acme.recipes.viewmodel.RecipeViewModel;

public class RecipeDetailFragment extends Fragment {

    private static final String RECIPE_ID_KEY = "recipe_id";
    private RecipeDetailFragmentBinding binding;
    private IngredientAdapter ingredientListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,
                R.layout.recipe_detail_fragment,
                container, false);

        ingredientListAdapter = new IngredientAdapter();
        binding.ingredientsList.setAdapter(ingredientListAdapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecipeViewModel.Factory factory =
                new RecipeViewModel.Factory(getArguments().getString(RECIPE_ID_KEY));

        RecipeViewModel viewModel = ViewModelProviders
                .of(this, factory)
                .get(RecipeViewModel.class);

        binding.setRecipe(viewModel.getRecipe());
        ingredientListAdapter.setIngredients(viewModel.getRecipe().getIngredients());
    }

    public static RecipeDetailFragment forRecipe(String recipeId) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putString(RECIPE_ID_KEY, recipeId);
        fragment.setArguments(args);
        return fragment;
    }
}
