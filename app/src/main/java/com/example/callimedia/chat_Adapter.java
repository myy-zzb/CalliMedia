package com.example.callimedia;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class chat_Adapter extends RecyclerView.Adapter<chat_Adapter.ViewHolder>{



    List<chat_item> datelist;

    public chat_Adapter(List<chat_item> datalist) {
        this.datelist = datalist;
    }

    @NonNull
    @Override
    public chat_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new chat_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chat_Adapter.ViewHolder holder, int position) {
        chat_item item = datelist.get(position);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i=0;i<item.text.size();i++) {
            // 创建 SpannableString

            chat_text chatText = item.text.get(i);
            SpannableString spannableString = new SpannableString(chatText.text);

            // 设置颜色
            spannableString.setSpan(new ForegroundColorSpan(chatText.color), 0, chatText.text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // 设置字体大小
            spannableString.setSpan(new RelativeSizeSpan(chatText.size / 16f), 0, chatText.text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // 将 SpannableString 添加到 SpannableStringBuilder
            spannableStringBuilder.append(spannableString);

            // 添加分隔符（如空格或换行符）
            if(i!=item.text.size()-1)
                spannableStringBuilder.append("\n"); // 或者 \n 添加换行符
        }

        // 将 SpannableStringBuilder 设置到 TextView
        holder.textview.setText(spannableStringBuilder);
        holder.imageview.setImageResource(item.touxiang_id);
        if(item.touxiang_id==R.drawable.avatar_person_05_6){
            holder.textview.setBackgroundColor(Color.parseColor("#ffffffff"));
        }
        else {
            holder.textview.setBackgroundColor(Color.parseColor("#ffffffff"));
        }
        // 设置文本
    }

    @Override
    public int getItemCount() {
        return datelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageview;
        private TextView textview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview = (ImageView)itemView.findViewById(R.id.chat_touxiang);
            textview = (TextView)itemView.findViewById(R.id.chat_text);
        }
    }
}
