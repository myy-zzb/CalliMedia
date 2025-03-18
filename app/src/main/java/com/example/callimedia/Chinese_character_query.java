package com.example.callimedia;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Chinese_character_query extends Fragment {
    private View root_;
    private GridView gridView;
    private TextView zidian,beitie;
    private ImageView zidian_tiao,beitie_tiao;

    private EditText hanzi;

    ArrayList<hanzi_item> datalist,datalist2;
    private void init(){
        datalist = new ArrayList<>();
        datalist2 = new ArrayList<>();
        hanzi = (EditText) root_.findViewById(R.id.hanzichaxun);
        gridView = (GridView) root_.findViewById(R.id.hanzi_grid);
        zidian = (TextView) root_.findViewById(R.id.zidian);
        beitie = (TextView) root_.findViewById(R.id.beitie);
        zidian_tiao = (ImageView) root_.findViewById(R.id.zidian_tiao);
        beitie_tiao = (ImageView) root_.findViewById(R.id.beitie_tiao);
        zidian_tiao.setVisibility(View.GONE);



        beitie_tiao.setVisibility(View.GONE);


        hanzi.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // 检查 actionId 是否为回车键的动作
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_SEND ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {


                    if(hanzi.getText().toString().equals("你")) {
                        Log.d("xixi",hanzi.getText().toString());
                        new NetworkTask().execute("1");
//                        new NetworkTask2().execute("1");
                    }
                    else {
                        new NetworkTask().execute("2");
//                        new NetworkTask2().execute("2");
                    }



                    return true;  // 表示事件已处理
                }
                return false; // 表示事件未处理
            }
        });

        zidian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zidian_tiao.setVisibility(View.VISIBLE);
                beitie_tiao.setVisibility(View.GONE);
                if(datalist.size()!=0) {
                    hanzi_Adapter x = new hanzi_Adapter(getContext(), datalist);
                    gridView.setAdapter(x);
                }
            }
        });
        beitie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zidian_tiao.setVisibility(View.GONE);
                beitie_tiao.setVisibility(View.VISIBLE);
                if(datalist2.size()!=0) {
                    hanzi_Adapter x = new hanzi_Adapter(getContext(), datalist2);
                    gridView.setAdapter(x);
                }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hanzi_item item =(hanzi_item) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), zhanshi.class);
                intent.putExtra("hanzi",item.url);
                Log.d("xixi",item.url);
                startActivity(intent);
            }
        });




    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_chinese_character_query, null);
        init();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }



    private class NetworkTask extends AsyncTask<String, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(String... params) {
            String ziValue = params[0];  // 获取传入的 "zi" 值

            OkHttpClient client = new OkHttpClient().newBuilder().build();

            // 构造 POST 请求的媒体类型和请求体
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("zi", ziValue)  // 使用传入的 zi 值
                    .build();

            // 构建请求
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63020/learning/zitie")
                    .post(body) // 使用 POST 方法和请求体
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Accept", "*/*")
                    .addHeader("Host", "172.20.10.2:63020")
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
                hanzi_item item;
                datalist = new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    try {
                        item = new hanzi_item(result.getString(i));
                        Log.d("xixi",item.url);
                        datalist.add(item);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                zidian.performClick();
            }
        }
    }
    private class NetworkTask2 extends AsyncTask<String, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(String... params) {
            String ziValue = params[0];  // 获取传入的 "zi" 值

            OkHttpClient client = new OkHttpClient().newBuilder().build();

            // 构造 POST 请求的媒体类型和请求体
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("zi", ziValue)  // 使用传入的 zi 值
                    .build();

            // 构建请求
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63020/learning/beitie")
                    .post(body) // 使用 POST 方法和请求体
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Accept", "*/*")
                    .addHeader("Host", "172.20.10.2:63020")
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
                hanzi_item item;
                datalist2 = new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    try {
                        item = new hanzi_item(result.getString(i));
                        Log.d("xixi",item.url);
                        datalist2.add(item);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                beitie.performClick();
            }
        }
    }

}