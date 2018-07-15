package com.mfathy.ladelicerestaurant.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfathy.ladelicerestaurant.R;
import com.mfathy.ladelicerestaurant.view.adapter.HomeItemsAdapter;
import com.mfathy.ladelicerestaurant.view.menu.MenuActivity;
import com.mfathy.ladelicerestaurant.view.model.HomeItem;
import com.mfathy.mutilites.utils.ActivityUtils;
import com.mfathy.mutilites.widgets.AppBarStateChangeListener;
import com.mfathy.mutilites.widgets.HorizontalLayoutManager;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeItemsAdapter.OnHomeItemInteractionListener {

    private AppCompatTextView textViewSubtitle;
    private DrawerLayout drawerLayout;
    private AppBarLayout appBarLayout;
    private float initMargin;
    private float initHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initRequiredDisplayMargins();

        Toolbar toolbar = setupToolBar();

        textViewSubtitle = findViewById(R.id.textView_Subtitle);
        appBarLayout = findViewById(R.id.app_bar);

        setupAppBarTitleAnimation();

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView(recyclerView);
        adjustRecyclerView(recyclerView);

    }

    private Toolbar setupToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (getSupportActionBar() != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        return toolbar;
    }

    private void setupAppBarTitleAnimation() {
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int i) {
                if (state.name().equals(State.COLLAPSED.name())) {
                    Animation centerSwipe = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.android_center_animation);
                    centerSwipe.setFillAfter(true);
                    textViewSubtitle.startAnimation(centerSwipe);
                } else if (state.name().equals(State.EXPANDED.name())) {
                    Animation leftSwipe = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.android_start_animation);
                    leftSwipe.setFillAfter(true);
                    textViewSubtitle.startAnimation(leftSwipe);
                } else {
                    // do nothing
                }
            }
        });
    }

    private void initRequiredDisplayMargins() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;
        initMargin = -density * 100;
        initHeight = 200 * density;
    }

    private void initRecyclerView(RecyclerView recyclerView) {

        HorizontalLayoutManager horizontalLayoutManager = new HorizontalLayoutManager(this);
        horizontalLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalLayoutManager.setScrollEnabled(false);

        recyclerView.setLayoutManager(horizontalLayoutManager);

        HomeItemsAdapter adapter = new HomeItemsAdapter(initHomeItemsList(), this, this);
        recyclerView.setAdapter(adapter);
    }

    private void adjustRecyclerView(final RecyclerView recyclerView) {

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) recyclerView.getLayoutParams();
                lp.topMargin = (int) ((appBarLayout.getTotalScrollRange() - Math.abs(verticalOffset)) / (double) appBarLayout.getTotalScrollRange() * initMargin);
                recyclerView.setLayoutParams(lp);

                int imageHeight = (int) ((Math.abs(verticalOffset)) / (double) appBarLayout.getTotalScrollRange() * initHeight);
                ((HomeItemsAdapter) recyclerView.getAdapter()).changeImageWidth(imageHeight, imageHeight == initHeight);

                HorizontalLayoutManager manager = (HorizontalLayoutManager) recyclerView.getLayoutManager();
                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    manager.setScrollEnabled(true);
                } else {
                    manager.setScrollEnabled(false);
                }
            }
        });

    }

    private List<HomeItem> initHomeItemsList() {
        List<HomeItem> homeItems = new ArrayList<>();
        homeItems.add(new HomeItem(getString(R.string.label_about), getString(R.string.desc_about), new int[]{R.drawable.img_about_01, R.drawable.img_about_02}));
        homeItems.add(new HomeItem(getString(R.string.label_recipes), getString(R.string.desc_recipes), new int[]{R.drawable.img_menu_01, R.drawable.img_menu_01}));
        homeItems.add(new HomeItem(getString(R.string.label_call), getString(R.string.desc_call), new int[]{R.drawable.img_contact_01, R.drawable.img_contact_01}));
        return homeItems;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            HomeItem homeItem = new HomeItem(getString(R.string.label_about), getString(R.string.desc_about), new int[]{R.drawable.img_about_01, R.drawable.img_about_02});
            Bundle bundle = new Bundle();
            bundle.putParcelable(HomeItem.class.getSimpleName(), homeItem);
            ActivityUtils.goToActivity(this, AboutActivity.class, bundle);
        } else if (id == R.id.nav_recipes) {
            HomeItem homeItem = new HomeItem(getString(R.string.label_recipes), getString(R.string.desc_recipes), new int[]{R.drawable.img_menu_01, R.drawable.img_menu_01});
            Bundle bundle = new Bundle();
            bundle.putParcelable(HomeItem.class.getSimpleName(), homeItem);
            ActivityUtils.goToActivity(this, MenuActivity.class, bundle);

        } else if (id == R.id.nav_call) {
            HomeItem homeItem = new HomeItem(getString(R.string.label_call), getString(R.string.desc_call), new int[]{R.drawable.img_contact_01, R.drawable.img_contact_01});
            Bundle bundle = new Bundle();
            bundle.putParcelable(HomeItem.class.getSimpleName(), homeItem);
            ActivityUtils.goToActivity(this, ContactActivity.class, bundle);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onItemInteraction(HomeItem homeItem, View itemView, int adapterPosition) {

        switch (adapterPosition) {
            case 0:
                goToActivity(homeItem, itemView, adapterPosition, AboutActivity.class);
                break;
            case 1:
                goToActivity(homeItem, itemView, adapterPosition, MenuActivity.class);
                break;
            case 2:
                goToActivity(homeItem, itemView, adapterPosition, ContactActivity.class);
                break;
        }

    }

    private void goToActivity(HomeItem homeItem, View itemView, int adapterPosition, Class<?> targetClass) {
        ImageView itemImage = itemView.findViewById(R.id.item_image);
        TextView itemTitle = itemView.findViewById(R.id.item_title);
        TextView itemDescription = itemView.findViewById(R.id.item_description);

        Intent intent = new Intent(this, targetClass);
        intent.putExtra(HomeItem.class.getSimpleName(), homeItem);
        intent.putExtra("adapterPosition", adapterPosition);

        Pair<View, String> p1 = Pair.create(itemImage, getString(R.string.item_image) + adapterPosition);
        Pair<View, String> p2 = Pair.create(itemTitle, getString(R.string.item_title) + adapterPosition);
        Pair<View, String> p3 = Pair.create(itemDescription, getString(R.string.item_description) + adapterPosition);

        ActivityOptionsCompat options = makeSceneTransitionAnimation(this, p1, p2, p3);
        startActivity(intent, options.toBundle());
    }

}
