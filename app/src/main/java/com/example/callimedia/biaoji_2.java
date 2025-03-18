package com.example.callimedia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class biaoji_2 extends Fragment {

    private View root_;
    private RecyclerView re;

    private ImageButton biaoyang,daigaijin;
    ArrayList<biaoji_re_item> PeopleList=new ArrayList<>();//创建装People的List

   private HashMap<String, Integer> map;
    void lv_(){
        biaoji_re_Adapter stringAdapter = new biaoji_re_Adapter(PeopleList);
        //配置适配器
        re.setAdapter(stringAdapter);
        //配置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        re.setLayoutManager(layoutManager);
    }
    void init() {
        re = root_.findViewById(R.id.biaoji_2_re);
        biaoyang = root_.findViewById(R.id.biaoji_re_biaoyang);
        biaoyang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new biaojihuode1().execute();
                biaoyang.setImageResource(R.drawable.biaoyang2);
                daigaijin.setImageResource(R.drawable.daigaijin1);
            }
        });
        daigaijin = root_.findViewById(R.id.biaoji_re_daigaijin);
        daigaijin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new biaojihuode2().execute();
                biaoyang.setImageResource(R.drawable.biaoyang1);
                daigaijin.setImageResource(R.drawable.daigaijin2);
            }
        });
        biaoyang.performClick();
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_biaoji_2, null);
        init();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }




    private class biaojihuode1 extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/getmark")
                    .method("POST", body)
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Accept", "*/*")
                    .addHeader("Host", "172.20.10.2:63070")
                    .addHeader("Connection", "keep-alive")
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
                Log.d("biaojihuode1", "you");
                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject obj = result.getJSONObject(i);

                        // 获取各字段
                        String markName = obj.getString("markName");
                        JSONArray nameArray = obj.getJSONArray("name");
                        map = new HashMap<>();

                        for (int j = 0; j < nameArray.length(); j++) {
                            String name = nameArray.getString(j);
                            if(map.containsKey(name)) {
                                int res=map.get(name);
                                map.put(name, res + 1);
                            }
                            else
                                map.put(name, 1);
                        }
                        String status = obj.getString("status");
                        if(status.equals("1")) {
                            Bitmap res=null;
                            if("举手回答".equals(markName))res=BitmapFactory.decodeResource(getResources(), R.drawable.jushou);
                            else if("认真听课".equals(markName))res=BitmapFactory.decodeResource(getResources(), R.drawable.zhuyili);
                            else if("积极思考".equals(markName))res=BitmapFactory.decodeResource(getResources(), R.drawable.jiji);
                            else if("笔力遒劲".equals(markName))res=BitmapFactory.decodeResource(getResources(), R.drawable.biliqiujing);
                            else res=BitmapFactory.decodeResource(getResources(), R.drawable.xingyun);
                            PeopleList.add(new biaoji_re_item(res, markName, map));
                        }


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                lv_();
            }
        }
    }


    private class biaojihuode2 extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/getmark")
                    .method("POST", body)
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Accept", "*/*")
                    .addHeader("Host", "172.20.10.2:63070")
                    .addHeader("Connection", "keep-alive")
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

                        // 获取各字段
                        String markName = obj.getString("markName");
                        JSONArray nameArray = obj.getJSONArray("name");
                        map = new HashMap<>();

                        for (int j = 0; j < nameArray.length(); j++) {
                            String name = nameArray.getString(j);
                            if(map.containsKey(name)) {
                                int res=map.get(name);
                                map.put(name, res + 1);
                            }
                            else
                                map.put(name, 1);
                        }
                        String status = obj.getString("status");
                        if(status.equals("0")) {


                            Bitmap res=null;
                            if("态度懈怠".equals(markName))res=BitmapFactory.decodeResource(getResources(), R.drawable.taiduxiedai);
                            else if("消极合作".equals(markName))res=BitmapFactory.decodeResource(getResources(), R.drawable.xiaojihezuo);
                            else if("无视纪律".equals(markName))res=BitmapFactory.decodeResource(getResources(), R.drawable.next_07_1_1);
                            else if("书写潦草".equals(markName))res=BitmapFactory.decodeResource(getResources(), R.drawable.shuxieliaocao);
                            else res=BitmapFactory.decodeResource(getResources(), R.drawable.zixingbuwen);

                            PeopleList.add(new biaoji_re_item(res, markName, map));

                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                lv_();
            }
        }
    }
}