package com.example.callimedia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class Fun_game extends Fragment implements bihua_dialog.OnDialogResultListener, pipei_Dialog.OnDialogResultListener {


    private ImageButton bihua,pipei;
    private View root_;

    private void showFragment(Fragment fragment) {
        FragmentManager fManager = getParentFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.teaching_fragment, fragment);
        fTransaction.commit();
    }

    private void init(){
        bihua = root_.findViewById(R.id.begin_bihua);
        pipei= root_.findViewById(R.id.begin_pipei);
        bihua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.title_bihua).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.title_pipei).setVisibility(View.GONE);
                bihua_dialog dialog = new bihua_dialog(v.getContext(),R.style.mydialog);
                dialog.setOnDialogResultListener(Fun_game.this); // 设置监听器
                dialog.show();
            }
        });
        pipei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.title_bihua).setVisibility(View.GONE);
                getActivity().findViewById(R.id.title_pipei).setVisibility(View.VISIBLE);
                pipei_Dialog dialog = new pipei_Dialog(v.getContext(),R.style.mydialog);
                dialog.setOnDialogResultListener(Fun_game.this); // 设置监听器
                dialog.show();
            }
        });

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fun_game, null);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }


    @Override
    public void onDialogResult(String result) {
        if(result.equals("bihua")){
            showFragment(new bihua());
        }
        else if(result.equals("pipei")){
            showFragment(new pipei());
        }
    }
}