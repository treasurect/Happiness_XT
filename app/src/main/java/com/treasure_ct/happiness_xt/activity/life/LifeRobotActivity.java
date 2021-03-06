package com.treasure_ct.happiness_xt.activity.life;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.LifeRobotListAdapter;
import com.treasure_ct.happiness_xt.bean.LifeRobotBean;
import com.treasure_ct.happiness_xt.bean.UserInfoBean;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
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
import okhttp3.Response;

public class LifeRobotActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    private List<LifeRobotBean> list;
    private LifeRobotListAdapter adapter;
    private TextView send;
    private EditText input;
    private ListView listView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 201:
                    String text = msg.getData().getString("text");
                    LifeRobotBean lifeRobotBean = new LifeRobotBean();
                    lifeRobotBean.setText(text);
                    lifeRobotBean.setUserName("小T");
                    lifeRobotBean.setReceived(true);
                    lifeRobotBean.setTime(System.currentTimeMillis());
                    list.add(lifeRobotBean);
                    adapter.notifyDataSetChanged();
                    int count = adapter.getCount();
                    listView.setSelection(count - 1);
                    break;
                case 400:
                    String error = msg.getData().getString("error");
                    Toast.makeText(LifeRobotActivity.this, "原因：" + error, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_robot);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        btn_back.setVisibility(View.VISIBLE);
        title.setText("智能机器人");
        showHintDialog();
        initFindId();
        initListView();
        initClick();
        receiveMessage("回答");
    }

    private void showHintDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("你可以问我点什么");
        builder.setMessage("吉凶查询、聊天对话、问答百科、生活百科、知识库、星座运势、新闻资讯、成语接龙、故事大全、菜谱大全、快递查询、笑话大全、天气查询、图片搜索、列车查询、航班查询、数字计算、日期查询、股票查询、路程报价、公交查询、绕口令、顺口溜、、租房信息、歇后语、影视搜索、实时路况、果蔬报价、汽油报价、脑筋急转弯、中英互译、城市邮编、附近酒店、附近餐厅");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    private void initFindId() {
        send = (TextView) findViewById(R.id.assistant_robot_send);
        input = (EditText) findViewById(R.id.assistant_robot_input);
        listView = (ListView) findViewById(R.id.assistant_robot_listView);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new LifeRobotListAdapter(this, list);
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
                LifeRobotBean lifeRobotBean = new LifeRobotBean();
                lifeRobotBean.setText(content);
                if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))) {
                    String nick_name = ((UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo")).getNick_name();
                    lifeRobotBean.setUserName(nick_name);
                } else {
                    lifeRobotBean.setUserName("我");
                }
                lifeRobotBean.setReceived(false);
                lifeRobotBean.setTime(System.currentTimeMillis());
                list.add(lifeRobotBean);
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
        if (!Tools.isNull(s.toString().trim())) {
            send.setClickable(true);
            send.setBackgroundResource(R.drawable.bottom_gold_yellow_radius5);
        } else {
            send.setClickable(false);
            send.setBackgroundResource(R.drawable.bottom_gray_radius5);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void receiveMessage(String content) {
        FormBody body = new FormBody.Builder()
                .add("key", StringContents.Turing_Robot_APIKEY)
                .add("info", content)
                .build();
        HttpHelper.doPostCall(StringContents.Turing_Robot_url,
                this,
                body,
                new Callback() {
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
