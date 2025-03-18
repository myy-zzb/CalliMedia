package com.example.callimedia;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class jifen_Dialog extends Dialog {

    private RecyclerView re_;

    private ImageButton bt_jifen_xueqi, bt_jifen_yue, bt_jifen_xunzhang;

    private ImageView jifenbiaoti;

    private int[] id;
    List<jifen_item> PeopleList ;//创建装People的List

    void init() {
        PeopleList = new ArrayList<>();//创建装People的List
        id = new int[]{R.drawable.hlw_1, R.drawable.hlw_2, R.drawable.hlw_3, R.drawable.hlw_4, R.drawable.hlw_5, R.drawable.hlw_6};
        bt_jifen_xueqi = findViewById(R.id.bt_jifen_xueqi);
        bt_jifen_xunzhang = findViewById(R.id.bt_xunzhang);
        bt_jifen_yue = findViewById(R.id.bt_jifen_yue);

        jifenbiaoti = findViewById(R.id.jifen_id);
        re_ = (RecyclerView) findViewById(R.id.jifen_re);

    }

    void lv() {

        jifen_Adapter stringAdapter = new jifen_Adapter(PeopleList);

        //配置适配器
        re_.setAdapter(stringAdapter);
//        //配置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        re_.setLayoutManager(layoutManager);
    }


    void lv_xun() {
        List<xunzhang> PeopleList = new ArrayList<>();//创建装People的List
        xunzhang res;
        res = new xunzhang(60, 3, 1);
        PeopleList.add(res);
        res = new xunzhang(99, 1, 1);
        PeopleList.add(res);
        res = new xunzhang(99, 1, 1);
        PeopleList.add(res);
        res = new xunzhang(9, 1, 1);
        PeopleList.add(res);


        xunzhang_Adapter stringAdapter = new xunzhang_Adapter(PeopleList);

        //配置适配器
        re_.setAdapter(stringAdapter);
//        //配置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        re_.setLayoutManager(layoutManager);
    }

    void chu() {
        bt_jifen_xueqi.setImageResource(R.drawable.choice_xueqijifen_07_2_3_bef);
        bt_jifen_yue.setImageResource(R.drawable.choice_yuejifen_07_2_3_bef);
        bt_jifen_xunzhang.setImageResource(R.drawable.choice_chengjiuxunz_07_2_3_bef);
    }

    public jifen_Dialog(@NonNull Context context, int themeResId) {

        super(context, themeResId);
        setContentView(R.layout.ranking_of_points);


        init();

        findViewById(R.id.bt_jifen_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加确定按键效果
                dismiss();
            }
        });

        bt_jifen_xueqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chu();
                bt_jifen_xueqi.setImageResource(R.drawable.choice_xueqijifen_07_2_3_aft);
                jifenbiaoti.setImageResource(R.drawable.title_xueq_07_2);
                new NetworkTask().execute();
            }
        });

        bt_jifen_yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chu();
                bt_jifen_yue.setImageResource(R.drawable.choice_yuejifen_07_2_3_aft);
                jifenbiaoti.setImageResource(R.drawable.title_yuej_07_2);
                new NetworkTask().execute();
            }
        });

        bt_jifen_xunzhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chu();
                bt_jifen_xunzhang.setImageResource(R.drawable.choice_chengjiuxunz_07_2_3_aft);
                jifenbiaoti.setImageResource(R.drawable.title_chengjiu_07_2);
                lv_xun();
            }
        });

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; // 设置宽度
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; // 设置高度
        this.getWindow().setAttributes(layoutParams);


        bt_jifen_yue.performClick();
    }


    private class NetworkTask extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/rank")
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Accept", "*/*")
                    .addHeader("Host", "172.20.10.2:63070")
                    .addHeader("Connection", "keep-alive")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response != null && response.body() != null) {
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
                PeopleList.clear();
                jifen_item item;
                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject obj = result.getJSONObject(i);
                        item = new jifen_item(id[obj.getInt("studentId")%6], obj.getInt("score"), obj.getString("medal"), obj.getString("studentName"));
                        PeopleList.add(item);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                lv();
            }
        }
    }
}
