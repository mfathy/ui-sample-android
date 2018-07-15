package com.mfathy.ladelicerestaurant.data.remote;


import com.mfathy.ladelicerestaurant.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * Created by Mohammed Fathy on 12/07/2018.
 * dev.mfathy@gmail.com
 *
 * This is a remote server api endpoints.
 */

public interface ApiEndpoints {

    @GET("/recipes")
    Observable<List<Recipe>> getRecipes();
}
