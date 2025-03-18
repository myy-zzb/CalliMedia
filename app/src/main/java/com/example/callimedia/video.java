package com.example.callimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class video extends AppCompatActivity implements View.OnClickListener{

    private VideoView videoView;
    private ImageButton btn_start;
    private ImageButton btn_pause;
    private ImageButton btn_stop,btn_return;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        quan();
        bindViews();
    }

    private void bindViews() {
        videoView = (VideoView) findViewById(R.id.video_view);

        btn_start = (ImageButton) findViewById(R.id.btn_start);
        btn_pause = (ImageButton) findViewById(R.id.btn_pause);
        btn_stop = (ImageButton) findViewById(R.id.btn_stop);
        btn_return = (ImageButton) findViewById(R.id.btn_return);

        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_return.setOnClickListener(this);



        //读取放在raw目录下的文件
        String xx=getIntent().getStringExtra("shipin");
        if(xx.equals("a11"))
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.a11));
        else if(xx.equals("a22"))
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.a22));
        else if(xx.equals("a33"))
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.a33));
        else if(xx.equals("a44"))
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.a44));
        else if(xx.equals("a55"))
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.a55));
        else if(xx.equals("a66"))
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.a66));
        else if(xx.equals("a77"))
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.a77));
        else if(xx.equals("a88"))
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.a88));
        else if(xx.equals("a99"))
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.a99));
        videoView.setMediaController(new MediaController(this));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_start)
            videoView.start();
        else if(v.getId() == R.id.btn_pause)
            videoView.pause();
        else if(v.getId() == R.id.btn_stop)
            videoView.stopPlayback();
        else if(v.getId() == R.id.btn_return)
            finish();
    }
}