package com.mfathy.ladelicerestaurant.data.remote;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.mfathy.ladelicerestaurant.data.DataSource;
import com.mfathy.ladelicerestaurant.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohammed Fathy on 15/03/2018.
 * dev.mfathy@gmail.com
 * <p>
 * Remote data source which provides data from remote / local mock server.
 */
public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE = null;
    private ApiEndpoints mApiEndpoints;

    // private constructor to prevent direct instantiation of the class from other classes.
    private RemoteDataSource(Context context) {
        if (mApiEndpoints == null) {
            final OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(new OfflineMockInterceptor(context))
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://mock.api")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

            mApiEndpoints = retrofit.create(ApiEndpoints.class);
        }
    }

    /**
     * @param context param to initialize mock server.
     * @return INSTANCE of remote data source
     * double checked locking principle is used. In this approach,
     * the synchronized block is used inside the if condition with an additional check to ensure
     * that only one instance of singleton class is created.
     */
    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteDataSource(context);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    static void clearInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return mApiEndpoints.getRecipes();
    }
}
