package com.mfathy.ladelicerestaurant.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohammed Fathy on 12/07/2018.
 * dev.mfathy@gmail.com
 *
 * {@link Recipe} is a data class menu item.
 */
public class Recipe {

    @SerializedName("recipe_id")
    @Expose
    private Integer recipeId;
    @SerializedName("recipe_name")
    @Expose
    private String recipeName;
    @SerializedName("recipe_description")
    @Expose
    private String recipeDescription;
    @SerializedName("recipe_url")
    @Expose
    private String recipeUrl;
    @SerializedName("recipe_price")
    @Expose
    private Double recipePrice;

    public Recipe() {
    }

    public Recipe(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }

    public void setRecipeUrl(String recipeUrl) {
        this.recipeUrl = recipeUrl;
    }

    public Double getRecipePrice() {
        return recipePrice;
    }

    public void setRecipePrice(Double recipePrice) {
        this.recipePrice = recipePrice;
    }

}
