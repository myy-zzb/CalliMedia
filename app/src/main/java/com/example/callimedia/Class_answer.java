package com.example.callimedia;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Class_answer extends Fragment {

    private View root_;

    private ImageView bt_suiji;

    private ImageView suiji_touxiang;
    private TextView suiji_name;
    private int[] id;
    private ArrayList<biaoji_grid_item> PeopleList;

    private GridView gridView;
    int touxiang,mz;
    String stuid;

    private void init(){
        stuid="-1";
        gridView=(GridView) root_.findViewById(R.id.jiafen_gridview);
        PeopleList=new ArrayList<>();
//        new NetworkTask().execute();
        touxiang=0;
        mz=0;
        id = new int[]{R.drawable.hlw_1, R.drawable.hlw_2, R.drawable.hlw_3, R.drawable.hlw_4, R.drawable.hlw_5, R.drawable.hlw_6};
        View includedLayout = root_.findViewById(R.id.suijijiafen);
        suiji_touxiang=(ImageView) includedLayout.findViewById(R.id.biaoji_1_tx);
        suiji_name=(TextView) includedLayout.findViewById(R.id.biaoji_1_name);
        bt_suiji=(ImageView) root_.findViewById(R.id.bt_suiji);
        bt_suiji.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                new suijidianming().execute();

//                Log.d("haha", stuid);


                Handler handler = new Handler();
                // 定义持续时间
                final long duration = 2000; // 2秒
                // 定义间隔时间
                final long interval = 200; // 每200毫秒变化一次
                // 记录开始时间
                final long startTime = System.currentTimeMillis();

                // 创建一个Runnable
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        // 计算已过时间
                        long elapsedTime = System.currentTimeMillis() - startTime;

                        // 如果未达到持续时间，继续执行
                        if (elapsedTime < duration) {
                            // 这里你可以根据需要修改ImageView的背景和TextView的内容
                            suiji_touxiang.setImageResource(id[touxiang%6]);
                            touxiang++;
                            suiji_name.setText(PeopleList.get(mz).name);
                            mz++;
                            mz%=PeopleList.size();

                            // 每隔200ms再次执行Runnable
                            handler.postDelayed(this, interval);
                        } else {

                            suiji_touxiang.setImageResource(id[(Integer.parseInt(stuid)-1)%6]);
                            suiji_name.setText(PeopleList.get(Integer.parseInt(stuid)-1).name);
                        }
                    }
                };

                // 启动Runnable
                handler.post(runnable);


            }
        });

        new NetworkTask().execute();
//        for(int i=0;i<10;i++){
//            PeopleList.add(new biaoji_grid_item(BitmapFactory.decodeResource(getResources(), id[i%6]), "姓名"+i,String.valueOf(i)));
//        }

        biaoji_xiao_Adapter x = new biaoji_xiao_Adapter(getContext(),PeopleList);
        gridView.setAdapter(x);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(stuid!="-1"){

                    View itemView = gridView.getChildAt(Integer.parseInt(stuid)-1);
                    itemView.findViewById(R.id.item_bg).setBackgroundColor(Color.parseColor("#F7F3ED"));
                }
                View xixi = gridView.getChildAt(position);
                xixi.findViewById(R.id.item_bg).setBackgroundColor(Color.parseColor("#FFC0CB"));
                biaoji_grid_item item =( biaoji_grid_item) parent.getItemAtPosition(position);
                stuid=item.id;


            }
        });

        root_.findViewById(R.id.jiafen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiafen_Dialog dialog = new jiafen_Dialog(getContext(),R.style.mydialog, stuid);
                dialog.show();
            }
        });

    }



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_class_answer, null);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }

    private class NetworkTask extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/getall")
                    .get()  // 直接指定 GET 方法，无需 RequestBody
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBodyString = response.body().string();
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
                PeopleList=new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject obj = result.getJSONObject(i);
                        item = new biaoji_grid_item(BitmapFactory.decodeResource(getResources(), id[i%6]), obj.getString("studentName"),String.valueOf(obj.getInt("studentId")));
                        PeopleList.add(item);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                biaoji_xiao_Adapter x = new biaoji_xiao_Adapter(getContext(),PeopleList);
                gridView.setAdapter(x);
            }

        }
    }

    private class suijidianming extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            // 创建 OkHttpClient 实例
            OkHttpClient client = new OkHttpClient().newBuilder().build();

            // 构建 GET 请求
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/student")
                    .get() // 确保使用 GET 方法
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBodyString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseBodyString);

                    // 提取 "data" 字段的值
                    String data = jsonObject.getString("data");
                    return data;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // 在这里处理结果
            if (result != null) {
                stuid=result;
                Log.d("hahaa", result);

            }
        }
    }



}