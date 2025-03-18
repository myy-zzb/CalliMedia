package com.example.callimedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Class_list extends AppCompatActivity {

    private RecyclerView re_;
    private ImageButton create,return_,qiehuan;

    private ImageView title;

    private List<class_item> PeopleList;//创建装People的List

    private ArrayList<biaoji_grid_item> stuList;

    private GridView gridView;

    private int[] id;
    private int flag;

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




    void lv() {


        class_Adapter stringAdapter = new class_Adapter(PeopleList,gridView,this);

        //配置适配器
        re_.setAdapter(stringAdapter);
//        //配置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        re_.setLayoutManager(layoutManager);

    }

    void init() {
        gridView=findViewById(R.id.banji_grid);
        id = new int[]{R.drawable.hlw_1, R.drawable.hlw_2, R.drawable.hlw_3, R.drawable.hlw_4, R.drawable.hlw_5, R.drawable.hlw_6};


        flag=0;
        title=findViewById(R.id.list_title);
        qiehuan=findViewById(R.id.list_qiehuan);
        PeopleList=new ArrayList<>();
        quan();
        re_=findViewById(R.id.class_list_re);


        new banji().execute();   //获取班级名单



        create=findViewById(R.id.create_class);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class_item res;
                res=new class_item("新建班级","0");
                PeopleList.add(res);
                re_.getAdapter().notifyItemInserted(PeopleList.size()-1);
            }
        });

        return_=findViewById(R.id.class_list_return);
        return_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        qiehuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0){
                    title.setImageResource(R.drawable.seating_06_1_2);
                    flag++;
                }
                else {
                    title.setImageResource(R.drawable.class_06_1_1);
                    flag--;
                }
            }
        });

        re_.post(new Runnable() {
            @Override
            public void run() {
                // 获取第一项的 ViewHolder
                RecyclerView.ViewHolder viewHolder = re_.findViewHolderForAdapterPosition(0);

                if (viewHolder != null) {
                    // 模拟点击
                    viewHolder.itemView.performClick();
                }
            }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        init();
    }



    private class banji extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            // 构造 GET 请求
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/getallclassed")
                    .get() // 使用 GET 方法
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
                class_item item;
                PeopleList = new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    try {
                        Log.d("banji",result.getString(i));
                        item = new class_item(result.getString(i)+"班",String.valueOf(i));
                        PeopleList.add(item);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                lv();
                dianji1(0);
            }
        }
    }






    void dianji1(final int x) {
        re_.post(new Runnable() {
            @Override
            public void run() {
                // 查找当前的 ViewHolder
                RecyclerView.ViewHolder viewHolder = re_.findViewHolderForAdapterPosition(x);

                if (viewHolder == null) {
                    // 如果未找到，滚动到该位置
                    re_.scrollToPosition(x);

                    // 再次 post 以确保 RecyclerView 有时间加载并创建视图
                    re_.post(new Runnable() {
                        @Override
                        public void run() {
                            RecyclerView.ViewHolder viewHolder = re_.findViewHolderForAdapterPosition(x);
                            if (viewHolder != null) {
                                // 获取 itemView 并触发点击
                                View itemView = viewHolder.itemView;
                                new Handler(Looper.getMainLooper()).post(() -> itemView.performClick());
                            }
                        }
                    });
                } else {
                    // 如果找到了，直接触发点击
                    View itemView = viewHolder.itemView;
                    itemView.performClick();
                }
            }
        });
    }
}