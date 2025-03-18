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

public class Class_interaction extends Fragment {


    private View root_;
    private RecyclerView re_;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_class_interaction, null);
        init();
        lv();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }

    private void init() {
        re_=(RecyclerView) root_.findViewById(R.id.rec);
    }

    public void lv(){
        List<re_item> PeopleList=new ArrayList<>();//创建装People的List
        re_item res;
        res=new re_item("知识展示");
        PeopleList.add(res);
        res=new re_item("汉字查询");
        PeopleList.add(res);
        res=new re_item("教学资源");
        PeopleList.add(res);
        res=new re_item("趣味游戏");
        PeopleList.add(res);
        res=new re_item("课堂回答");
        PeopleList.add(res);
        res=new re_item("课堂教学");
        PeopleList.add(res);

        Home_pageAdapter stringAdapter = new Home_pageAdapter(PeopleList);
        //配置适配器
        re_.setAdapter(stringAdapter);
        //配置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        re_.setLayoutManager(layoutManager);

    }
}