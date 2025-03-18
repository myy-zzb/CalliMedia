package com.example.callimedia;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;


public class Class_teaching_2 extends Fragment {

    public static final int TAKE_PHOTO = 1;
    private View root_;

    private TextView bianji,xianshi,tianjia;
    private ImageButton bt_return;

    private ImageButton bt_1,bt_2,bt_3,bt_4,bt_5,bt_6,bt_7;

    private ImageButton bt_edit_1,bt_edit_2,bt_edit_3,bt_edit_4,bt_edit_5,bt_edit_6,bt_edit_7;
    private ImageButton bt_edit,bt_add,bt_view;

    private int[] jiaoxueid;
    private int[] leixing;


    private Uri[] zhaopian;
    private ImageButton bt_paizhao,bt_yunduan,bt_bendi;

    private ImageView line1,line2,line3,line4,line5,line6;
    private Class_teaching fra;

    private int weizhi;

    private int num;
    private void showFragment(Fragment fragment) {
        FragmentManager fManager = getParentFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.teaching_fragment, fragment);
        fTransaction.commit();
    }

    private void startCamera() {
        Intent intent2=new Intent(root_.getContext(),Camera.class);//创建跳转到Camera显示的窗口的Intent
        startActivity(intent2);//进入camera的窗口界面
    }

    private void showPopupWindow(View anchorView,int position) {


        // 设置 contentView
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popwindow, null);
        PopupWindow mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        // 初始化按钮
        bt_paizhao = contentView.findViewById(R.id.paizhaodaoru);
        bt_bendi = contentView.findViewById(R.id.bendidaoru);
        bt_yunduan = contentView.findViewById(R.id.yunduandaoru);

        // 设置各个控件的点击响应
        bt_paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "1", Toast.LENGTH_SHORT).show();
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO);
                } else {
                    // 启动相机程序
                    startCamera();
                }
            }
        });

        bt_bendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weizhi=position;
//                Toast.makeText(v.getContext(), "2", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //调用setDataAndType方法，指定了选择的数据类型为图片
                //设置数据的URI为MediaStore.Images.Media.EXTERNAL_CONTENT_URI，表示选择外部存储中的图片
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                //调用startActivityForResult方法，将Intent发送给系统，并指定一个请求码为2，以便在之后的回调中处理用户选择的图片
                startActivityForResult(intent, 2);


            }
        });

        bt_yunduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "3", Toast.LENGTH_SHORT).show();
            }
        });

        // 显示 PopupWindow 在 anchorView 的右边
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);

        // 获取 anchorView 的宽度
        int anchorWidth = anchorView.getWidth();

        // 设置 x 轴偏移量，使 PopupWindow 出现在按钮的右边
        int offsetX = anchorWidth;

        // 显示 PopupWindow
        mPopWindow.showAsDropDown(anchorView, offsetX, 0);
    }




    private void init(){
         bianji=root_.findViewById(R.id.bianji);
         xianshi=root_.findViewById(R.id.xianshi);
         tianjia=root_.findViewById(R.id.tianjia);
        zhaopian=new Uri[]{null,null,null,null,null,null,null};
        leixing=new int[]{0,1,0,0,0,0,0};//0代表图片，1代表视频
        jiaoxueid=new int[]{R.drawable.zitie1,R.raw.a22,R.drawable.zitie2,R.drawable.zitie4,R.drawable.zitie5,R.drawable.zitie6,R.drawable.zitie7};
        fra=new Class_teaching();
        bt_return=root_.findViewById(R.id.teaching_return);
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(fra);
            }
        });

        bt_bendi=root_.findViewById(R.id.bendidaoru);
        bt_yunduan=root_.findViewById(R.id.yunduandaoru);
        bt_paizhao=root_.findViewById(R.id.paizhaodaoru);

        num=0;
        bt_1=root_.findViewById(R.id.teaching_bt_1);
        bt_2=root_.findViewById(R.id.teaching_bt_2);
        bt_2.setVisibility(View.GONE);
        bt_3=root_.findViewById(R.id.teaching_bt_3);
        bt_3.setVisibility(View.GONE);
        bt_4=root_.findViewById(R.id.teaching_bt_4);
        bt_4.setVisibility(View.GONE);
        bt_5=root_.findViewById(R.id.teaching_bt_5);
        bt_5.setVisibility(View.GONE);
        bt_6=root_.findViewById(R.id.teaching_bt_6);
        bt_6.setVisibility(View.GONE);
        bt_7=root_.findViewById(R.id.teaching_bt_7);
        bt_7.setVisibility(View.GONE);

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leixing[0]==0){
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("haha",String.valueOf(jiaoxueid[0]));
                    startActivity(intent);
                }
                else if(leixing[0]==1){
                    Intent intent=new Intent(root_.getContext(),video.class);
                    intent.putExtra("shipin","a22");
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("zuopin",zhaopian[0].toString());
                    startActivity(intent);
                }
            }
        });
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leixing[1]==0){
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("haha",String.valueOf(jiaoxueid[1]));
                    startActivity(intent);
                }
                else if(leixing[1]==1){
                    Intent intent=new Intent(root_.getContext(),video.class);
                    intent.putExtra("shipin","a22");
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("zuopin",zhaopian[1]);
                    startActivity(intent);
                }
            }
        });
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leixing[2]==0){
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("haha",String.valueOf(jiaoxueid[2]));
                    startActivity(intent);
                }
                else if(leixing[2]==1){
                    Intent intent=new Intent(root_.getContext(),video.class);
                    intent.putExtra("shipin","a22");
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("zuopin",zhaopian[2]);
                    startActivity(intent);
                }
            }
        });
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leixing[3]==0){
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("haha",String.valueOf(jiaoxueid[3]));
                    startActivity(intent);
                }
                else if(leixing[3]==1){
                    Intent intent=new Intent(root_.getContext(),video.class);
                    intent.putExtra("shipin","a22");
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("zuopin",zhaopian[3]);
                    startActivity(intent);
                }
            }
        });
        bt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leixing[4]==0){
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("haha",String.valueOf(jiaoxueid[4]));
                    startActivity(intent);
                }
                else if(leixing[4]==1){
                    Intent intent=new Intent(root_.getContext(),video.class);
                    intent.putExtra("shipin","a22");
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("zuopin",zhaopian[4]);
                    startActivity(intent);
                }
            }
        });

        bt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leixing[5]==0){
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("haha",String.valueOf(jiaoxueid[5]));
                    startActivity(intent);
                }
                else if(leixing[5]==1){
                    Intent intent=new Intent(root_.getContext(),video.class);
                    intent.putExtra("shipin","a22");
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("zuopin",zhaopian[5]);
                    startActivity(intent);
                }
            }
        });
        bt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leixing[6]==0){
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("haha",String.valueOf(jiaoxueid[6]));
                    startActivity(intent);
                }
                else if(leixing[6]==1){
                    Intent intent=new Intent(root_.getContext(),video.class);
                    intent.putExtra("shipin","a22");
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(root_.getContext(),zhanshi.class);
                    intent.putExtra("zuopin",zhaopian[6]);
                    startActivity(intent);
                }
            }
        });


        bt_edit_1=root_.findViewById(R.id.teaching_bt_edit_1);
        bt_edit_1.setVisibility(View.GONE);
        bt_edit_2=root_.findViewById(R.id.teaching_bt_edit_2);
        bt_edit_2.setVisibility(View.GONE);
        bt_edit_3=root_.findViewById(R.id.teaching_bt_edit_3);
        bt_edit_3.setVisibility(View.GONE);
        bt_edit_4=root_.findViewById(R.id.teaching_bt_edit_4);
        bt_edit_4.setVisibility(View.GONE);
        bt_edit_5=root_.findViewById(R.id.teaching_bt_edit_5);
        bt_edit_5.setVisibility(View.GONE);
        bt_edit_6=root_.findViewById(R.id.teaching_bt_edit_6);
        bt_edit_6.setVisibility(View.GONE);
        bt_edit_7=root_.findViewById(R.id.teaching_bt_edit_7);
        bt_edit_7.setVisibility(View.GONE);

        bt_edit=root_.findViewById(R.id.teaching_bt_edit);
        bt_view=root_.findViewById(R.id.teaching_bt_view);
        bt_add=root_.findViewById(R.id.teaching_bt_add);

        line1=root_.findViewById(R.id.line_1);
        line1.setVisibility(View.GONE);
        line2=root_.findViewById(R.id.line_2);
        line2.setVisibility(View.GONE);
        line3=root_.findViewById(R.id.line_3);
        line3.setVisibility(View.GONE);
        line4=root_.findViewById(R.id.line_4);
        line4.setVisibility(View.GONE);
        line5=root_.findViewById(R.id.line_5);
        line5.setVisibility(View.GONE);
        line6=root_.findViewById(R.id.line_6);
        line6.setVisibility(View.GONE);


        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tianjia.setTextColor(Color.parseColor("#748564"));
                bianji.setTextColor(Color.parseColor("#B3B4B3"));
                xianshi.setTextColor(Color.parseColor("#B3B4B3"));
                tianjia.setText("添加");
                bianji.setText("编辑");
                xianshi.setText("查看");


                num++;
                if(num==1){
                    line1.setVisibility(View.VISIBLE);
                    bt_2.setVisibility(View.VISIBLE);
                }
                else if(num==2){
                    line2.setVisibility(View.VISIBLE);
                    bt_3.setVisibility(View.VISIBLE);
                }
                else if(num==3){
                    line3.setVisibility(View.VISIBLE);
                    bt_4.setVisibility(View.VISIBLE);
                }
                else if(num==4){
                    line4.setVisibility(View.VISIBLE);
                    bt_5.setVisibility(View.VISIBLE);
                }
                else if(num==5){
                    line5.setVisibility(View.VISIBLE);
                    bt_6.setVisibility(View.VISIBLE);
                }
                else if(num==6){
                    line6.setVisibility(View.VISIBLE);
                    bt_7.setVisibility(View.VISIBLE);
                }
            }
        });

        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tianjia.setTextColor(Color.parseColor("#B3B4B3"));
                bianji.setTextColor(Color.parseColor("#748564"));
                xianshi.setTextColor(Color.parseColor("#B3B4B3"));
                tianjia.setText("添加");
                bianji.setText("编辑");
                xianshi.setText("查看");
                if(bt_1.getVisibility()==View.VISIBLE)
                    bt_edit_1.setVisibility(View.VISIBLE);
                if(bt_2.getVisibility()==View.VISIBLE)
                    bt_edit_2.setVisibility(View.VISIBLE);
                if(bt_3.getVisibility()==View.VISIBLE)
                    bt_edit_3.setVisibility(View.VISIBLE);
                if(bt_4.getVisibility()==View.VISIBLE)
                    bt_edit_4.setVisibility(View.VISIBLE);
                if(bt_5.getVisibility()==View.VISIBLE)
                    bt_edit_5.setVisibility(View.VISIBLE);
                if(bt_6.getVisibility()==View.VISIBLE)
                    bt_edit_6.setVisibility(View.VISIBLE);
                if(bt_7.getVisibility()==View.VISIBLE)
                    bt_edit_7.setVisibility(View.VISIBLE);

            }
        });

        bt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tianjia.setTextColor(Color.parseColor("#B3B4B3"));
                bianji.setTextColor(Color.parseColor("#B3B4B3"));
                xianshi.setTextColor(Color.parseColor("#748564"));
                tianjia.setText("添加");
                bianji.setText("编辑");
                xianshi.setText("查看");
                bt_edit_1.setVisibility(View.GONE);
                bt_edit_2.setVisibility(View.GONE);
                bt_edit_3.setVisibility(View.GONE);
                bt_edit_4.setVisibility(View.GONE);
                bt_edit_5.setVisibility(View.GONE);
                bt_edit_6.setVisibility(View.GONE);
                bt_edit_7.setVisibility(View.GONE);
            }
        });

        bt_edit_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(bt_edit_1,0);
            }
        });

        bt_edit_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(bt_edit_2,1);
            }
        });

        bt_edit_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(bt_edit_3,2);
            }
        });

        bt_edit_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(bt_edit_4,3);
            }
        });


        bt_edit_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(bt_edit_5,4);
            }
        });

        bt_edit_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(bt_edit_6,5);
            }
        });

        bt_edit_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(bt_edit_7,6);
            }
        });


    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_class_teaching_2, null);
        init();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode ==Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                // 设置图片到 ImageView

//                ((ImageView)root_.findViewById(R.id.shishi)).setImageURI(selectedImageUri);

                zhaopian[weizhi]=selectedImageUri;
                leixing[weizhi]=-1;
            }
        }
    }

}