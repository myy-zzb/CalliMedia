package com.example.callimedia;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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


public class bihua extends Fragment {

    private ArrayList<String>timu,timumiaoshu;
    private ArrayList<String>xuanxiang;

    private ArrayList<Integer>daan;

    private ArrayList<Integer>vis;//是否已经答过题


    int timuid;

    private ImageView bihua_timu;

    private TextView xx1,xx2,xx3,xx4,tm;
    private LinearLayout xuanxiang1,xuanxiang2,xuanxiang3,xuanxiang4;

    void chu(){


        if(timuid==0)
            bihua_timu.setImageResource(R.drawable.w2221);
        else bihua_timu.setImageResource(R.drawable.w2222);




        xx1.setText(xuanxiang.get(timuid*4));

        xx2.setText(xuanxiang.get(timuid*4+1));

        xx3.setText(xuanxiang.get(timuid*4+2));

        xx4.setText(xuanxiang.get(timuid*4+3));

        tm.setText(timumiaoshu.get(timuid));

//        bihua_timu.setImageResource(R.drawable.timu);

        xuanxiang1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.common_choice));

        xuanxiang2.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.common_choice));

        xuanxiang3.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.common_choice));

        xuanxiang4.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.common_choice));
//        测试数据

    }


    void xianshizhengquedaan(){
        if(daan.get(timuid)==1)
            xuanxiang1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.right_choice));
        else if(daan.get(timuid)==2)
            xuanxiang2.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.right_choice));
        else if(daan.get(timuid)==3)
            xuanxiang3.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.right_choice));
        else if(daan.get(timuid)==4)
            xuanxiang4.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.right_choice));
    }


    void biantanchuang(int dijiti,int dui1cuo0){
        View view=root_.findViewById(R.id.bihua_tanchuang);
        if(dijiti==1) {
            if (dui1cuo0 == 1)
                ((ImageButton) view.findViewById(R.id.timu1)).setImageResource(R.drawable.right_num_05_4_1);
            else
                ((ImageButton) view.findViewById(R.id.timu1)).setImageResource(R.drawable.wrong_num_05_4_1);
        }
        else if(dijiti==2) {
            if (dui1cuo0 == 1)
                ((ImageButton) view.findViewById(R.id.timu2)).setImageResource(R.drawable.right_num_05_4_1);
            else
                ((ImageButton) view.findViewById(R.id.timu2)).setImageResource(R.drawable.wrong_num_05_4_1);
        }
        else if(dijiti==3) {
            if (dui1cuo0 == 1)
                ((ImageButton) view.findViewById(R.id.timu3)).setImageResource(R.drawable.right_num_05_4_1);
            else
                ((ImageButton) view.findViewById(R.id.timu3)).setImageResource(R.drawable.wrong_num_05_4_1);
        }

        else if(dijiti==4) {

            if (dui1cuo0 == 1)
                ((ImageButton) view.findViewById(R.id.timu4)).setImageResource(R.drawable.right_num_05_4_1);
            else
                ((ImageButton) view.findViewById(R.id.timu4)).setImageResource(R.drawable.wrong_num_05_4_1);
        }
        else if(dijiti==5) {
            if (dui1cuo0 == 1)
                ((ImageButton) view.findViewById(R.id.timu5)).setImageResource(R.drawable.right_num_05_4_1);
            else
                ((ImageButton) view.findViewById(R.id.timu5)).setImageResource(R.drawable.wrong_num_05_4_1);
        }
        else if(dijiti==6) {
            if (dui1cuo0 == 1)
                ((ImageButton) view.findViewById(R.id.timu6)).setImageResource(R.drawable.right_num_05_4_1);
            else
                ((ImageButton) view.findViewById(R.id.timu6)).setImageResource(R.drawable.wrong_num_05_4_1);
        }

        else if(dijiti==7) {

            if (dui1cuo0 == 1)
                ((ImageButton) view.findViewById(R.id.timu7)).setImageResource(R.drawable.right_num_05_4_1);
            else
                ((ImageButton) view.findViewById(R.id.timu7)).setImageResource(R.drawable.wrong_num_05_4_1);
        }
        else if(dijiti==8) {
            if (dui1cuo0 == 1)
                ((ImageButton) view.findViewById(R.id.timu8)).setImageResource(R.drawable.right_num_05_4_1);
            else
                ((ImageButton) view.findViewById(R.id.timu8)).setImageResource(R.drawable.wrong_num_05_4_1);
        }
        else if(dijiti==9) {
            if (dui1cuo0 == 1)
                ((ImageButton) view.findViewById(R.id.timu9)).setImageResource(R.drawable.right_num_05_4_1);
            else
                ((ImageButton) view.findViewById(R.id.timu9)).setImageResource(R.drawable.wrong_num_05_4_1);
        }
        else if(dijiti==10) {
            if (dui1cuo0 == 1)
                ((ImageButton) view.findViewById(R.id.timu10)).setImageResource(R.drawable.right_num_05_4_1);
            else
                ((ImageButton) view.findViewById(R.id.timu10)).setImageResource(R.drawable.wrong_num_05_4_1);
        }
    }


    void init(){
        bihua_timu = (ImageView)root_.findViewById(R.id.bihua_timu);
        timu = new ArrayList<String>();
        xuanxiang = new ArrayList<String>();
        daan = new ArrayList<Integer>();
        vis = new ArrayList<Integer>();


        bihua_timu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), zhanshi.class);
                if(timuid==0)
                    intent.putExtra("haha",String.valueOf(R.drawable.w2221));
                else intent.putExtra("haha",String.valueOf(R.drawable.w2222));
                startActivity(intent);
            }
        });


        xx1 = (TextView)root_.findViewById(R.id.xuanxiang1_text);
        xx2 = (TextView)root_.findViewById(R.id.xuanxiang2_text);
        xx3 = (TextView)root_.findViewById(R.id.xuanxiang3_text);
        xx4 = (TextView)root_.findViewById(R.id.xuanxiang4_text);
        tm=(TextView)root_.findViewById(R.id.bihua_timu_text);
        timuid=0;
        for(int i=0;i<10;i++)vis.add(0);
        daan.add(1);
        daan.add(3);
        daan.add(3);
        daan.add(4);
        daan.add(1);
        daan.add(2);
        daan.add(3);
        daan.add(4);
        daan.add(1);
        daan.add(2);
//        测试数据

        xuanxiang1 = (LinearLayout)root_.findViewById(R.id.bihua_xuanxinag1);
        xuanxiang2 = (LinearLayout)root_.findViewById(R.id.bihua_xuanxinag2);
        xuanxiang3 = (LinearLayout)root_.findViewById(R.id.bihua_xuanxinag3);
        xuanxiang4 = (LinearLayout)root_.findViewById(R.id.bihua_xuanxinag4);

        root_.findViewById(R.id.bihua_tanchuang).setVisibility(View.GONE);
        root_.findViewById(R.id.bihua_tanchuang_kai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root_.findViewById(R.id.bihua_tanchuang).setVisibility(View.VISIBLE);
                root_.findViewById(R.id.bihua_xiala).setVisibility(View.GONE);
            }
        });

        root_.findViewById(R.id.bihua_tanchuang).findViewById(R.id.shanghua).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root_.findViewById(R.id.bihua_tanchuang).setVisibility(View.GONE);
                root_.findViewById(R.id.bihua_xiala).setVisibility(View.VISIBLE);
            }
        });

        xuanxiang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daan.get(timuid)!=1) {
                    xuanxiang1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.wrong_choice));
                    biantanchuang(timuid+1,0);
                }
                else biantanchuang(timuid+1,1);
                xianshizhengquedaan();
                vis.set(timuid,1);
            }
        });

        xuanxiang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daan.get(timuid)!=2) {
                    biantanchuang(timuid+1,0);
                    xuanxiang2.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.wrong_choice));
                }
                else biantanchuang(timuid+1,1);
                xianshizhengquedaan();
                vis.set(timuid,2);
            }
        });

        xuanxiang3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(daan.get(timuid)!=3) {
                    biantanchuang(timuid+1,0);
                    xuanxiang3.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.wrong_choice));
                }
                else biantanchuang(timuid+1,1);
                xianshizhengquedaan();
                vis.set(timuid,3);
            }
        });

        xuanxiang4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (daan.get(timuid) != 4) {
                    biantanchuang(timuid+1,0);
                    xuanxiang4.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.wrong_choice));
                }
                else biantanchuang(timuid+1,1);
                xianshizhengquedaan();
                vis.set(timuid,4);
            }
        });

        root_.findViewById(R.id.bihua_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timuid==9){
                    Toast.makeText(v.getContext(), "已经是最后一题咯！", Toast.LENGTH_SHORT).show();
                    return;
                }
                timuid++;
                chu();
                if(vis.get(timuid)!=0){
                    if(vis.get(timuid)==1)
                        xuanxiang1.performClick();
                    else if(vis.get(timuid)==2)
                        xuanxiang2.performClick();
                    else if(vis.get(timuid)==3)
                        xuanxiang3.performClick();
                    else if(vis.get(timuid)==4)
                        xuanxiang4.performClick();
                }
            }
        });
        tanchuanganniu();
        new NetworkTask().execute();//获取所有题目信息
    }
    private View root_;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bihua, null);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }



    void tanchuanganniu(){
        View view=root_.findViewById(R.id.bihua_tanchuang);
        view.findViewById(R.id.timu1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timuid=0;
                chu();
                if(vis.get(timuid)!=0){
                    if(vis.get(timuid)==1)
                        xuanxiang1.performClick();
                    else if(vis.get(timuid)==2)
                        xuanxiang2.performClick();
                    else if(vis.get(timuid)==3)
                        xuanxiang3.performClick();
                    else if(vis.get(timuid)==4)
                        xuanxiang4.performClick();
                }
            }
        });

        view.findViewById(R.id.timu2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timuid=1;
                chu();
                if(vis.get(timuid)!=0){
                    if(vis.get(timuid)==1)
                        xuanxiang1.performClick();
                    else if(vis.get(timuid)==2)
                        xuanxiang2.performClick();
                    else if(vis.get(timuid)==3)
                        xuanxiang3.performClick();
                    else if(vis.get(timuid)==4)
                        xuanxiang4.performClick();
                }
            }
        });

        view.findViewById(R.id.timu3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timuid=2;
                chu();
                if(vis.get(timuid)!=0){
                    if(vis.get(timuid)==1)
                        xuanxiang1.performClick();
                    else if(vis.get(timuid)==2)
                        xuanxiang2.performClick();
                    else if(vis.get(timuid)==3)
                        xuanxiang3.performClick();
                    else if(vis.get(timuid)==4)
                        xuanxiang4.performClick();
                }
            }
        });

        view.findViewById(R.id.timu4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timuid=3;
                chu();
                if(vis.get(timuid)!=0){
                    if(vis.get(timuid)==1)
                        xuanxiang1.performClick();
                    else if(vis.get(timuid)==2)
                        xuanxiang2.performClick();
                    else if(vis.get(timuid)==3)
                        xuanxiang3.performClick();
                    else if(vis.get(timuid)==4)
                        xuanxiang4.performClick();
                }
            }
        });

        view.findViewById(R.id.timu5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timuid = 4;
                chu();
                if (vis.get(timuid) != 0) {
                    if (vis.get(timuid) == 1)
                        xuanxiang1.performClick();
                    else if (vis.get(timuid) == 2)
                        xuanxiang2.performClick();
                    else if (vis.get(timuid) == 3)
                        xuanxiang3.performClick();
                    else if (vis.get(timuid) == 4)
                        xuanxiang4.performClick();
                }
            }
        });

        view.findViewById(R.id.timu6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timuid = 5;
                chu();
                if (vis.get(timuid) != 0) {
                    if (vis.get(timuid) == 1)
                        xuanxiang1.performClick();
                    else if (vis.get(timuid) == 2)
                        xuanxiang2.performClick();
                    else if (vis.get(timuid) == 3)
                        xuanxiang3.performClick();
                    else if (vis.get(timuid) == 4)
                        xuanxiang4.performClick();
                }
            }
        });

        view.findViewById(R.id.timu7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timuid = 6;
                chu();
                if (vis.get(timuid) != 0) {
                    if (vis.get(timuid) == 1)
                        xuanxiang1.performClick();
                    else if (vis.get(timuid) == 2)
                        xuanxiang2.performClick();
                    else if (vis.get(timuid) == 3)
                        xuanxiang3.performClick();
                    else if (vis.get(timuid) == 4)
                        xuanxiang4.performClick();
                }
            }
        });

        view.findViewById(R.id.timu8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timuid = 7;
                chu();
                if (vis.get(timuid) != 0) {
                    if (vis.get(timuid) == 1)
                        xuanxiang1.performClick();
                    else if (vis.get(timuid) == 2)
                        xuanxiang2.performClick();
                    else if (vis.get(timuid) == 3)
                        xuanxiang3.performClick();
                    else if (vis.get(timuid) == 4)
                        xuanxiang4.performClick();
                }
            }
        });

        view.findViewById(R.id.timu9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timuid = 8;
                chu();
                if (vis.get(timuid) != 0) {
                    if (vis.get(timuid) == 1)
                        xuanxiang1.performClick();
                    else if (vis.get(timuid) == 2)
                        xuanxiang2.performClick();
                    else if (vis.get(timuid) == 3)
                        xuanxiang3.performClick();
                    else if (vis.get(timuid) == 4)
                        xuanxiang4.performClick();
                }
            }
        });

        view.findViewById(R.id.timu10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timuid = 9;
                chu();
                if (vis.get(timuid) != 0) {
                    if (vis.get(timuid) == 1)
                        xuanxiang1.performClick();
                    else if (vis.get(timuid) == 2)
                        xuanxiang2.performClick();
                    else if (vis.get(timuid) == 3)
                        xuanxiang3.performClick();
                    else if (vis.get(timuid) == 4)
                        xuanxiang4.performClick();
                }
            }
        });
    }



    private class NetworkTask extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63020/learning/game1")
                    .method("POST", body)
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
                timu=new ArrayList<>();
                xuanxiang=new ArrayList<>();
                timumiaoshu=new ArrayList<>();
                bihua_timu = (ImageView)root_.findViewById(R.id.bihua_timu);
                for(int i=0;i<result.length();i++){
                    try {
                        JSONObject jo=result.getJSONObject(i);
                        JSONArray questionArray = jo.getJSONArray("question");
                        for(int j=0;j<questionArray.length();j++){
                            JSONObject questionObject = questionArray.getJSONObject(j);
                            timu.add(questionObject.getString("questionName"));
                            timumiaoshu.add(questionObject.getString("questionName2"));
                            JSONArray answareArray = questionObject.getJSONArray("question2");
                            for(int k=0;k<answareArray.length();k++){
                                JSONObject answareObject = answareArray.getJSONObject(k);
                                xuanxiang.add(answareObject.getString("answareName"));
                            }
                        }


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }


                chu();

            }

        }
    }
}