package com.example.khajan.expatassistance;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import static com.example.khajan.expatassistance.utils.Constants.ARG_COLOR;
import static com.example.khajan.expatassistance.utils.Constants.ARG_TEXT;

/**
 * Created by Khajan on 11/28/17.
 */

public class SavedExploreFragment extends Fragment {



    public static Fragment newInstance(String text, int color) {
        Fragment frag = new SavedExploreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        args.putInt(ARG_COLOR, color);
        frag.setArguments(args);
        return frag;
    }
}
