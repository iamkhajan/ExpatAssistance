package com.example.khajan.expatassistance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khajan.expatassistance.model.explore.ExploreMainData;
import com.example.khajan.expatassistance.utils.Utils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.khajan.expatassistance.utils.Constants.ARG_COLOR;
import static com.example.khajan.expatassistance.utils.Constants.ARG_TEXT;

/**
 * Created by Khajan on 11/16/17.
 */

public class ExploreFragment extends Fragment {

    private String mText;
    private int mColor;

    private View mContent;
    private TextView mTextView;
    private RecyclerView mExploreRecyclerView;
    private ExploreAdapter mExploreAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public static Fragment newInstance(String text, int color) {
        Fragment frag = new ExploreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // retrieve text and color from bundle or savedInstanceState
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            mText = args.getString(ARG_TEXT);
            mColor = args.getInt(ARG_COLOR);
        } else {
            mText = savedInstanceState.getString(ARG_TEXT);
            mColor = savedInstanceState.getInt(ARG_COLOR);
        }


        // initialize views
        mContent = view.findViewById(R.id.explore_fragment_content);
        mTextView =view.findViewById(R.id.text);
        mExploreRecyclerView=view.findViewById(R.id.main_recycler_view);
        mExploreRecyclerView.setHasFixedSize(true);


        mExploreAdapter=new ExploreAdapter(getActivity());
        mLayoutManager=new LinearLayoutManager(getActivity());

        mExploreRecyclerView.setLayoutManager(mLayoutManager);
        mExploreRecyclerView.setAdapter(mExploreAdapter);

        mExploreRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));


        // set text and background color
        mTextView.setText(mText);
     //   mContent.setBackgroundColor(mColor);


        Observable<ExploreMainData> exploreMainDataObservable
                =Observable.just(Utils.loadJSONFromAsset(getActivity()));
        exploreMainDataObservable.subscribe(new Observer<ExploreMainData>() {
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
        return inflater.inflate(R.layout.explore_fragment,container,false);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_TEXT, mText);
        outState.putInt(ARG_COLOR, mColor);
        super.onSaveInstanceState(outState);
    }
}
