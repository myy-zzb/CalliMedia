package com.example.callimedia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class show_Adapter extends RecyclerView.Adapter<show_Adapter.ViewHolder>{

    private List<show_item>datelist;



    public show_Adapter(List<show_item>x){datelist=x;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position==1){
            holder.tv_title.setText("进入积分模块");
        }

        if(position==0)
            holder.fenmian.setImageResource(R.drawable.xuexizhanshi);
        else
            holder.fenmian.setImageResource(R.drawable.jifenpaiming);


        holder.im_bt.setOnClickListener(v -> {
            // 根据位置(position)执行不同的操作
            switch (position) {
                case 0:
//                    Toast.makeText(v.getContext(), "Button in item " + position + " clicked!", Toast.LENGTH_SHORT).show();
                    // 执行具体操作1
                    Intent intent = new Intent(v.getContext(), study_show.class);
                    v.getContext().startActivity(intent);
//                    ((Activity)(v.getContext())).finish();
                    break;
                case 1:
                    jifen_Dialog md = new jifen_Dialog (v.getContext(),R.style.mydialog); //传入值
                    md.show();//调用对话框类
//                    Toast.makeText(v.getContext(), "Button in item " + position + " clicked!", Toast.LENGTH_SHORT).show();
                    // 执行具体操作2
                    break;
                default:
//                    Toast.makeText(v.getContext(), "Button in item " + position + " clicked!", Toast.LENGTH_SHORT).show();
                    // 执行默认操作
                    break;
            }
        });

    }


    @Override
    public int getItemCount() {
        return datelist.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView tv_,bg;
        private ImageButton im_bt;

        private ImageView fenmian;

        private TextView tv_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.show_title);
            tv_=itemView.findViewById(R.id.show_tv);
            im_bt=itemView.findViewById(R.id.show_button);
            im_bt.setImageResource(R.drawable.button_enter_04);
            bg=itemView.findViewById(R.id.show_bg);
            fenmian=itemView.findViewById(R.id.fenmian1);
        }
    }

}
