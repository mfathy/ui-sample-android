package com.mfathy.ladelicerestaurant.view.menu;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mfathy.ladelicerestaurant.R;
import com.mfathy.ladelicerestaurant.data.Injection;
import com.mfathy.ladelicerestaurant.data.model.Recipe;
import com.mfathy.ladelicerestaurant.view.adapter.RecipesAdapter;
import com.mfathy.ladelicerestaurant.view.model.HomeItem;

import java.util.List;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener, MenuContract.View {

    private MenuPresenter menuPresenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuPresenter = new MenuPresenter(Injection.provideDataRepository(this));
        menuPresenter.onBind(this);

        ImageView imageViewClose = findViewById(R.id.imageView_close);
        ImageView imageView01 = findViewById(R.id.imageView_01);
        TextView itemTitle = findViewById(R.id.item_title);
        recyclerView = findViewById(R.id.recyclerView);

        fillViewWithData(imageView01, itemTitle);

        initRecyclerView(menuPresenter);

        imageViewClose.setOnClickListener(this);
    }

    private void initRecyclerView(MenuPresenter menuPresenter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        menuPresenter.getRecipes();
    }

    private void fillViewWithData(ImageView imageView01, TextView itemTitle) {
        HomeItem item = getIntent().getParcelableExtra(HomeItem.class.getSimpleName());
        int position = getIntent().getIntExtra("adapterPosition", 0);
        imageView01.setTransitionName(getString(R.string.item_image) + position);
        imageView01.setImageResource((item != null ? item.getImageResources() : new int[0])[0]);

        itemTitle.setTransitionName(getString(R.string.item_title) + position);
        itemTitle.setText(item != null ? item.getItemName().toUpperCase() : "");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageView_close) {
            Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.android_fadeout_animation);
            v.setAnimation(startAnimation);
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayRecipes(List<Recipe> recipes) {
        if (recipes != null && !recipes.isEmpty()) {
            RecipesAdapter adapter = new RecipesAdapter(recipes, this, Glide.with(this));
            recyclerView.setAdapter(adapter);
        } else {
            showToast("Recipes response is null!");
        }
    }

    @Override
    public void displayError(String s) {
        showToast(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        menuPresenter.onDestroy();
    }
}
