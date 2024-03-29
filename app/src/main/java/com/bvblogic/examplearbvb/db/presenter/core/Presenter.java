package com.bvblogic.examplearbvb.db.presenter.core;


import android.app.Activity;
import android.content.Context;

import com.bvblogic.examplearbvb.db.core.AppDatabase;
import com.bvblogic.examplearbvb.db.datamanager.core.DBView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by hanz on 08.05.2018.
 */
@EBean
public abstract class Presenter<T> implements DBView<T> {

    @RootContext
    protected Activity activity;

    protected AppDatabase appDatabase;

    @AfterInject
    public void init() {
        if(isActivityAlive()){
            appDatabase = AppDatabase.getAppDatabase(activity);
        }
    }

    public boolean isActivityAlive(){
        return activity != null;
    }

    public AppDatabase getAppDatabaseWithCustomContext(Context context){
        return AppDatabase.getAppDatabase(context);
    }
    @Override
    public void showWait() {
        // ((MainActivity) baseFragment.getActivity()).showProgressBar();
    }

    @Override
    public void hideWait() {
        //  ((MainActivity) baseFragment.getActivity()).hideProgressBar();
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
