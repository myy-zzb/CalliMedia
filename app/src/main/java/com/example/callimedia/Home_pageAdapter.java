package com.example.callimedia;

import android.annotation.SuppressLint;
import android.app.Activity;
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

public class Home_pageAdapter extends RecyclerView.Adapter<Home_pageAdapter.ViewHolder>{

    private List<re_item>datelist;



    public Home_pageAdapter(List<re_item>x){datelist=x;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fun_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_vt.setText(datelist.get(position).getname());


        if(position==0)
        {
            holder.fenmian.setImageResource(R.drawable.zhishizhanshi);
        }
        else if(position==1){
            holder.fenmian.setImageResource(R.drawable.hanzichaxun);
        }
        else if(position==2){holder.fenmian.setImageResource(R.drawable.yunduanziyuan);}
        else if(position==3){holder.fenmian.setImageResource(R.drawable.bihuawenda);}
        else if(position==4){holder.fenmian.setImageResource(R.drawable.ketangwenda);}
        else if(position==5){holder.fenmian.setImageResource(R.drawable.ketangjiaoxue);}


        holder.im_bt.setOnClickListener(v -> {
            // 根据位置(position)执行不同的操作
            Intent intent;
            switch (position) {
                case 0:
                    intent= new Intent(v.getContext(),Teaching_resources.class);
                    intent.putExtra("yemian","zhishizhanshi"); // 传递标志数据
                    v.getContext().startActivity(intent);

//                    ((Activity)(v.getContext())).finish();

//                    Toast.makeText(v.getContext(), "Button in item " + position + " clicked!", Toast.LENGTH_SHORT).show();
                    // 执行具体操作1
                    break;
                case 1:
                    intent= new Intent(v.getContext(),Teaching_resources.class);
                    intent.putExtra("yemian","hanzichaxun"); // 传递标志数据
                    v.getContext().startActivity(intent);

//                    ((Activity)(v.getContext())).finish();

//                    Toast.makeText(v.getContext(), "Button in item " + position + " clicked!", Toast.LENGTH_SHORT).show();
                    // 执行具体操作2
                    break;
                case 2:
                    intent= new Intent(v.getContext(),Teaching_resources.class);
                    intent.putExtra("yemian", "yunduanziyuan"); // 传递标志数据
                    v.getContext().startActivity(intent);

//                    ((Activity)(v.getContext())).finish();
                     // 执行具体操作2
                    break;
                case 3:
                    intent= new Intent(v.getContext(),Teaching_resources.class);
                    intent.putExtra("yemian","quweiyouxi"); // 传递标志数据
                    v.getContext().startActivity(intent);

//                    ((Activity)(v.getContext())).finish();
//                    Toast.makeText(v.getContext(), "Button in item " + position + " clicked!", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    intent= new Intent(v.getContext(),Teaching_resources.class);
                    intent.putExtra("yemian","ketanghuida"); // 传递标志数据
                    v.getContext().startActivity(intent);

//                    ((Activity)(v.getContext())).finish();
//                    Toast.makeText(v.getContext(), "Button in item " + position + " clicked!", Toast.LENGTH_SHORT).show();
                    // 执行具体操作2
                    break;
                case 5:
                    intent= new Intent(v.getContext(),Teaching_resources.class);
                    intent.putExtra("yemian","ketangjiaoxue"); // 传递标志数据
                    v.getContext().startActivity(intent);

//                    Toast.makeText(v.getContext(), "Button in item " + position + " clicked!", Toast.LENGTH_SHORT).show();
                    // 执行具体操作2
                    break;
                case 6:
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
        private ImageView tv_,fenmian;
        private ImageButton im_bt;
        private TextView tv_vt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_=itemView.findViewById(R.id.tv);
            tv_.setImageResource(R.drawable.tv);
            im_bt=itemView.findViewById(R.id.game_button);
            im_bt.setImageResource(R.drawable.button_enter_04);
            tv_vt=itemView.findViewById(R.id.game_tv);
            fenmian=itemView.findViewById(R.id.fenmian);
        }
    }

}
