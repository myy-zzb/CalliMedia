package com.example.callimedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class study_show extends AppCompatActivity implements study_1_Adapter.OnItemClickListener {


    private ImageButton return_,study_1,study_2,study_3;
    private ImageButton study_add,study_choose,study1_bt,study_last,study_next,study2_bt;

    private RecyclerView study_re1,study_re2;
    private TextView study1_tv,study2_tv;
    private List<study_1_item> PeopleList;
    private int anniuid,itemid;
    private List<study_2_item> PeopleList2;
    void change(View view){
        // 获取视图
        // 获取视图的布局参数
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        // 设置视图的位置
        params.leftMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                696,
                getResources().getDisplayMetrics()
        );;
        // 设置视图相对于父布局的位置
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        // 应用新的布局参数
        view.setLayoutParams(params);
    }
    void jia(View view){
        // 获取视图
        // 获取视图的布局参数
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        // 设置视图的位置
        params.leftMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                703,
                getResources().getDisplayMetrics()
        );;
        // 设置视图相对于父布局的位置
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        // 应用新的布局参数
        view.setLayoutParams(params);
    }
    void quan(){

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

    void lv_1(){



        study_1_Adapter stringAdapter = new study_1_Adapter(PeopleList,this);
        stringAdapter.setOnItemClickListener(this);
        //配置适配器
        study_re1.setAdapter(stringAdapter);
//        //配置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        study_re1.setLayoutManager(layoutManager);
    }


    void lv_2(){


        study_2_Adapter stringAdapter = new study_2_Adapter(PeopleList2,this);
        //配置适配器
        study_re2.setAdapter(stringAdapter);
//        //配置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        study_re2.setLayoutManager(layoutManager);
    }
    void chuu(){
        study_add.setVisibility(View.VISIBLE);
        study_choose.setVisibility(View.VISIBLE);
        study_re1.setVisibility(View.VISIBLE);
        study1_bt.setVisibility(View.VISIBLE);
        study1_tv.setVisibility(View.VISIBLE);
        study_re2.setVisibility(View.GONE);
        study2_bt.setVisibility(View.GONE);
        study2_tv.setVisibility(View.GONE);
    }

    void chuuu(){
        study_add.setVisibility(View.GONE);
        study_choose.setVisibility(View.GONE);
        study_re1.setVisibility(View.GONE);
        study1_bt.setVisibility(View.GONE);
        study1_tv.setVisibility(View.GONE);
        study_re2.setVisibility(View.VISIBLE);
        study2_bt.setVisibility(View.VISIBLE);
        study2_tv.setVisibility(View.VISIBLE);
    }
    void init() {
        PeopleList=new ArrayList<>();
        PeopleList2=new ArrayList<>();
        anniuid=0;
        itemid=0;
        quan();
        return_ = (ImageButton) findViewById(R.id.study_show_return);
        return_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(study_show.this, Home_page.class);
//                startActivity(intent);

                finish();
            }
        });
        study_1 = (ImageButton) findViewById(R.id.study_1);
        study_2 = (ImageButton) findViewById(R.id.study_2);
        study_3 = (ImageButton) findViewById(R.id.study_3);
        study_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change(study_1);
                change(study_2);
                change(study_3);
                jia(study_1);
                chuu();
                new NetworkTask().execute();
                anniuid=1;
                itemid=0;
                dianji1(0);
            }
        });

        study_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change(study_1);
                change(study_2);
                change(study_3);
                jia(study_2);
                chuu();
                new NetworkTask().execute();
                anniuid=2;
                itemid=0;

            }
        });

        study_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change(study_1);
                change(study_2);
                change(study_3);
                jia(study_3);
                chuuu();
                new NetworkTask2().execute();
                anniuid=3;
                itemid=0;

            }
        });

        study_add=findViewById(R.id.study_add);
        study_choose=findViewById(R.id.study_choose);
        study_re1=findViewById(R.id.study_re1);
        study_re2=findViewById(R.id.study_re2);
        study1_bt=findViewById(R.id.study_1_bt);
        study1_tv=findViewById(R.id.study_1_tv);
        study_last=findViewById(R.id.study_last);
        study_next=findViewById(R.id.study_next);
        study2_bt=findViewById(R.id.study_2_bt);
        study2_tv=findViewById(R.id.study_2_tv);


        study_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(anniuid==3){
                    jiafen_Dialog dialog=new jiafen_Dialog(study_show.this,R.style.mydialog,PeopleList2.get(itemid).id);
                    dialog.show();
//                    new jiafen().execute(PeopleList2.get(itemid).id,"1");
//                    Toast.makeText(v.getContext(), PeopleList2.get(itemid).name+"加分", Toast.LENGTH_SHORT).show();
                }
                else{
                    jiafen_Dialog dialog=new jiafen_Dialog(study_show.this,R.style.mydialog,String.valueOf(PeopleList.get(itemid).id));
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(128, 0, 0, 0)));
                    dialog.show();

//                    new jiafen().execute(String.valueOf(PeopleList.get(itemid).id),"1");

//                    Toast.makeText(v.getContext(), PeopleList.get(itemid).name+"加分", Toast.LENGTH_SHORT).show();
                }

            }
        });


        study_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(anniuid==3){
                    if(itemid==PeopleList2.size()-1||PeopleList2.size()==0){
                        Toast.makeText(v.getContext(), "已经是最后一个咯！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    itemid++;
                    dianji2(itemid);                }
                else{
                    if(itemid==PeopleList.size()-1||PeopleList.size()==0){
                        Toast.makeText(v.getContext(), "已经是最后一个咯！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    itemid++;
                    dianji1(itemid);
                }
            }
        });

        study_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(anniuid==3){
                    if(itemid==0){
                        Toast.makeText(v.getContext(), "已经是第一个咯！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    itemid--;
                    dianji2(itemid);                }
                else{
                    if(itemid==0){
                        Toast.makeText(v.getContext(), "已经是第一个咯！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    itemid--;
                    dianji1(itemid);
                }
            }
        });

        study_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(anniuid==3){
                    new youxiu(Integer.parseInt(PeopleList2.get(itemid).id)).execute();
                    Toast.makeText(v.getContext(), PeopleList2.get(itemid).name+"的作品被选为优秀作品", Toast.LENGTH_SHORT).show();
                }
                else{
                    new youxiu(PeopleList.get(itemid).id).execute();
                    Log.d("xixi", "onClick: "+PeopleList.get(itemid).id);
                    Toast.makeText(v.getContext(), PeopleList.get(itemid).name+"的作品被选为优秀作品", Toast.LENGTH_SHORT).show();
                }
            }
        });

        study_1.performClick();
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_show);

        init();
    }

    @Override
    public void onItemClick(int position) {
        itemid=position;
//        Log.d("xixi", "onItemClick: "+itemid);
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
                study_1_item item;
                PeopleList=new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject obj = result.getJSONObject(i);
                        item = new study_1_item(obj.getString("studentName"), obj.getInt("studentId"),obj.getString("data"), obj.getString("workContent"));
                        Log.d("xixi", "doInBackground: "+obj.getInt("studentId"));
                        PeopleList.add(item);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                lv_1();
                dianji1(0);
            }
        }
    }



    private class NetworkTask2 extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63020/learning/getgood")
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
                study_2_item item;
                PeopleList2=new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject obj = result.getJSONObject(i);
                        item = new study_2_item(obj.getString("studentName"), obj.getString("studentId"),obj.getString("workContent"));
                        PeopleList2.add(item);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                lv_2();
                dianji2(0);
            }
        }
    }


    private class youxiu extends AsyncTask<String, Void, Void> {

        private int id;

        // 构造函数，用于传入 id
        public youxiu(int id) {
            this.id = id;
        }

        @Override
        protected Void doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();

            // 准备 POST 请求的媒体类型和请求体
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("id", String.valueOf(id)) // 使用传入的 id
                    .build();

            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63020/learning/setgood")
                    .post(body) // 使用 POST 方法和请求体
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    // 处理请求失败的情况
                    throw new IOException("Unexpected code " + response);
                }
                // 可选：你可以在这里记录或处理响应，如果需要的话
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // 在这里处理请求完成后的操作，例如更新 UI 或显示消息
//             Toast.makeText(context, "请求完成", Toast.LENGTH_SHORT).show();
        }
    }

    private class jiafen extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            // 确保传入的参数足够
            if (params.length < 2) {
                throw new IllegalArgumentException("必须提供 id 和 score 参数");
            }

            String score = params[0]; // 第一个参数是 id
            String id = params[1]; // 第二个参数是 score

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



    void dianji1(final int x) {
        study_re1.post(new Runnable() {
            @Override
            public void run() {
                // 查找当前的 ViewHolder
                RecyclerView.ViewHolder viewHolder = study_re1.findViewHolderForAdapterPosition(x);

                if (viewHolder == null) {
                    // 如果未找到，滚动到该位置
                    study_re1.scrollToPosition(x);

                    // 再次 post 以确保 RecyclerView 有时间加载并创建视图
                    study_re1.post(new Runnable() {
                        @Override
                        public void run() {
                            RecyclerView.ViewHolder viewHolder = study_re1.findViewHolderForAdapterPosition(x);
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
                    new Handler(Looper.getMainLooper()).post(() -> itemView.performClick());
                }
            }
        });
    }




    void dianji2(final int x) {
        study_re2.post(new Runnable() {
            @Override
            public void run() {
                // 尝试获取目标项的 ViewHolder
                RecyclerView.ViewHolder viewHolder = study_re2.findViewHolderForAdapterPosition(x);

                if (viewHolder == null) {
                    // 如果 ViewHolder 为空，说明目标项不在屏幕上，需要滚动到目标位置
                    study_re2.scrollToPosition(x);

                    // 再次 post 以确保 RecyclerView 有时间加载视图
                    study_re2.post(new Runnable() {
                        @Override
                        public void run() {
                            // 再次尝试获取目标项的 ViewHolder
                            RecyclerView.ViewHolder viewHolder = study_re2.findViewHolderForAdapterPosition(x);
                            if (viewHolder != null) {
                                // 如果 ViewHolder 已经存在，执行点击
                                View itemView = viewHolder.itemView;
                                new Handler(Looper.getMainLooper()).post(() -> itemView.performClick());
                            }
                        }
                    });
                } else {
                    // 如果 ViewHolder 已经存在，直接执行点击
                    View itemView = viewHolder.itemView;
                    new Handler(Looper.getMainLooper()).post(() -> itemView.performClick());

                }
            }
        });
    }


}