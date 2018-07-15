package com.mfathy.ladelicerestaurant.view.menu;

import android.support.annotation.NonNull;

import com.mfathy.ladelicerestaurant.data.model.Recipe;

import java.util.List;

/**
 * Created by Mohammed Fathy on 15/03/2018.
 * dev.mfathy@gmail.com
 * <p>
 * {@link MenuPresenter} {@link MenuActivity} Contract.
 * Defines all presenter behaviors and actions.
 * Defines all view methods.
 */

class MenuContract {

    interface View {

        void showToast(String s);

        void displayRecipes(List<Recipe> recipes);

        void displayError(String s);
    }

    interface Presenter<V extends View> {

        void onBind(@NonNull V view);

        void onDestroy();

        void getRecipes();
    }
}
