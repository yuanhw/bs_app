package cn.wyh.bs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.wyh.bs.R;

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
                Toast.makeText(Reg.this, "提交", Toast.LENGTH_LONG).show();
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
}
