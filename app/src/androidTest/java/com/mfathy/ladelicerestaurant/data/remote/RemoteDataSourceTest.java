package com.mfathy.ladelicerestaurant.data.remote;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mfathy.ladelicerestaurant.data.model.Recipe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mohammed Fathy on 15/07/2018.
 * dev.mfathy@gmail.com
 */
@RunWith(AndroidJUnit4.class)
public class RemoteDataSourceTest {

    private RemoteDataSource mRemoteDataSource;

    @Before
    public void setUp() throws Exception {
        mRemoteDataSource = RemoteDataSource.getInstance(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        RemoteDataSource.clearInstance();
    }

    @Test
    public void getInstance() throws Exception {
        assertNotNull(mRemoteDataSource);
    }

    @Test
    public void test_getRecipes_Should_Return_Four_Recipes() throws Exception {

        TestObserver<List<Recipe>> testObserver = new TestObserver<>();
        mRemoteDataSource.getRecipes().subscribe(testObserver);
        List<Recipe> result = testObserver.values().get(0);
        testObserver.assertNoErrors();
        assertTrue(result.size() >= 4);
    }

}