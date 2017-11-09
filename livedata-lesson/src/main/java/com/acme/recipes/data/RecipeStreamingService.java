package com.acme.recipes.data;

import android.support.annotation.WorkerThread;

import com.acme.recipes.data.dao.RecipeDao;
import com.acme.recipes.data.entity.IngredientEntity;
import com.acme.recipes.data.entity.RecipeEntity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import io.realm.Realm;

public class RecipeStreamingService {

    private Random random = new Random(System.currentTimeMillis());;
    private Timer timer;
    private final static int EMIT_EVERY_NUM_SECONDS = 1;

    public void startStreamingRecipes() {
        startTimer(EMIT_EVERY_NUM_SECONDS);
    }

    public void stopStreamingRecipes() {
        cancelTimer();
    }

    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    private void startTimer(int emitEveryNumSeconds) {

        cancelTimer();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                generateNewRecipe();
            }
        }, 500, emitEveryNumSeconds * 1000);

    }

    @WorkerThread
    private void generateNewRecipe() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        RecipeDao recipeDao = new RecipeDao(realm);

        String id = UUID.randomUUID().toString();
        String dessertName = deserts[random.nextInt(deserts.length - 1)];
        String dessertDesc = adjectives[random.nextInt(adjectives.length - 1)] + " " + dessertName;
        int calorieCount = random.nextInt(1000);

        RecipeEntity recipeEntity = recipeDao.copyOrUpdate(
            new RecipeEntity(id, dessertName, dessertDesc, calorieCount)
        );

        recipeEntity.getIngredients().add(
                new IngredientEntity(dessertName, 1, "Store Bought")
        );

        realm.commitTransaction();
    }

    private String[] adjectives = { "Tasty", "Yummy", "Delicious" };
    private String[] deserts = { "Baked Alaska",
                                 "Baked Apple",
                                 "Baklava",
                                 "Banana Aplit",
                                 "Belgian Waffle",
                                 "Biscotti",
                                 "Black Forest Cake",
                                 "Blueberry Muffin",
                                 "Boston Cream Pie",
                                 "Bread Pudding",
                                 "Brownie",
                                 "Buttercream Frosting",
                                 "Butterscotch"};
}
