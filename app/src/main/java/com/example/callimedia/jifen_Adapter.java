package com.example.callimedia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class jifen_Adapter extends RecyclerView.Adapter<jifen_Adapter.ViewHolder> {


    private List<jifen_item> datelist;



    public jifen_Adapter(List<jifen_item>x){datelist=x;}
    @NonNull
    @Override
    public jifen_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jifen_item, parent, false);
        return new jifen_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull jifen_Adapter.ViewHolder holder, int position) {
        jifen_item item = datelist.get(position);

        // 设置文本
        holder.xunzhang.setText("共获得  " + item.xunzhang + "  枚勋章");
        holder.name.setText(item.name);
        holder.jifen.setText(String.valueOf(item.jifen));
        if(position==0)
            holder.paiming.setImageResource(R.drawable.number1_paiming_07_2_1);
        else if(position==1)
            holder.paiming.setImageResource(R.drawable.number2_paiming_07_2_1);
        else if(position==2)
            holder.paiming.setImageResource(R.drawable.number3_paiming_07_2_1);

        holder.touxiang.setImageResource(item.touxiang_id);
    }

    @Override
    public int getItemCount() {
        return datelist.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView touxiang,paiming;
        private TextView jifen,xunzhang,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            touxiang=itemView.findViewById(R.id.jifen_touxiang);
            jifen=itemView.findViewById(R.id.jifen_jifen);
            xunzhang=itemView.findViewById(R.id.jifen_xunzhang);
            name=itemView.findViewById(R.id.jifen_name);
            paiming=itemView.findViewById(R.id.paiming);
        }
    }
}
