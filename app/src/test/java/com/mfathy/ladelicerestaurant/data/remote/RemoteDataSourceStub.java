package com.mfathy.ladelicerestaurant.data.remote;

import com.mfathy.ladelicerestaurant.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Mohammed Fathy on 15/07/2018.
 * dev.mfathy@gmail.com
 */

class RemoteDataSourceStub implements com.mfathy.ladelicerestaurant.data.DataSource {

    static List<Recipe> RECIPES_RESPONSE;

    static {
        givenRecipesInitialized();
    }

    public static void givenRecipesInitialized() {
        RECIPES_RESPONSE = new ArrayList<>();
        RECIPES_RESPONSE.add(new Recipe(0));
        RECIPES_RESPONSE.add(new Recipe(1));
        RECIPES_RESPONSE.add(new Recipe(2));
        RECIPES_RESPONSE.add(new Recipe(3));
        RECIPES_RESPONSE.add(new Recipe(4));
        RECIPES_RESPONSE.add(new Recipe(5));
    }

    public static void givenRecipesNotInitialized() {
        RECIPES_RESPONSE.clear();
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return Observable.just(RECIPES_RESPONSE);
    }
}
