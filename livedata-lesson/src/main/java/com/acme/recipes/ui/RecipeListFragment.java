package com.acme.recipes.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acme.recipes.R;
import com.acme.recipes.data.entity.RecipeEntity;
import com.acme.recipes.ui.viewmodel.RecipeListViewModel;

import javax.annotation.Nonnull;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class RecipeListFragment extends Fragment {

    private RecipeAdapter recipeAdapter;
    private RecipeListViewModel recipeListViewModel;
    private static final String TAG = "RecipeListFragment";

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

        recipeListViewModel.getRecipes().observe(this, new Observer<RealmResults<RecipeEntity>>() {
            @Override
            public void onChanged(@Nullable RealmResults<RecipeEntity> recipeEntities) {
                recipeAdapter.setRecipeList(recipeEntities);
            }
        });

        initializeStreamButton();
    }

    private void initializeStreamButton() {
        FloatingActionButton streamButton = getActivity().findViewById(R.id.fabStreamRecipes);
        streamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingActionButton fab = (FloatingActionButton) v;
                if(fab.isSelected()) {
                    fab.setSelected(false);
                    recipeListViewModel.stopStreaming();
                } else {
                    fab.setSelected(true);
                    recipeListViewModel.startStreaming();
                }
            }
        });
        streamButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                recipeListViewModel.deleteAllRecipes();
                return true;
            }
        });
    }

    private ItemClickListener<RecipeEntity> recipeClickListener =
            new ItemClickListener<RecipeEntity>() {
                @Override
                public void onClick(RecipeEntity recipe) {
                    recipeListViewModel.stopStreaming();
                    ((MainActivity) getActivity()).show(recipe);
                }
            };

}
