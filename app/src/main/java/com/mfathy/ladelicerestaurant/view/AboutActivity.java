package com.mfathy.ladelicerestaurant.view;

import android.app.SharedElementCallback;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mfathy.ladelicerestaurant.R;
import com.mfathy.ladelicerestaurant.view.model.HomeItem;

import java.util.List;


public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView imageView01 = findViewById(R.id.imageView_01);
        final TextView itemTitle = findViewById(R.id.item_title);
        final TextView itemDescription = findViewById(R.id.item_description);
        ImageView imageView02 = findViewById(R.id.imageView_02);

        final ImageView imageView = findViewById(R.id.imageView_close);

        fillViewWithData(imageView01, itemTitle, itemDescription, imageView02);

        setSharedElementEnterCallback(imageView);

        imageView.setOnClickListener(this);

    }

    private void fillViewWithData(ImageView imageView01, TextView itemTitle, TextView itemDescription, ImageView imageView02) {
        HomeItem item = getIntent().getParcelableExtra(HomeItem.class.getSimpleName());
        int position = getIntent().getIntExtra("adapterPosition", 0);
        imageView01.setTransitionName(getString(R.string.item_image) + position);
        imageView01.setImageResource((item != null ? item.getImageResources() : new int[0])[0]);

        itemTitle.setTransitionName(getString(R.string.item_title) + position);
        itemTitle.setText(item != null ? item.getItemName().toUpperCase() : "");

        itemDescription.setTransitionName(getString(R.string.item_description) + position);
        itemDescription.setText(item != null ? item.getItemDescription() : "");

        imageView02.setImageResource((item != null ? item.getImageResources() : new int[0])[1]);
    }

    private void setSharedElementEnterCallback(final ImageView imageView) {
        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementEnd(List<String> sharedElementNames,
                                           List<View> sharedElements,
                                           List<View> sharedElementSnapshots) {

                Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.android_fadein_animation);
                imageView.setAnimation(startAnimation);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageView_close) {
            Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.android_fadeout_animation);
            v.setAnimation(startAnimation);
            ActivityCompat.finishAfterTransition(this);
        }
    }
}
