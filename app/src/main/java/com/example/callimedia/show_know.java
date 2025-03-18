package com.example.callimedia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class    show_know extends Fragment {

    private View root_;
    private RecyclerView re_;

    private EditText et_input;

    private ImageButton release;

    List<chat_item> PeopleList;

    public static final String chatGptKey = "sk-oV56LO0LYktYEM7HAcTLZjheVc3Y4zbjDWL4dmnQqgnwQt1w";

    public static final MediaType JSON = MediaType.get("application/json");
    void callAPI(String question) {
//        messageList.add(new Message("Thinking...", Message.SEND_BY_BOT));
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS) // 设置连接超时时长为10秒
                .readTimeout(60, TimeUnit.SECONDS) // 设置读取超时时长为10秒
                .writeTimeout(60, TimeUnit.SECONDS) // 设置写入超时时长为10秒
                .build();
        JSONObject jsonBody = new JSONObject();
        try {
            JSONArray messagesArray = new JSONArray();

            // 创建消息对象1
            JSONObject messageObject1 = new JSONObject();
            messageObject1.put("role", "user");
            messageObject1.put("content", question);

            // 将消息对象添加到数组中
            messagesArray.put(messageObject1);

            // 创建消息对

            // 创建包含消息数组的 JSON 对象
            jsonBody.put("messages", messagesArray);
            jsonBody.put("model", "gpt-3.5-turbo");
//            jsonBody.put("prompt", question);
//            jsonBody.put("role", "user");
//            jsonBody.put("max_tokens", 4000);
//            jsonBody.put("temperature", 0);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // 创建请求体
        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
//                .url("https://api.openai.com/v1/completions")
                .url("https://api.chatanywhere.tech/v1/chat/completions")//免费api
                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer " + chatGptKey)
                .header("Authorization", chatGptKey)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        JSONObject message = (JSONObject) jsonArray.get(0);
                        String result = ((JSONObject)message.get("message")).getString("content");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    addResponse("Failed to load response due to " + response.body().toString());
                }
            }
        });
    }


    void addResponse(String response) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<chat_text> content=new ArrayList<>();
                content.add(new chat_text(response,0xff676c90,8));
                change(content,R.drawable.avatar_duihua_05_6);
            }
        });





    }
    private void init() {
        re_=(RecyclerView) root_.findViewById(R.id.chat_item);
        release=(ImageButton) root_.findViewById(R.id.chat_release);
        et_input=(EditText) root_.findViewById(R.id.message_fasong);
        release.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String input=et_input.getText().toString();

                List<chat_text> content=new ArrayList<>();
                content.add(new chat_text(input,0xff676c90,8));
                change(content,R.drawable.avatar_person_05_6);
                et_input.setText("");
                callAPI(input);


            }
        });

        et_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // 检查是否是回车键或者输入法的 "Done" 动作
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_SEND ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    // 在这里执行发送操作，例如：
                    String input = et_input.getText().toString();
                    if (!input.isEmpty()) {
                        List<chat_text> content = new ArrayList<>();
                        content.add(new chat_text(input, 0xff676c90, 8));
                        change(content, R.drawable.avatar_person_05_6);
                        et_input.setText("");  // 清空输入框
                        callAPI(input);
                    }
                    return true;  // 表示你已处理该事件
                }
                return false;
            }
        });
    }

    private void change(List<chat_text> x, int y) {
        // 更新数据
        PeopleList.add(new chat_item(x, y));

        // 如果适配器已经存在，只需通知数据已更改
        if (re_.getAdapter() != null) {
            chat_Adapter adapter = (chat_Adapter) re_.getAdapter();
            adapter.notifyDataSetChanged();

            // 滚动到列表底部
            re_.post(new Runnable() {
                @Override
                public void run() {
                    re_.smoothScrollToPosition(PeopleList.size() - 1);
                }
            });
        } else {
            // 如果适配器不存在，创建并绑定适配器
            chat_Adapter stringAdapter = new chat_Adapter(PeopleList);
            re_.setAdapter(stringAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            re_.setLayoutManager(layoutManager);

            // 滚动到列表底部
            re_.post(new Runnable() {
                @Override
                public void run() {
                    re_.smoothScrollToPosition(PeopleList.size() - 1);
                }
            });
        }
    }

    public void lv(){
        PeopleList=new ArrayList<>();//创建装People的List
        chat_item res;
        int touxiang=R.drawable.avatar_duihua_05_6;
        List<chat_text> content=new ArrayList<>();


        content.add(new chat_text("你好，",0xff05073b,10));
        content.add(new chat_text("我是书法小虫",0xff05073b,10));
        content.add(new chat_text("作为你的智能伙伴，我既能写文案、想点子，又能你陪你聊天、答疑解惑。",0xff676c90,8));



        res=new chat_item(content,touxiang);


        PeopleList.add(res);





        chat_Adapter stringAdapter = new chat_Adapter(PeopleList);
        //配置适配器
        re_.setAdapter(stringAdapter);
        //配置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        re_.setLayoutManager(layoutManager);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root_ = LayoutInflater.from(getContext()).inflate(R.layout.fragment_show_know, null);
        init();
        lv();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return root_;

    }


}