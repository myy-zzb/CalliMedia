package com.example.callimedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class Teaching_resources extends AppCompatActivity {

    private ImageButton ib_return;
    private RecyclerView re_;

    void quan() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(attributes);
        }

        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }

    void lv() {
        List<bt_item> PeopleList = new ArrayList<>();
        bt_item res;
        res = new bt_item(R.drawable.ketanghui_05__bef);
        PeopleList.add(res);
        res = new bt_item(R.drawable.yunduan_05_bef);
        PeopleList.add(res);
        res = new bt_item(R.drawable.ketangjiao_05_bef);
        PeopleList.add(res);
        res = new bt_item(R.drawable.quwei_05_bef);
        PeopleList.add(res);
        res = new bt_item(R.drawable.hanzi_05_bef);
        PeopleList.add(res);
        res = new bt_item(R.drawable.zhisshi_05_bef);
        PeopleList.add(res);

        bt_Adapter stringAdapter = new bt_Adapter(PeopleList, getSupportFragmentManager(), Teaching_resources.this);
        re_.setAdapter(stringAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        re_.setLayoutManager(layoutManager);

    }

    void dianji(final int x) {
        re_.post(new Runnable() {
            @Override
            public void run() {
                RecyclerView.ViewHolder viewHolder = re_.findViewHolderForAdapterPosition(x);
                if (viewHolder == null) {
                    re_.scrollToPosition(x);
                    re_.post(new Runnable() {
                        @Override
                        public void run() {
                            RecyclerView.ViewHolder viewHolder = re_.findViewHolderForAdapterPosition(x);
                            if (viewHolder != null) {
                                ImageButton button = viewHolder.itemView.findViewById(R.id.bt_item);
                                button.performClick();
                            }
                        }
                    });
                } else {
                    ImageButton button = viewHolder.itemView.findViewById(R.id.bt_item);
                    button.performClick();
                }
            }
        });
    }

    void init() {
        re_ = findViewById(R.id.re_button);
        ib_return = findViewById(R.id.Teaching_resources_return);
        quan();
        findViewById(R.id.title_bihua).setVisibility(View.GONE);
        findViewById(R.id.title_pipei).setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaching_resources);

        init();
        lv();

        String ress = getIntent().getStringExtra("yemian");
        if (ress != null) {
            switch (ress) {
                case "hanzichaxun":
                    dianji(4);
                    break;
                case "quweiyouxi":
                    dianji(3);
                    break;
                case "ketanghuida":
                    dianji(0);
                    break;
                case "yunduanziyuan":
                    dianji(1);
                    break;
                case "zhishizhanshi":
                    dianji(5);
                    break;
                case "ketangjiaoxue":
                    dianji(2);
                    break;
            }
        }

        ib_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teaching_resources.this, Home_page.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
