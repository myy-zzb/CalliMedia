package com.example.callimedia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.chrisbanes.photoview.PhotoView;

import java.io.InputStream;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class study_1_Adapter extends RecyclerView.Adapter<study_1_Adapter.ViewHolder> {



    private List<study_1_item> datelist;
    private Activity mactivity;

    public study_1_Adapter(List<study_1_item>x,Activity xx){
        datelist=x;
        mactivity=xx;
    }

    @NonNull
    @Override
    public study_1_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.study_1_item, parent, false);
        return new ViewHolder(view);
    }
    public void addNewItem() {
        // 假设你添加的项是一个简单的自增数字
        datelist.get(datelist.size() - 1).time="无";
        datelist.get(datelist.size() - 1).name="无";
        datelist.get(datelist.size() - 1).id=0;
        datelist.add(new study_1_item("无",0,"无","0") );

        notifyItemInserted(datelist.size() - 1);  // 通知RecyclerView新项已插入
        notifyItemChanged(datelist.size() - 2);  // 通知RecyclerView新项已插入
    }
    @Override
    public void onBindViewHolder(@NonNull study_1_Adapter.ViewHolder holder, int position) {
        study_1_item item = datelist.get(position);
        holder.tv.setText(item.time);

//        if (position == datelist.size() - 1) {
//            holder.tv.setText("添加");
//            holder.itemView.setOnClickListener(v -> {
//                if (position == getItemCount() - 1) {
//                    addNewItem();
//                }
//            });
//        }

        holder.itemView.setOnClickListener(v -> {
            ((TextView) mactivity.findViewById(R.id.study_1_tv)).setText(item.name);
//            Log.d("xixi", "onBindViewHolder: " + item.id);
            loadImageAsync(item.url);

            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return datelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.study_1_item_tv);
        }
    }

    public Bitmap getImageFromUrl(String urlString) {


//        Log.d("xixi", "getImageFromUrl: " + urlString);
        Bitmap bitmap = null;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlString)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                InputStream inputStream = response.body().byteStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    private void loadImageAsync(String urlString) {new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                return getImageFromUrl(params[0]);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    ((PhotoView) mactivity.findViewById(R.id.study_photo)).setImageBitmap(bitmap);
                } else {
                    // 处理加载失败的情况，例如显示一个占位图
                }
            }
        }.execute(urlString);
    }

}
