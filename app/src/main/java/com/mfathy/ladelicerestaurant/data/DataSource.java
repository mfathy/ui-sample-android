package com.mfathy.ladelicerestaurant.data;

import com.mfathy.ladelicerestaurant.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Mohammed Fathy on 12/07/2018.
 * dev.mfathy@gmail.com
 * <p>
 * <p>
 * Data source contract.
 */

public interface DataSource {
    Observable<List<Recipe>> getRecipes();
}
