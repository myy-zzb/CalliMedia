package com.example.callimedia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class xunzhang_Adapter extends RecyclerView.Adapter<xunzhang_Adapter.ViewHolder>{


    List<xunzhang> datelist;

    xunzhang_Adapter(List<xunzhang> datelist){
        this.datelist = datelist;
    }

    @NonNull
    @Override
    public xunzhang_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xunzhang_item, parent, false);
        return new xunzhang_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull xunzhang_Adapter.ViewHolder holder, int position) {

        if(position==0)
        {
            holder.xunzhang.setImageResource(R.drawable.xunzhang_ketanghuida);
            holder.miaoshu.setText("    课堂回答勋章\n上课积极回答获得");
            holder.huodeqingkuang.setText("获得者：张三，王五");
        }
        else if(position==1)
        {
            holder.xunzhang.setImageResource(R.drawable.xunzhang_quweiyouxi);
            holder.miaoshu.setText("    趣味游戏勋章\n趣味游戏闯关获得");
            holder.huodeqingkuang.setText("获得者：李四，王五");
        }
        else if(position==2){
            holder.xunzhang.setImageResource(R.drawable.xunzhang_suitangceshi);
            holder.miaoshu.setText("    随堂测试勋章\n随堂测试通过获得");
            holder.huodeqingkuang.setText("获得者：张三，盛六");
        }
        else {
            holder.xunzhang.setImageResource(R.drawable.xunzhang_xuexizhanshi);
            holder.miaoshu.setText("    学习展示勋章\n学习作品展示获得");
            holder.huodeqingkuang.setText("获得者：盛六，苏七");
        }

    }

    @Override
    public int getItemCount() {
        return datelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView xunzhang;
        private TextView huodeqingkuang,miaoshu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            xunzhang = itemView.findViewById(R.id.xunzhang_iv);

            huodeqingkuang = itemView.findViewById(R.id.huodeqingkuang_iv);

            miaoshu = itemView.findViewById(R.id.xunzhang_miaoshu);

        }
    }
}
