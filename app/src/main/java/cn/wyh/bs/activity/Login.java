package cn.wyh.bs.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import cn.wyh.bs.R;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.entity.User;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends BaseActivity {

    private EditText w_phone;
    private EditText w_password;
    private TextView w_reg;
    private TextView w_forget;
    private Button w_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        /* 获取控件 */
        this.w_phone = (EditText) findViewById(R.id.login_phone);
        this.w_password = (EditText) findViewById(R.id.login_password);
        this.w_reg = (TextView) findViewById(R.id.login_reg);
        this.w_forget = (TextView) findViewById(R.id.login_forget);
        this.w_submit = (Button) findViewById(R.id.login_submit);

        /* 忘记密码事件 */
        this.w_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "忘记密码", Toast.LENGTH_LONG).show();
            }
        });

        /* 注册事件 */
        this.w_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Reg.class);
                startActivityForResult(intent, 1);
            }
        });

        /* 提交事件 */
        this.w_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (validate()) {
                    sendRequest(w_phone.getText().toString(), w_password.getText().toString());
                }
                */
                //测试环境，不验证，不请求
                sendRequest0(w_phone.getText().toString(), w_password.getText().toString());
            }
        });

        /* 自动登录 */
        //this.init();
    }

    /* 接收reg的数据*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                this.w_phone.setText(data.getStringExtra("phone"));
                Toast.makeText(Login.this, "注册成功，请登录", Toast.LENGTH_LONG).show();
            }
        }
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

    /* 自动登录*/
    private void init() {
        SharedPreferences editor = getSharedPreferences("user", MODE_PRIVATE);
        String phone = editor.getString("phone", "");
        String password = editor.getString("password", "");
        if (!phone.equals("") && !password.equals("")) {
            this.sendRequest(phone, password);
        }
    }

    /* 静态登录，不请求服务器 */
    private void sendRequest0(String phone, String password) {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    /* 登录服务器验证 */
    private void sendRequest(final String phone, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String info;
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("phone", phone)
                            .add("password", password)
                            .build();
                    Request request = new Request.Builder()
                            .url(Global.BASE_URL + "/user/login.do")
                            .post(requestBody)
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    String dataStr = response.body().string();
                    JSONObject jsonObject = JSONObject.parseObject(dataStr);
                    int status = jsonObject.getInteger("status");
                    if (status == 1) {
                        User user = jsonObject.getObject("user", User.class);
                        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                        editor.putString("phone", user.getUserPhone());
                        editor.putString("password", user.getPassword());
                        editor.apply();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();  //结束Login activity
                        return;
                    } else {
                        info = jsonObject.getString("msg");
                    }
                    /* 非UI线程错误提示 */
                    Looper.prepare( );
                    Toast.makeText(Login.this, info, Toast.LENGTH_LONG).show();
                    Looper.loop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
