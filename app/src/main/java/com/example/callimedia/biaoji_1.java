package com.example.callimedia;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class biaoji_1 extends Fragment {

    private View root_;
    private GridView gridView;
    private int[] id;
    private ArrayList<biaoji_grid_item> datalist ;



    void lv(){
        biaoji_grid_Adapter x = new biaoji_grid_Adapter(getContext(),datalist);
        gridView.setAdapter(x);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                biaoji_grid_item item =( biaoji_grid_item) parent.getItemAtPosition(position);
                biaoji_Dialog dialog = new biaoji_Dialog(view.getContext(),R.style.mydialog, item.name,item.id);
                dialog.show();
            }
        });
    }
    void init() {
        id = new int[]{R.drawable.hlw_1, R.drawable.hlw_2, R.drawable.hlw_3, R.drawable.hlw_4, R.drawable.hlw_5, R.drawable.hlw_6};
        gridView = (GridView) root_.findViewById(R.id.biaoji_gridview);
        datalist = new ArrayList<biaoji_grid_item>();

        new NetworkTask().execute();//获取学生列表

//        datalist = new ArrayList<biaoji_grid_item>();
//        for(int i=0;i<30;i++){
//            datalist.add(new biaoji_grid_item(BitmapFactory.decodeResource(getResources(), R.drawable.tx_2),"Item "+i,"0"));
//        }

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_biaoji_1, null);
        init();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }


    private class NetworkTask extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Log.d("NetworkTask", "doInBackground started");

            Request request = new Request.Builder()
                    .url("http://192.168.167.174:63070/auth/getall")
                    .get()
                    .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                Log.d("NetworkTask", "Request executed");

                if (response.isSuccessful() && response.body() != null) {
                    String responseBodyString = response.body().string();
                    Log.d("NetworkTask", "Response successful: " + responseBodyString);

                    JSONObject jsonObject = new JSONObject(responseBodyString);
                    return jsonObject.getJSONArray("data");
                } else {
                    Log.e("NetworkTask", "Response failed: " + response.code());
                }
            } catch (IOException e) {
                Log.e("NetworkTask", "IOException occurred", e);
            } catch (JSONException e) {
                Log.e("NetworkTask", "JSONException occurred", e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            Log.d("NetworkTask", "onPostExecute started");

            if (result != null) {
                Log.d("NetworkTask", "Processing result JSONArray");
                biaoji_grid_item item;
                datalist = new ArrayList<>();

                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject obj = result.getJSONObject(i);
                        Log.d("NetworkTask", "Processing item: " + obj.toString());

                        item = new biaoji_grid_item(
                                BitmapFactory.decodeResource(getResources(), id[i % 6]),
                                obj.getString("studentName"),
                                obj.getString("studentId")
                        );
                        datalist.add(item);
                    } catch (JSONException e) {
                        Log.e("NetworkTask", "JSONException occurred while processing JSONArray", e);
                        throw new RuntimeException(e);
                    }
                }
                lv();
            } else {
                Log.e("NetworkTask", "Result is null, no data to process");
            }
        }
    }

}