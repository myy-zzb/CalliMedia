package com.example.callimedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class biaoji_grid_Adapter extends BaseAdapter {
    private Context context;
    private List<biaoji_grid_item>datalist;

    public biaoji_grid_Adapter(Context context, List<biaoji_grid_item> data) {
        this.context = context;
        this.datalist = data;
    }
    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public biaoji_grid_item getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.biaoji_1_grid_item, parent, false);
        }

        // 获取当前视图中的 TextView，并设置数据
        TextView textView = convertView.findViewById(R.id.biaoji_1_name);
        textView.setText(datalist.get(position).name);
        ImageView imageView = convertView.findViewById(R.id.biaoji_1_tx);
        imageView.setImageBitmap(datalist.get(position).image);


        return convertView;
    }
}
