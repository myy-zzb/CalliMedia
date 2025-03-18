package com.example.callimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class register extends AppCompatActivity {


    private ImageButton icon_return;
    private CheckBox check_button;

    private ImageButton ensure;

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

    void init()
    {
        quan();

        icon_return =(ImageButton) findViewById(R.id.icon_return);
        check_button=(CheckBox) findViewById(R.id.check_button);
        change_color();
        ensure=(ImageButton) findViewById(R.id.register_ensure);

    }
    void change_color(){

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();


        icon_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(register.this,MainActivity.class);
                startActivity(intent);

                finish();
            }
        });




        ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(register.this,register2.class);
                startActivity(intent);

                finish();
            }
        });
    }
}