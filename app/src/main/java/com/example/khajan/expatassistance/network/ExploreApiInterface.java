package com.example.khajan.expatassistance.network;

import com.example.khajan.expatassistance.model.explore.ExploreMainData;

import io.reactivex.Observable;

/**
 * Created by Khajan on 11/23/17.
 */

public interface ExploreApiInterface {

    Observable<ExploreMainData> getExploreData();
}
