package com.acme.recipes.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acme.recipes.R;
import com.acme.recipes.database.entity.RecipeEntity;
import com.acme.recipes.viewmodel.RecipeListViewModel;

import javax.annotation.Nonnull;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class RecipeListFragment extends Fragment {

    private RecipeAdapter recipeAdapter;
    private RecipeListViewModel recipeListViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View root = LayoutInflater.from(getContext())
                .inflate(R.layout.recipes_list_fragment, container, false);

        recipeAdapter = new RecipeAdapter(recipeClickListener);
        RecyclerView recyclerView = root.findViewById(R.id.recipes_list_recycler_view);
        recyclerView.setAdapter(recipeAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        recipeAdapter.setRecipeList(recipeListViewModel.getRecipes());
    }

    @Override
    public void onStart() {
        super.onStart();
        recipeListViewModel.getRecipes().addChangeListener(recipeEntityRealmChangeListener);

    }

    @Override
    public void onStop() {
        super.onStop();
        recipeListViewModel.getRecipes().removeChangeListener(recipeEntityRealmChangeListener);
    }

    // Update Recipes when they change.
    private RealmChangeListener<RealmResults<RecipeEntity>> recipeEntityRealmChangeListener =
            new RealmChangeListener<RealmResults<RecipeEntity>>() {
                @Override
                public void onChange(@Nonnull RealmResults<RecipeEntity> updatedRecipes) {
                    recipeAdapter.setRecipeList(updatedRecipes);
                }
            };

    private ItemClickListener<RecipeEntity> recipeClickListener =
            new ItemClickListener<RecipeEntity>() {
                @Override
                public void onClick(RecipeEntity recipe) {
                    ((MainActivity) getActivity()).show(recipe);
                }
            };

}
