package com.example.callimedia;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class study_2_Adapter extends RecyclerView.Adapter<study_2_Adapter.ViewHolder> {



    private List<study_2_item> datelist;
    private Activity mactivity;


    public study_2_Adapter(List<study_2_item>x,Activity mactivity){
        datelist=x;
        this.mactivity=mactivity;
    }

    @NonNull
    @Override
    public study_2_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.study_2_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull study_2_Adapter.ViewHolder holder, int position) {
        study_2_item item=datelist.get(position);
        holder.tv.setText(item.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("xixi", "onBindViewHolder: " + item.id);

               ((TextView)(mactivity.findViewById(R.id.study_2_tv))).setText(item.name);
                loadImageAsync(item.url);

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
            tv=itemView.findViewById(R.id.study_2_item_tv);
        }
    }


    public Bitmap getImageFromUrl(String urlString) {
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
