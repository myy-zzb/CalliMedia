package com.example.callimedia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Student_situation extends Fragment {


    private RecyclerView re_;
    private View root_;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_student_situation, null);
        init();
        lv();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }

    private void init(){
        re_=root_.findViewById(R.id.rec_show);
    }
    public void lv(){
        List<show_item> PeopleList=new ArrayList<>();//创建装People的List
        show_item res;
        res=new show_item(1);
        PeopleList.add(res);
        res=new show_item(2);
        PeopleList.add(res);


        show_Adapter stringAdapter = new show_Adapter(PeopleList);
        //配置适配器
        re_.setAdapter(stringAdapter);
        //配置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        re_.setLayoutManager(layoutManager);

    }

}