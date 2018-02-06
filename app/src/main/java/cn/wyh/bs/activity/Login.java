package cn.wyh.bs.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.home.CityActivity;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.ImgProcess;
import cn.wyh.bs.common.PermissionUtils;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.DBHelper;
import cn.wyh.bs.storage.KeyValueTable;

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

                if (validate()) {
                    String password = w_password.getText().toString();
                    sendRequest(w_phone.getText().toString(), Global.getMD5(password));
                }

                //测试环境，不验证，不请求
                //sendRequest0(w_phone.getText().toString(), w_password.getText().toString());
                /*
                Intent intent = new Intent(Login.this, LocationTest.class);
                startActivity(intent);
                */
            }
        });

        /* app初始设置 */
        this.appInit();
        /* 自动登录 */
        this.init();
    }

    private void appInit() {
        // 创建storage.db数据库
        DBHelper.instance(Login.this, 1);
        PermissionUtils.verifyLocationPermissions(Login.this);
        //PermissionUtils.verifyStoragePermissions(Login.this);
    }

    /* 接收reg的数据*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                this.w_phone.setText(data.getStringExtra("phone"));
                this.w_password.setText("");
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
        User user = KeyValueTable.getObject("user", User.class);
        //Log.i("mms_init", user + "");
        if (user != null) {
            this.sendRequest(user.getUserPhone(), user.getPassword());
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
            String info;
            JSONObject request = new JSONObject();
            request.put("phone", phone);
            request.put("password", password);
            JSONObject response = Global.httpPost("/user/login.do", request.toJSONString());
            if (response.getInteger("code") == 1) {
                JSONObject content= JSONObject.parseObject(response.getString("respStr"));
                int status = content.getInteger("status");
                if (status == 1) {
                    User user = content.getObject("user", User.class);
                    /*
                    SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                    editor.putString("phone", user.getUserPhone());
                    editor.putString("password", user.getPassword());
                    editor.apply();
                    */
                    KeyValueTable.addObject("user", user);
                    loadImg(user.getTouImgPath());
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();  //结束Login activity
                    return;
                } else {
                    info = content.getString("msg");
                }
            } else {
                info = response.getString("msg");
            }
             /* 非UI线程错误提示 */
            Looper.prepare( );
            Toast.makeText(Login.this, info, Toast.LENGTH_LONG).show();
            Looper.loop();
            }
        }).start();
    }

    //加载用户头像
    private void loadImg(final String uri) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream in = null;
                try {
                    in = Global.upDownFile(uri);
                    Bitmap bm = BitmapFactory.decodeStream(in);
                    String[] path = uri.split("/");
                    ImgProcess.saveBitmap(bm, path[path.length - 1]);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
