package com.example.callimedia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Class_teaching extends Fragment {


    private View root_;
    private ImageView ima;

    private Class_teaching_2 fra;
    private void showFragment(Fragment fragment) {
        FragmentManager fManager = getParentFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.teaching_fragment, fragment);
        fTransaction.commit();
    }


    private void init(){

        fra=new Class_teaching_2();
        ima=(ImageView) root_.findViewById(R.id.teching_1);
        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(fra);
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_class_teaching, null);
        init();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }
}