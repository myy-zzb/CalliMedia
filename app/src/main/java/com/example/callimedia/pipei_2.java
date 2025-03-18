package com.example.callimedia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class pipei_2 extends Fragment {

    void init(){




    }
    private View root_;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_pipei_2, null);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }
}