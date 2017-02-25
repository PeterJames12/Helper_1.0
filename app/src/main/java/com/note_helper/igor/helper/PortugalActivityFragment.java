package com.note_helper.igor.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Igor Gnes on 20.02.17.
 */

public class PortugalActivityFragment extends Fragment {

    public static final String TAG = "PortugalActivityFragment";

    public PortugalActivityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_portugues_words, null);
    }
}
