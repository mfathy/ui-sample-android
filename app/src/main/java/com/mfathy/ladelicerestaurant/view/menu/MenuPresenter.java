package com.mfathy.ladelicerestaurant.view.menu;

import android.support.annotation.NonNull;

import com.mfathy.ladelicerestaurant.data.DataRepository;
import com.mfathy.ladelicerestaurant.data.model.Recipe;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.mfathy.mutilites.utils.ValidationUtils.checkNotNull;

/**
 * Created by Mohammed Fathy on 14/07/2018.
 * dev.mfathy@gmail.com
 *
 * {@link MenuPresenter}
 */

public class MenuPresenter implements MenuContract.Presenter {

    private final DataRepository mDataRepository;
    private WeakReference<MenuContract.View> mView;

    MenuPresenter(DataRepository mDataRepository) {
        this.mDataRepository = mDataRepository;
    }

    @Override
    public void onBind(@NonNull MenuContract.View view) {
        checkNotNull(view);
        mView = new WeakReference<>(view);
    }

    @Override
    public void onDestroy() {
        if (mView != null)
            mView.clear();
    }

    @Override
    public void getRecipes() {
        getObservable().subscribeWith(getObserver());
    }

    private Observable<List<Recipe>> getObservable() {
        return mDataRepository.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<List<Recipe>> getObserver() {
        return new DisposableObserver<List<Recipe>>() {

            @Override
            public void onNext(@NonNull List<Recipe> movieResponse) {
                if (mView.get() != null)
                    mView.get().displayRecipes(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                if (mView.get() != null)
                    mView.get().displayError("Error getting recipes");
            }

            @Override
            public void onComplete() {
            }
        };
    }
}
