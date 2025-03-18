package com.example.callimedia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class bt_Adapter extends RecyclerView.Adapter<bt_Adapter.ViewHolder> {

    private List<bt_item> datelist;

    private FragmentManager fragmentManager;

    private Class_answer fra1;
    private Cloud_resource fra2;
    private Class_teaching fra3;
    private bihua fra4;
    private Chinese_character_query fra5;

    private show_know fra6;
    private Activity mainActivity;


    bt_Adapter(List<bt_item>x,FragmentManager y,Activity z)
    {
        mainActivity=z;
        datelist=x;
        fragmentManager=y;
        fra1=new Class_answer();
        fra2=new Cloud_resource();
        fra3=new Class_teaching();
        fra4=new bihua();
        fra5=new Chinese_character_query();
        fra6=new show_know();
    }

    void chu(@NonNull bt_Adapter.ViewHolder holder,int position){
        for(int i=0;i<datelist.size();i++){
            if(i==position)continue;
            bt_item item = datelist.get(i);
            holder.anniu.setBackgroundResource(item.id);
            notifyItemChanged(i); // 刷新特定item
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fManager = fragmentManager;
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.teaching_fragment, fragment);
        fTransaction.commit();
    }
    @NonNull
    @Override
    public bt_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_item, parent, false);
        return new bt_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bt_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        bt_item item = datelist.get(position);
        holder.anniu.setBackgroundResource(item.id);
        holder.anniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.findViewById(R.id.title_bihua).setVisibility(View.GONE);
                mainActivity.findViewById(R.id.title_pipei).setVisibility(View.GONE);
                if(position==0) {
                    chu(holder,position);
                    showFragment(fra1);
                    holder.anniu.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    holder.anniu.setBackgroundResource(R.drawable.ketanghui_05_aft);
                }
                else if(position==1) {
                    chu(holder,position);
                    showFragment(fra2);
                    holder.anniu.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    holder.anniu.setBackgroundResource(R.drawable.yunduan_05_aft);
                }
                else if(position==2) {
                    chu(holder,position);
                    showFragment(fra3);
                    holder.anniu.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    holder.anniu.setBackgroundResource(R.drawable.ketang_05_aft);
                }
                else if (position==3) {
                    chu(holder,position);
//                    bihua_dialog dialog_bihua=new bihua_dialog(mainActivity,R.style.mydialog);
//                    dialog_bihua.show();

                    showFragment(fra4);
                    holder.anniu.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    holder.anniu.setBackgroundResource(R.drawable.quwei_05_aft);
                }
                else if(position==4) {
                    chu(holder,position);
                    showFragment(fra5);
                    holder.anniu.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    holder.anniu.setBackgroundResource(R.drawable.hanzi_05_aft);
                }
                else if(position==5) {
                    chu(holder,position);
                    showFragment(fra6);
                    holder.anniu.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    holder.anniu.setBackgroundResource(R.drawable.zhishi_05_aft);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton anniu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            anniu=itemView.findViewById(R.id.bt_item);
        }
    }
}
