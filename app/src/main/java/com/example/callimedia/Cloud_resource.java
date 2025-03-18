package com.example.callimedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Cloud_resource extends Fragment {

    private ImageButton mingjiazuopin, jiaoxueshipin, zitiezhanshi;
    private View root_;
    int flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_ = inflater.inflate(R.layout.fragment_cloud_resource, container, false);
        init();
        return root_;
    }

    private void init() {
        flag=0;
        mingjiazuopin = root_.findViewById(R.id.mingjiazuopin);
        jiaoxueshipin = root_.findViewById(R.id.jiaoxueshipin);
        zitiezhanshi = root_.findViewById(R.id.zitiezhanshi);

        root_.findViewById(R.id.teching_1).setOnClickListener(listener);
        root_.findViewById(R.id.teching_2).setOnClickListener(listener);
        root_.findViewById(R.id.teching_3).setOnClickListener(listener);
        root_.findViewById(R.id.teching_4).setOnClickListener(listener);
        root_.findViewById(R.id.teching_5).setOnClickListener(listener);
        root_.findViewById(R.id.teching_6).setOnClickListener(listener);
        root_.findViewById(R.id.teching_7).setOnClickListener(listener);
        root_.findViewById(R.id.teching_8).setOnClickListener(listener);
        root_.findViewById(R.id.teching_9).setOnClickListener(listener);

        mingjiazuopin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                ((ImageView) root_.findViewById(R.id.teching_1)).setImageResource(R.drawable.zuopin1);
                ((ImageView) root_.findViewById(R.id.teching_2)).setImageResource(R.drawable.zuopin2);
                ((ImageView) root_.findViewById(R.id.teching_3)).setImageResource(R.drawable.zuopin3);
                ((ImageView) root_.findViewById(R.id.teching_4)).setImageResource(R.drawable.zuopin4);
                ((ImageView) root_.findViewById(R.id.teching_5)).setImageResource(R.drawable.zuopin5);
                ((ImageView) root_.findViewById(R.id.teching_6)).setImageResource(R.drawable.zuopin6);
                ((ImageView) root_.findViewById(R.id.teching_7)).setImageResource(R.drawable.zuopin7);
                ((ImageView) root_.findViewById(R.id.teching_8)).setImageResource(R.drawable.zuopin8);
                ((ImageView) root_.findViewById(R.id.teching_9)).setImageResource(R.drawable.zuopin9);
            }
        });

        zitiezhanshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                ((ImageView) root_.findViewById(R.id.teching_1)).setImageResource(R.drawable.zitie1);
                ((ImageView) root_.findViewById(R.id.teching_2)).setImageResource(R.drawable.zitie2);
                ((ImageView) root_.findViewById(R.id.teching_3)).setImageResource(R.drawable.zitie3);
                ((ImageView) root_.findViewById(R.id.teching_4)).setImageResource(R.drawable.zitie4);
                ((ImageView) root_.findViewById(R.id.teching_5)).setImageResource(R.drawable.zitie5);
                ((ImageView) root_.findViewById(R.id.teching_6)).setImageResource(R.drawable.zitie6);
                ((ImageView) root_.findViewById(R.id.teching_7)).setImageResource(R.drawable.zitie7);
                ((ImageView) root_.findViewById(R.id.teching_8)).setImageResource(R.drawable.zitie8);
                ((ImageView) root_.findViewById(R.id.teching_9)).setImageResource(R.drawable.zitie9);
            }
        });
        jiaoxueshipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                ((ImageView) root_.findViewById(R.id.teching_1)).setImageResource(R.drawable.fenmian);
                ((ImageView) root_.findViewById(R.id.teching_2)).setImageResource(R.drawable.fenmian);
                ((ImageView) root_.findViewById(R.id.teching_3)).setImageResource(R.drawable.fenmian);
                ((ImageView) root_.findViewById(R.id.teching_4)).setImageResource(R.drawable.fenmian);
                ((ImageView) root_.findViewById(R.id.teching_5)).setImageResource(R.drawable.fenmian);
                ((ImageView) root_.findViewById(R.id.teching_6)).setImageResource(R.drawable.fenmian);
                ((ImageView) root_.findViewById(R.id.teching_7)).setImageResource(R.drawable.fenmian);
                ((ImageView) root_.findViewById(R.id.teching_8)).setImageResource(R.drawable.fenmian);
                ((ImageView) root_.findViewById(R.id.teching_9)).setImageResource(R.drawable.fenmian);
            }
        });
        mingjiazuopin.performClick();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(flag==0) {
                Intent intent = new Intent(getContext(), zhanshi.class);
                int id = view.getId();
                Uri uri = null;

                if (id == R.id.teching_1) {
                    uri = saveBitmapToFile(((BitmapDrawable) ((ImageView) root_.findViewById(R.id.teching_1)).getDrawable()).getBitmap());
                } else if (id == R.id.teching_2) {
                    uri = saveBitmapToFile(((BitmapDrawable) ((ImageView) root_.findViewById(R.id.teching_2)).getDrawable()).getBitmap());
                } else if (id == R.id.teching_3) {
                    uri = saveBitmapToFile(((BitmapDrawable) ((ImageView) root_.findViewById(R.id.teching_3)).getDrawable()).getBitmap());
                } else if (id == R.id.teching_4) {
                    uri = saveBitmapToFile(((BitmapDrawable) ((ImageView) root_.findViewById(R.id.teching_4)).getDrawable()).getBitmap());
                } else if (id == R.id.teching_5) {
                    uri = saveBitmapToFile(((BitmapDrawable) ((ImageView) root_.findViewById(R.id.teching_5)).getDrawable()).getBitmap());
                } else if (id == R.id.teching_6) {
                    uri = saveBitmapToFile(((BitmapDrawable) ((ImageView) root_.findViewById(R.id.teching_6)).getDrawable()).getBitmap());
                } else if (id == R.id.teching_7) {
                    uri = saveBitmapToFile(((BitmapDrawable) ((ImageView) root_.findViewById(R.id.teching_7)).getDrawable()).getBitmap());
                } else if (id == R.id.teching_8) {
                    uri = saveBitmapToFile(((BitmapDrawable) ((ImageView) root_.findViewById(R.id.teching_8)).getDrawable()).getBitmap());
                } else if (id == R.id.teching_9) {
                    uri = saveBitmapToFile(((BitmapDrawable) ((ImageView) root_.findViewById(R.id.teching_9)).getDrawable()).getBitmap());
                }

                if (uri != null) {
                    intent.putExtra("zuopin", uri.toString()); // Pass Uri as string
                    startActivity(intent);
                }
            }
            else {
                Intent intent = new Intent(getContext(), video.class);
                int id = view.getId();
                String res = null;
                if (id == R.id.teching_1) {
                    res="a11";
                } else if (id == R.id.teching_2) {
                    res="a22";
                } else if (id == R.id.teching_3) {
                    res="a33";
                } else if (id == R.id.teching_4) {
                    res="a44";
                } else if (id == R.id.teching_5) {
                    res="a55";
                } else if (id == R.id.teching_6) {
                    res="a66";
                } else if (id == R.id.teching_7) {
                    res="a77";
                } else if (id == R.id.teching_8) {
                    res="a88";
                } else if (id == R.id.teching_9) {
                    res="a99";
                }
                intent.putExtra("shipin", res); // Pass Uri as string
                startActivity(intent);
            }
        }
    };


    private Uri saveBitmapToFile(Bitmap bitmap) {
        File file = new File(getContext().getCacheDir(), "image.png");
        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return Uri.fromFile(file); // Return Uri instead of file path
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
