package com.example.callimedia;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class biaoji_re_Adapter extends RecyclerView.Adapter<biaoji_re_Adapter.ViewHolder> {

    ArrayList<biaoji_re_item> datalist;

    biaoji_re_Adapter(ArrayList<biaoji_re_item> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public biaoji_re_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.biaoji_re_item, parent, false);
        return new biaoji_re_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull biaoji_re_Adapter.ViewHolder holder, int position) {
        biaoji_re_item item = datalist.get(position);
        holder.name.setText(item.name);
        holder.tx.setImageBitmap(item.bitmap);
        SpannableStringBuilder res= new SpannableStringBuilder();
        for (Map.Entry<String, Integer> entry : item.x.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            String v=value.toString();


            SpannableString spannableText2 = new SpannableString(key);
            res.append(spannableText2);
            spannableText2=new SpannableString("("+v+")  ");
            spannableText2.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0276ff")),0,spannableText2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            res.append(spannableText2);
            holder.message.setText(res);
        }

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,message;
        private ImageView tx;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.biaoji_re_name);

            message = itemView.findViewById(R.id.biaoji_re_message);

            tx = itemView.findViewById(R.id.biaoji_re_tx);
        }
    }
}
