package com.mfathy.ladelicerestaurant.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.mfathy.ladelicerestaurant.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;

import static com.mfathy.mutilites.utils.ValidationUtils.checkNotNull;

/**
 * Created by Mohammed Fathy on 12/07/2018.
 * dev.mfathy@gmail.com
 * <p>
 * Repository data source implementation to control which data source the app will read from /write to.
 */

public class DataRepository implements DataSource {

    private static DataRepository INSTANCE = null;

    private final DataSource mRemoteDataSource;

    // Prevent direct instantiation.
    private DataRepository(@NonNull DataSource mRemoteDataSource) {
        this.mRemoteDataSource = checkNotNull(mRemoteDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param mRemoteDataSource the backend data source
     * @return the {@link DataRepository} instance
     */
    static DataRepository getInstance(DataSource mRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository(mRemoteDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(DataSource)} to create a new instance
     * next time it's called.
     */
    @VisibleForTesting
    static void clearInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return mRemoteDataSource.getRecipes();
    }
}
