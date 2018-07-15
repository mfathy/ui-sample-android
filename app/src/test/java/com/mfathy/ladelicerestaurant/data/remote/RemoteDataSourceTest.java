package com.mfathy.ladelicerestaurant.data.remote;

import com.mfathy.ladelicerestaurant.data.DataSource;
import com.mfathy.ladelicerestaurant.data.model.Recipe;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mohammed Fathy on 15/07/2018.
 * dev.mfathy@gmail.com
 */
public class RemoteDataSourceTest {

    private DataSource mRemoteDataSource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mRemoteDataSource = new RemoteDataSourceStub();
    }

    @Test
    public void getInstance() throws Exception {
        assertNotNull(mRemoteDataSource);
    }

    @Test
    public void test_getRecipes_Should_Return_6_Elements() throws Exception {
        RemoteDataSourceStub.givenRecipesInitialized();

        TestObserver<List<Recipe>> testObserver = new TestObserver<>();
        mRemoteDataSource.getRecipes().subscribe(testObserver);
        List<Recipe> result = testObserver.values().get(0);
        testObserver.assertNoErrors();
        assertTrue(result.size() == 6);
    }

    @Test
    public void test_getRecipes_Should_Return_No_Elements() throws Exception {
        RemoteDataSourceStub.givenRecipesNotInitialized();

        TestObserver<List<Recipe>> testObserver = new TestObserver<>();
        mRemoteDataSource.getRecipes().subscribe(testObserver);
        List<Recipe> result = testObserver.values().get(0);
        testObserver.assertNoErrors();
        assertTrue(result.size() == 0);
    }

}