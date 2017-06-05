package com.note_helper.igor.helper.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.note_helper.igor.helper.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SupportActivityFragment extends Fragment {

    public static final String TAG = "SupportActivityFragment";

    public SupportActivityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_support, null);
    }
}
