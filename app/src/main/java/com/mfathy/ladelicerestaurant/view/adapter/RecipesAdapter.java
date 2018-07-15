package com.mfathy.ladelicerestaurant.view.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.mfathy.ladelicerestaurant.R;
import com.mfathy.ladelicerestaurant.data.model.Recipe;
import com.mfathy.ladelicerestaurant.view.model.HomeItem;

import java.util.List;

/**
 * Created by Mohammed Fathy on 12/07/2018.
 * dev.mfathy@gmail.com
 *
 * {@link RecipesAdapter} is a custom adapter for recipes items on {@link com.mfathy.ladelicerestaurant.view.menu.MenuActivity} recyclerView.
 */
public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    //  Glide instance to manage only one instance of Glide per adapter.
    private RequestManager mGlide;
    private List<Recipe> itemList;
    private Context mContext;

    public RecipesAdapter(List<Recipe> itemList, Context context, RequestManager glide) {
        this.itemList = itemList;
        this.mContext = context;
        this.mGlide = glide;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new ViewHolder(inflater.inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = itemList.get(position);
        mGlide.load(recipe.getRecipeUrl())
                .into((holder).recipeImageView);
        holder.recipeTitle.setText(recipe.getRecipeName());
        holder.recipeDescription.setText(recipe.getRecipeDescription());
        holder.recipePrice.setText(String.valueOf(recipe.getRecipePrice()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView recipeImageView;
        private TextView recipeTitle;
        private TextView recipeDescription;
        private TextView recipePrice;
        private ImageButton addButton;
        private TextView countTextView;
        private ImageButton minusButton;

        ViewHolder(View view) {
            super(view);
            recipeImageView = view.findViewById(R.id.recipe_imageView);
            recipeTitle = view.findViewById(R.id.recipe_title);
            recipeDescription = view.findViewById(R.id.recipe_description);
            recipePrice = view.findViewById(R.id.recipe_price);
            addButton = view.findViewById(R.id.add_button);
            countTextView = view.findViewById(R.id.count_textView);
            minusButton = view.findViewById(R.id.minus_button);

            addButton.setOnClickListener(this);
            minusButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String count = countTextView.getText().toString();
            int countInteger = Integer.parseInt(count);
            switch (v.getId()) {
                case R.id.add_button:
                    animateNumber(countTextView, countInteger, ++countInteger);
                    break;
                case R.id.minus_button:
                    if (countInteger == 0) break;
                    animateNumber(countTextView, countInteger, --countInteger);
                    break;
            }
        }

        void animateNumber(TextView countTextView, int oldValue, int newValue){
            ValueAnimator animator = ValueAnimator.ofInt(oldValue, newValue);
            animator.setDuration(250);
            animator.addUpdateListener(animation -> countTextView.setText(animation.getAnimatedValue().toString()));
            animator.start();
        }
    }
}
