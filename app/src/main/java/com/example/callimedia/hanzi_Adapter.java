package com.example.callimedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class hanzi_Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<hanzi_item> datalist;

    public hanzi_Adapter(Context context, ArrayList<hanzi_item> data) {
        this.context = context;
        this.datalist = data;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public hanzi_item getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hanzi_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.hanzi_item);

        // 获取 URL
        String url = datalist.get(position).url;

        // 使用 Glide 加载图片
        Glide.with(context)
                .load(url)
                .into(imageView);

        return convertView;
    }
}
