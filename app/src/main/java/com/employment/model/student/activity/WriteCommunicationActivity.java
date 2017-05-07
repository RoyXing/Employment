package com.employment.model.student.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.employment.R;
import com.employment.app.App;
import com.employment.app.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by roy on 2016/12/24.
 * 发帖界面
 */

public class WriteCommunicationActivity extends AppCompatActivity implements View.OnClickListener {

    TextView customTitle;
    Toolbar mToolbar;
    EditText edTitle, edContent;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_communication);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        edTitle = (EditText) findViewById(R.id.ed_title);
        edContent = (EditText) findViewById(R.id.ed_content);
        customTitle = (TextView) findViewById(R.id.custom_title);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
        customTitle.setText("发帖");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * 进行数据上传
     */
    private void up() throws Exception {
        OkHttpUtils
                .post()
                .url(Constants.ADD_TOPIC)
                .addParams("userId", App.getAppComponent().realmHelper().getStudentInfoBean().getSid() + "")
                .addParams("content", new String(edContent.getText().toString().getBytes(), "UTF-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(WriteCommunicationActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optInt("code") == 10000 && jsonObject.optString("info").equals("success")) {
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                });
    }

    /**
     * 上传检测
     */
    private void check() {
//        if (TextUtils.isEmpty(edTitle.getText().toString())) {
//            Toast.makeText(this, "你的帖子还不完善，请输入标题", Toast.LENGTH_SHORT).show();
//        } else
        if (TextUtils.isEmpty(edContent.getText().toString())) {
            Toast.makeText(this, "你的帖子还不完善，请输入内容", Toast.LENGTH_SHORT).show();
        } else {
            try {
                up();
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                check();
                break;
        }
    }
}
