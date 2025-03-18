package com.example.callimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class zhanshi extends AppCompatActivity {



    private ImageView fanhui;

    private PhotoView photo;
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
        fanhui = findViewById(R.id.pestDetection1);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        photo = findViewById(R.id.picture1);

        String uriString = getIntent().getStringExtra("zuopin");
        Log.d("hahaxx", "uriString: " + uriString);
        if (uriString != null) {
            Uri imageUri = Uri.parse(uriString);
            Bitmap bitmap = null;
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                if (inputStream != null) {
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close(); // Ensure the stream is closed after use
                } else {
                    Log.e("ImageLoad", "InputStream is null for URI: " + imageUri);
                }
            } catch (FileNotFoundException e) {
                Log.e("ImageLoad", "FileNotFoundException while loading image", e);
            } catch (IOException e) {
                Log.e("ImageLoad", "IOException while closing InputStream", e);
            }
            if (bitmap != null) {
                photo.setImageBitmap(bitmap);
            } else {
                Log.d("ImageLoad", "uriString: " + uriString);
                Log.e("ImageLoad", "Bitmap is null, unable to set image.");
            }
        } else {
            Log.e("ImageLoad", "uriString is null, unable to load image.");
        }

        String ress = getIntent().getStringExtra("hanzi");
        if (ress != null && !ress.equals("null")) {
            Glide.with(this)
                    .load(ress)
                    .into(photo);
        }
        else if(uriString==null){
            photo.setImageResource(Integer.parseInt(getIntent().getStringExtra("haha")));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanshi);

        init();
    }
    public Bitmap getImageFromUrl(String urlString) {
        Bitmap bitmap = null;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlString)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                InputStream inputStream = response.body().byteStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}