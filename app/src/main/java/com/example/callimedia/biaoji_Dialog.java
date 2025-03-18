package com.example.callimedia;

import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class biaoji_Dialog extends Dialog {

    private TextView mTextView;
    private ImageButton return_,biaoyang,daigaijin;
    private GridView gridView;
    String name,idd;
    private ArrayList<biaoji_grid_item> datalist ;


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

    private int flag=0;

    void lv(){
        flag=0;
        datalist = new ArrayList<biaoji_grid_item>();
//        举手回答，认真书写，积极思考，笔力遒劲，挥洒自如，行云流水，稳健端庄
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.jushou),"举手回答","1"));
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.zhuyili),"认真听课","1"));
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.jiji),"积极思考","1"));
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.biliqiujing),"笔力遒劲","1"));
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.xingyun),"行云流水","1"));
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bianji),"编辑","1"));
        lv_();
    }
    void lvv(){
        flag=1;
        datalist = new ArrayList<biaoji_grid_item>();
//        上课不认真，无视纪律，书写潦草，字形不稳，笔画凌乱
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.taiduxiedai),"态度懈怠","0"));
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.xiaojihezuo),"消极合作","0"));
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.next_07_1_1),"无视纪律","0"));
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.shuxieliaocao),"书写潦草","0"));
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.zixingbuwen),"字形不稳","0"));
        datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bianji),"编辑","0"));


        lv_();
    }
    void lv_(){
        biaoji_xiao_Adapter x = new biaoji_xiao_Adapter(getContext(),datalist);
        gridView.setAdapter(x);

    }
    void init() {
        quan();
        flag=0;
        mTextView = findViewById(R.id.biaoji__name);
        mTextView.setText(name);
        return_ = findViewById(R.id.biaoji_close);
        return_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        gridView = findViewById(R.id.tanchuang_grid);
        biaoyang = findViewById(R.id.tanchuang_by);
        daigaijin = findViewById(R.id.tanchuang_dgj);
        biaoyang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv();
                biaoyang.setImageResource(R.drawable.biaoyang2);
                daigaijin.setImageResource(R.drawable.daigaijin1);
            }
        });

        daigaijin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvv();
                biaoyang.setImageResource(R.drawable.biaoyang1);
                daigaijin.setImageResource(R.drawable.daigaijin2);
            }
        });
        biaoyang.performClick();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                biaoji_grid_item item = datalist.get(position);
                Log.d("biaojii", "onItemClick: "+item.name);
                new biaoji().execute(item.name,idd,item.id,name);//传输学生id，标记类型，标记
                dismiss();
            }
        });
    }
    public biaoji_Dialog(@NonNull Context context,int themeResId,String name,String id) {
        super(context, themeResId);
        this.name = name;
        this.idd = id;
        setContentView(R.layout.biaoji_dialog);


        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; // 设置宽度
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT; // 设置高度
        this.getWindow().setAttributes(layoutParams);
        init();
    }


    private class biaoji extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            String markName=params[0];
            String studentId=params[1];
            String status=params[2];
            String studentName=params[3];

            Log.d("biaojii", "doInBackground: "+markName+" "+studentId+" "+status+" "+studentName);
            RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("markName", markName)
                    .addFormDataPart("studentId", studentId)
                    .addFormDataPart("status", status)
                    .addFormDataPart("studentName", studentName)
                    .build();

            // 构造 GET 请求
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/mark")
                    .post(body)
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Accept", "*/*")
                    .addHeader("Host", "172.20.10.2:63070")
                    .addHeader("Connection", "keep-alive")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBodyString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseBodyString);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
