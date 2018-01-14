package cn.wyh.bs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.wyh.bs.R;
import cn.wyh.bs.common.Global;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Reg extends Activity{

    private ImageView w_back;
    private EditText w_phone;
    private EditText w_code;
    private TextView w_send;
    private EditText w_password;
    private Button w_submit;
    private TextView w_protocol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        /* 获取控件 */
        this.w_back = (ImageView) findViewById(R.id.register_back);
        this.w_phone = (EditText) findViewById(R.id.register_phone);
        this.w_code = (EditText) findViewById(R.id.register_code);
        this.w_send = (TextView) findViewById(R.id.register_send);
        this.w_password = (EditText) findViewById(R.id.register_password);
        this.w_submit = (Button) findViewById(R.id.register_submit);
        this.w_protocol = (TextView) findViewById(R.id.register_protocol);

        /* 返回事件 */
        this.w_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reg.this.finish();
            }
        });

        /* 发送验证码事件 */
        this.w_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Reg.this, "发送验证码", Toast.LENGTH_LONG).show();
            }
        });

        /* 登录事件 */
        this.w_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Reg.this.sendRequest(w_phone.getText().toString(), w_password.getText().toString());
                }
            }
        });

        /* 查看用户协议 */
        this.w_protocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Reg.this, "用户协议", Toast.LENGTH_LONG).show();
            }
        });
    }

    /* 验证输入框 */
    private boolean validate() {
        if (w_phone.getText().toString().equals("") || w_password.getText().toString().equals("")) {
            Toast.makeText(this, "手机号和密码均不能为空", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!Global.isPhoneLegal(w_phone.getText().toString())) {
            Toast.makeText(this, "手机号格式错误", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    /* 注册请求 */
    private void sendRequest(final String phone, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("userPhone", phone)
                            .add("password", password)
                            .build();
                    Request request = new Request.Builder()
                            .url(Global.BASE_URL + "/user/reg.do")
                            .post(requestBody)
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    String dataStr = response.body().string();
                    JSONObject rt = (JSONObject) JSON.parse(dataStr);
                    int status = rt.getInteger("status");
                    String msg = rt.getString("msg");
                    if (status == 1) {
                        Intent intent = new Intent();
                        intent.putExtra("phone", phone);
                        setResult(RESULT_OK, intent);
                        finish();
                        return;
                    }
                     /* 非UI线程错误提示 */
                    Looper.prepare();
                    Toast.makeText(Reg.this, msg, Toast.LENGTH_LONG).show();
                    Looper.loop();
                } catch (Exception e) {
                    Log.i("reg_exception", e.getMessage());
                }
            }
        }).start();
    }
}
