package com.treasure_ct.happiness_xt.activity.assistant;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.AssistantRobotListAdapter;
import com.treasure_ct.happiness_xt.bean.AssistantRobotBean;
import com.treasure_ct.happiness_xt.bean.UserInfoBean;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.StringContents;
import com.treasure_ct.happiness_xt.utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LifeAssistantRobotActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    private List<AssistantRobotBean> list;
    private AssistantRobotListAdapter adapter;
    private TextView send;
    private EditText input;
    private ListView listView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 201:
                    String text = msg.getData().getString("text");
                    AssistantRobotBean assistantRobotBean = new AssistantRobotBean();
                    assistantRobotBean.setText(text);
                    assistantRobotBean.setUserName("小T");
                    assistantRobotBean.setReceived(true);
                    assistantRobotBean.setTime(System.currentTimeMillis());
                    list.add(assistantRobotBean);
                    adapter.notifyDataSetChanged();
                    int count = adapter.getCount();
                    listView.setSelection(count - 1);
                    break;
                case 400:
                    String error = msg.getData().getString("error");
                    Toast.makeText(LifeAssistantRobotActivity.this, "原因：" + error, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_assistant_robot);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        btn_back.setVisibility(View.VISIBLE);
        title.setText("智能机器人");

        initFindId();
        initListView();
        initClick();
        receiveMessage("回答");
    }

    private void initFindId() {
        send = (TextView) findViewById(R.id.assistant_robot_send);
        input = (EditText) findViewById(R.id.assistant_robot_input);
        listView = (ListView) findViewById(R.id.assistant_robot_listView);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new AssistantRobotListAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void initClick() {
        send.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        input.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assistant_robot_send:
                String content = input.getText().toString().trim();
                AssistantRobotBean assistantRobotBean = new AssistantRobotBean();
                assistantRobotBean.setText(content);
                if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))){
                    String nick_name = ((UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo")).getNick_name();
                    assistantRobotBean.setUserName(nick_name);
                }else {
                    assistantRobotBean.setUserName("我");
                }
                assistantRobotBean.setReceived(false);
                assistantRobotBean.setTime(System.currentTimeMillis());
                list.add(assistantRobotBean);
                adapter.notifyDataSetChanged();
                int count = adapter.getCount();
                listView.setSelection(count - 1);
                input.setText("");

                receiveMessage(content);
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!Tools.isNull(s.toString().trim())){
            send.setClickable(true);
            send.setBackgroundResource(R.drawable.bottom_gold_yellow_radius5);
        }else {
            send.setClickable(false);
            send.setBackgroundResource(R.drawable.bottom_gray_radius5);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    private void receiveMessage(String content) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("key", StringContents.Turing_Robot_APIKEY)
                .add("info", content)
                .build();
        Request request = new Request.Builder()
                .url(StringContents.Turing_Robot_url)
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Bundle bundle = new Bundle();
                bundle.putString("error", e.getMessage());
                Message message = new Message();
                message.setData(bundle);
                message.what = 400;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    String code = jsonObject.getString("code");
                    if (code.equals("100000")) {
                        String text = jsonObject.optString("text");
                        Bundle bundle = new Bundle();
                        bundle.putString("text", text);
                        Message message = new Message();
                        message.setData(bundle);
                        message.what = 201;
                        mHandler.sendMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
