package com.mfathy.mutilites.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Mohammed Fathy on 13/07/2018.
 * dev.mfathy@gmail.com
 *
 * {@link ActivityUtils} use this class when you want to go from activity to activity, add fragment or replace fragment.
 */

public class ActivityUtils {
    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, boolean addToBackStack) {
        ValidationUtils.checkNotNull(fragmentManager);
        ValidationUtils.checkNotNull(fragment);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commitAllowingStateLoss();
    }

    public static void addFragmentToActivityWithoutReplace(@NonNull FragmentManager fragmentManager,
                                                           @NonNull Fragment fragment, int frameId, boolean addToBackStack) {
        ValidationUtils.checkNotNull(fragmentManager);
        ValidationUtils.checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commitAllowingStateLoss();
    }


    public static void goToActivity(Context packageContext, Class<?> cls, Bundle extras) {
        Intent intent = new Intent(packageContext, cls);
        if (extras != null) {
            intent.putExtras(extras);
        }
        packageContext.startActivity(intent);
    }

    public static void openUrl(Context packageContext, String url) {
        if (url == null || !Patterns.WEB_URL.matcher(url).matches()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
        packageContext.startActivity(intent);
    }


    public static void goToActivityForResult(Context packageContext, Class<?> cls, Bundle extras, int requestCode) {
        Intent intent = new Intent(packageContext, cls);
        if (extras != null) {
            intent.putExtras(extras);
        }
        ((Activity) packageContext).startActivityForResult(intent, requestCode);
    }

    public static void showToast(View view, String message) {
        Toast toast = Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT);
        TextView tvToast = toast.getView().findViewById(android.R.id.message);
        tvToast.setTypeface(Typeface.DEFAULT);
        toast.show();
    }

    public static void shareIntent(Context context, String shareMessage) {
        Intent in = new Intent(Intent.ACTION_SEND);
        in.setType("text/plain");
        in.putExtra(Intent.EXTRA_TEXT, shareMessage);
        context.startActivity(Intent.createChooser(in, "مشاركه"));
    }
}
