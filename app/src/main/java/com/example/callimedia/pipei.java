package com.example.callimedia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class pipei extends Fragment {

    void init(){
        root_.findViewById(R.id.bihua_tanchuang).setVisibility(View.GONE);
        root_.findViewById(R.id.bihua_tanchuang_kai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root_.findViewById(R.id.bihua_tanchuang).setVisibility(View.VISIBLE);
                root_.findViewById(R.id.bihua_xiala).setVisibility(View.GONE);
            }
        });

        root_.findViewById(R.id.bihua_tanchuang).findViewById(R.id.shanghua).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root_.findViewById(R.id.bihua_tanchuang).setVisibility(View.GONE);
                root_.findViewById(R.id.bihua_xiala).setVisibility(View.VISIBLE);
            }
        });



    }
    private View root_;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_pipei, null);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }
}