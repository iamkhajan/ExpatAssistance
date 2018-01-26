package com.example.khajan.expatassistance;

import android.app.Application;

import com.example.khajan.expatassistance.di.DaggerExploreComponent;
import com.example.khajan.expatassistance.di.ExploreComponent;
import com.example.khajan.expatassistance.di.module.AppModule;
import com.example.khajan.expatassistance.di.module.NetworkModule;

import static com.example.khajan.expatassistance.utils.Constants.BASE_URL;

/**
 * Created by Khajan on 11/17/17.
 */

public class ExplorerApp extends Application {

    private ExploreComponent mExploreComponent;
    @Override
    public void onCreate() {
        super.onCreate();


        // Dagger%COMPONENT_NAME%
        mExploreComponent = DaggerExploreComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .networkModule(new NetworkModule(BASE_URL))
                .build();
    }

    public ExploreComponent getmExploreComponent() {
        return mExploreComponent;
    }
}
