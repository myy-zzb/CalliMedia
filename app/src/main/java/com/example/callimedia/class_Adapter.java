package com.example.callimedia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class class_Adapter extends RecyclerView.Adapter<class_Adapter.ViewHolder>{
    private List<class_item> datelist;

    private GridView gridView;

    private ArrayList<biaoji_grid_item> stuList;
    private int[] id;

    private Context context;



    public class_Adapter(List<class_item>x,GridView gridView,Context context){
        datelist=x;
        this.gridView=gridView;
        this.context=context;
        id = new int[]{R.drawable.hlw_1, R.drawable.hlw_2, R.drawable.hlw_3, R.drawable.hlw_4, R.drawable.hlw_5, R.drawable.hlw_6};
    }
    @NonNull
    @Override
    public class_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clss_list_item, parent, false);
        return new class_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        class_item item = datelist.get(position);

        holder.name.setText(item.name);
        // 设置文本

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("stuList","dianji"+item.id);
                new xuesheng().execute(String.valueOf(Integer.parseInt(item.id)+1));//获取每个班级的学生
//                stuList=new ArrayList<>();
//                for(int i=0;i<10;i++){
//                    stuList.add(new biaoji_grid_item(BitmapFactory.decodeResource(context.getResources(), id[i%6]), "姓名"+i,String.valueOf(i)));
//                }//测试
//                if(position==0)stuList.remove(0);
//                biaoji_xiao_Adapter x = new biaoji_xiao_Adapter(context,stuList);
//                gridView.setAdapter(x);

            }
        });

    }

    @Override
    public int getItemCount() {
        return datelist.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.class_name);

        }
    }


    private class xuesheng extends AsyncTask<String, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(String... params) {
            String classid = params[0];  // 从参数中获取传入的 classid

            OkHttpClient client = new OkHttpClient().newBuilder().build();

            // 构造 POST 请求的媒体类型和请求体
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = new FormBody.Builder()
                    .add("classed", classid)  // 使用传入的 classid
                    .build();

            // 构建 POST 请求
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/getclassed?classed="+classid)
                    .get()
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Accept", "*/*")
                    .addHeader("Host", "172.20.10.2:63070")
                    .addHeader("Connection", "keep-alive")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBodyString = response.body().string();  // 只调用一次
//                    Log.d("stuList", "response:" + responseBodyString);  // 打印日志
                    JSONObject jsonObject = new JSONObject(responseBodyString);
                    return jsonObject.getJSONArray("data");
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            // 在这里处理结果
            if (result != null) {
                // 处理JSONArray
                biaoji_grid_item item;
//                Log.d("stuList","you"+result.length());
                stuList = new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    try {
                        item = new biaoji_grid_item(BitmapFactory.decodeResource(context.getResources(), id[i%6]), result.getString(i),String.valueOf(i+1));
//                        Log.d("stuList","haha"+item.toString());
                        stuList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace(); // 用适当的日志记录代替抛出异常
                    }
                }


                biaoji_xiao_Adapter x = new biaoji_xiao_Adapter(context,stuList);
                gridView.setAdapter(x);


            }
        }
    }

}
