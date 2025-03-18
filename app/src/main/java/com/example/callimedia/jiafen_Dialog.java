package com.example.callimedia;

import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class jiafen_Dialog extends Dialog {

    String id;


    void quan() {
        // 清除半透明状态栏标志
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // 设置系统 UI 可见性
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        // 设置为透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // 处理刘海屏
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(attributes);
        }

        // 使得布局能够延伸到状态栏和导航栏区域
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

    }

    void init() {
        quan();
        findViewById(R.id.jiafen_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        findViewById(R.id.jiayifen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new jiafen().execute(id, "1");
                dismiss();
            }
        });
        findViewById(R.id.jiaerfen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new jiafen().execute(id, "2");
                dismiss();
            }
        });
        findViewById(R.id.jiasanfen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new jiafen().execute(id, "3");
                dismiss();
            }
        });
    }
    public jiafen_Dialog(@NonNull Context context,int themeResId,String id) {
        super(context, themeResId);
        this.id = id;
        setContentView(R.layout.jiafen_dailog);


        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; // 设置宽度
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT; // 设置高度
        this.getWindow().setAttributes(layoutParams);
        init();
    }
    private class jiafen extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            // 确保传入的参数足够
            if (params.length < 2) {
                throw new IllegalArgumentException("必须提供 id 和 score 参数");
            }

            String id = params[0]; // 第一个参数是 id
            String score = params[1]; // 第二个参数是 score

            OkHttpClient client = new OkHttpClient();

            // 设置媒体类型和请求体
            RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("id", id)
                    .addFormDataPart("score", score)
                    .build();

            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/getscore")
                    .post(body) // 使用 POST 方法和请求体
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    // 处理请求失败的情况
                    throw new IOException("Unexpected code " + response);
                }
                // 可选：你可以在这里处理响应内容
                String responseBody = response.body().string();
                // 在这里处理响应
                System.out.println(responseBody);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // 在这里处理请求完成后的操作，例如更新 UI 或显示消息
            // 例如：Toast.makeText(context, "请求完成", Toast.LENGTH_SHORT).show();
        }
    }
}
