package com.mfathy.ladelicerestaurant.data;

import android.content.Context;

import com.mfathy.ladelicerestaurant.data.remote.RemoteDataSource;

import static com.mfathy.mutilites.utils.ValidationUtils.checkNotNull;

/**
 * Created by Mohammed Fathy on 12/07/2018.
 * dev.mfathy@gmail.com
 *
 * <p>
 * Simple dependency injection class.
 */
public class Injection {
    public static DataRepository provideDataRepository(Context context) {
        checkNotNull(context);
        return DataRepository.getInstance(RemoteDataSource.getInstance(context));
    }
}
