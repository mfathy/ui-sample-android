package com.mfathy.ladelicerestaurant.view.menu;

import android.support.annotation.NonNull;

import com.mfathy.ladelicerestaurant.data.DataRepository;
import com.mfathy.ladelicerestaurant.data.model.Recipe;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Mohammed Fathy on 15/07/2018.
 * dev.mfathy@gmail.com
 */
public class MenuPresenterTest {

    private static List<Recipe> RECIPES;

    @Mock
    private DataRepository mDataRepository;
    @Mock
    private MenuContract.View mView;
    private MenuPresenter menuPresenter;
    private TestScheduler testScheduler;

    public static void givenRecipesInitialized() {
        RECIPES = new ArrayList<>();
        RECIPES.add(new Recipe(0));
        RECIPES.add(new Recipe(1));
        RECIPES.add(new Recipe(2));
        RECIPES.add(new Recipe(3));
        RECIPES.add(new Recipe(4));
        RECIPES.add(new Recipe(5));
    }

    /**
     * Initializing RxAndroidPlugins with a different Scheduler before the tests are run.
     */
    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();

        menuPresenter = new MenuPresenter(mDataRepository);
        menuPresenter.onBind(mView);
    }

    @Test
    public void getRecipes() throws Exception {
        givenRecipesInitialized();

        doReturn(Observable.just(RECIPES)).when(mDataRepository).getRecipes();
        menuPresenter.getRecipes();
        testScheduler.triggerActions();

        verify(mView).displayRecipes(RECIPES);
    }

}