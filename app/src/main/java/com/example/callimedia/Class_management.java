package com.example.callimedia;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Class_management extends Fragment {


    ImageView class_list,student_marker;

    private View root_;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_class_management, null);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }

    private void init(){
        class_list=root_.findViewById(R.id.Class_list);
        class_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(),Class_list.class);
                startActivity(intent);

//                getActivity().finish();
            }
        });


        student_marker=root_.findViewById(R.id.Student_marker);
        student_marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(),Student_biaoji.class);
                startActivity(intent);

//                getActivity().finish();
            }
        });

    }



}