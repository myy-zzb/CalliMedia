package com.example.callimedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home_page extends AppCompatActivity {


    private Class_interaction fra1;
    private Class_management fra2;

    private Student_situation fra3;

    private ImageButton class_tv, classroom, student;
    private TextView tv_class_tv, tv_classroom, tv_student;

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
        fra1 = new Class_interaction();
        fra2=new Class_management();
        fra3=new Student_situation();
        class_tv = (ImageButton) findViewById(R.id.bt_class_tv);
        classroom = (ImageButton) findViewById(R.id.bt_classroom);
        student = (ImageButton) findViewById(R.id.bt_student);

        tv_class_tv=(TextView)findViewById(R.id.class_tv);
        tv_classroom=(TextView)findViewById(R.id.classroom_tv);
        tv_student=(TextView)findViewById(R.id.student_tv);


        class_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(fra1);
//                Toast.makeText(v.getContext(), "Button in item " + " clicked!", Toast.LENGTH_SHORT).show();
                tv_class_tv.setTextColor(Color.parseColor("#ffffc24c"));
                tv_classroom.setTextColor(Color.parseColor("#ffffffff"));
                tv_student.setTextColor(Color.parseColor("#ffffffff"));

            }
        });

        classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(fra2);
//                Toast.makeText(v.getContext(), "Button in item " + " clicked!", Toast.LENGTH_SHORT).show();
                tv_class_tv.setTextColor(Color.parseColor("#ffffffff"));
                tv_classroom.setTextColor(Color.parseColor("#ffffc24c"));
                tv_student.setTextColor(Color.parseColor("#ffffffff"));
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(fra3);
//                Toast.makeText(v.getContext(), "Button in item " + " clicked!", Toast.LENGTH_SHORT).show();
                tv_class_tv.setTextColor(Color.parseColor("#ffffffff"));
                tv_classroom.setTextColor(Color.parseColor("#ffffffff"));
                tv_student.setTextColor(Color.parseColor("#ffffc24c"));
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init();
        class_tv.performClick();
//        showFragment(fra1);

    }
    private void showFragment(Fragment fragment) {
        FragmentManager fManager = getSupportFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.fragment, fragment);
        fTransaction.commit();
    }

}