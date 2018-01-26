package com.example.khajan.expatassistance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khajan.expatassistance.model.explore.Event;
import com.example.khajan.expatassistance.model.explore.ExploreMainData;
import com.example.khajan.expatassistance.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import static com.example.khajan.expatassistance.utils.Constants.ARG_COLOR;
import static com.example.khajan.expatassistance.utils.Constants.ARG_TEXT;

/**
 * Created by Khajan on 1/17/18.
 */

public class ConcernedFragment extends Fragment{


    private RecyclerView mExploreRecyclerView;
    private ExploreAdapter mExploreAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    public static Fragment newInstance(String text, int color) {
        Fragment frag = new ConcernedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mExploreRecyclerView=view.findViewById(R.id.main_recycler_view);
        mExploreRecyclerView.setHasFixedSize(true);

        mExploreAdapter=new ExploreAdapter(getActivity());
        mLayoutManager=new LinearLayoutManager(getActivity());

        mExploreRecyclerView.setLayoutManager(mLayoutManager);
        mExploreRecyclerView.setAdapter(mExploreAdapter);

        mExploreRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));



        Observable<ExploreMainData> exploreMainDataObservable
                =Observable.just(Utils.loadJSONFromAsset(getActivity()));
        exploreMainDataObservable.flatMap(new Function<ExploreMainData, ObservableSource<ExploreMainData>>() {
            @Override
            public ObservableSource<ExploreMainData> apply(ExploreMainData exploreMainData) throws Exception {
                List<Event> eventList=new ArrayList<>();
                for (Event event:exploreMainData.getEvents()) {
                    if(event.isFavorite())
                        eventList.add(event);

                }
                exploreMainData.setEvents(eventList);
                return Observable.just(exploreMainData);
            }
        }).subscribe(new Observer<ExploreMainData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ExploreMainData exploreMainData) {
                mExploreAdapter.setData(exploreMainData.getEvents());
                mExploreAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.concerned_fragment,container,false);
    }
}
