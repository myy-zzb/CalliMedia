package com.example.callimedia;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class login extends AppCompatActivity {

    private ImageButton icon_return, login_bt;
    private EditText et_zhanghao, et_mima;
    private CheckBox check_button;
    int flag; // 如果等于0，就是在密码登录界面，等于1就是在验证码登录界面
    int wait; // 如果等于0，代表可以点击获取验证码，如果等于1代表正在倒数
    private TextView mima, yanzhengma, hq_yzm, tishi;

    void error_tishi() {
        LayoutInflater inflater = getLayoutInflater();
        View customToastView = inflater.inflate(R.layout.tishi, null);

        // 创建自定义 Toast
        Toast customToast = new Toast(getApplicationContext());
        customToast.setDuration(Toast.LENGTH_SHORT); // 可以设置为 Toast.LENGTH_SHORT 或 Toast.LENGTH_LONG
        customToast.setView(customToastView);

        // 设置 Toast 显示位置：中央
        customToast.setGravity(Gravity.CENTER, 0, 0);
        // 显示自定义 Toast
        customToast.show();
    }

    void change_color() {
        String text = "同意《用户注册协议》、《隐私政策》、《儿童隐私政策》和《双减政策响应声明》";
        SpannableString spannableString = new SpannableString(text);

        // 设置《用户注册协议》的颜色
        int start1 = text.indexOf("《用户注册协议》");
        int end1 = start1 + "《用户注册协议》".length();
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), start1, end1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置《隐私政策》的颜色
        int start2 = text.indexOf("《隐私政策》");
        int end2 = start2 + "《隐私政策》".length();
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置《儿童隐私政策》的颜色
        int start3 = text.indexOf("《儿童隐私政策》");
        int end3 = start3 + "《儿童隐私政策》".length();
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), start3, end3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 设置《双减政策响应声明》的颜色
        int start4 = text.indexOf("《双减政策响应声明》");
        int end4 = start4 + "《双减政策响应声明》".length();
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), start4, end4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        check_button.setText(spannableString);
    }

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
        icon_return = findViewById(R.id.icon_return);
        check_button = findViewById(R.id.check_button);
        change_color();
        mima = findViewById(R.id.pass_tv);
        yanzhengma = findViewById(R.id.ma_tv);
        hq_yzm = findViewById(R.id.huoqu_yzm);
        flag = 0;
        hq_yzm.setVisibility(View.GONE);
        et_zhanghao = findViewById(R.id.et_zhanghao);
        et_mima = findViewById(R.id.et_mima);
        wait = 0;
        tishi = findViewById(R.id.tishi);
        login_bt = findViewById(R.id.login_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        icon_return.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        yanzhengma.setOnClickListener(v -> {
            if (flag == 1) return;
            flag = 1;
            hq_yzm.setVisibility(View.VISIBLE);
            yanzhengma.setTextSize(16);
            mima.setTextSize(12);
            et_mima.setHint("请输入验证码");
            tishi.setText("未有账号？点此注册");
        });

        mima.setOnClickListener(v -> {
            if (flag == 0) return;
            flag = 0;
            hq_yzm.setVisibility(View.GONE);
            mima.setTextSize(16);
            yanzhengma.setTextSize(12);
            et_mima.setHint("请输入密码");
            tishi.setText("忘记密码？");
        });

        hq_yzm.setOnClickListener(v -> {
            if (wait == 1) return;
//            Toast.makeText(login.this, "haha", Toast.LENGTH_SHORT).show();

            new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long seconds = (millisUntilFinished / 1000) % 60;
                    String timeLeft = String.format("%02dS后重试", seconds);
                    hq_yzm.setText(timeLeft);
                    hq_yzm.setTextColor(Color.parseColor("#ffdcdcdc"));
                }

                @Override
                public void onFinish() {
                    hq_yzm.setText("重新获取");
                    hq_yzm.setTextColor(Color.parseColor("#FFFFFFFF"));
                }
            }.start();
        });

        login_bt.setOnClickListener(v -> {
            if (!check_button.isChecked()) {
                error_tishi();
                return;
            }

//            new NetworkTask().execute();
            Intent intent = new Intent(login.this, Home_page.class);
            startActivity(intent);
            finish();
        });

    }

    // 内部类：处理网络请求的 AsyncTask
    private class NetworkTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "username=12345&password=12345&name=张三");
            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/user/login")
                    .method("POST", body)
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .addHeader("Accept", "*/*")
                    .addHeader("Host", "121.40.153.72:63070")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response != null && response.body() != null) {
                    String responseBodyString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseBodyString);
                    return jsonObject.getString("msg");
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String msg) {
            if (msg != null) {
                Toast.makeText(login.this, msg, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this, Home_page.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
