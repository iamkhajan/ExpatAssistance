package com.example.khajan.expatassistance.di;

import com.example.khajan.expatassistance.MyMainActivity;
import com.example.khajan.expatassistance.di.module.AppModule;
import com.example.khajan.expatassistance.di.module.NetworkModule;

import dagger.Component;

/**
 * Created by Khajan on 11/17/17.
 */

@Component(modules={AppModule.class, NetworkModule.class})
public interface ExploreComponent {

    void inject(MyMainActivity activity);
}
