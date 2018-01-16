package cn.wyh.bs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
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

public class Reg extends BaseActivity{

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
                JSONObject request = new JSONObject();
                request.put("userPhone", phone);
                request.put("password", password);
                JSONObject response = Global.httpPost("/user/reg.do", request.toJSONString());

                String msg;
                if (response.getInteger("code") == 1) {
                    JSONObject rt = (JSONObject) JSON.parse(response.getString("respStr"));
                    int status = rt.getInteger("status");
                    msg = rt.getString("msg");
                    if (status == 1) {
                        Intent intent = new Intent();
                        intent.putExtra("phone", phone);
                        setResult(RESULT_OK, intent);
                        finish();
                        return;
                    }
                } else {
                    msg = response.getString("msg");
                }
                 /* 非UI线程错误提示 */
                Looper.prepare();
                Toast.makeText(Reg.this, msg, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();
    }
}
