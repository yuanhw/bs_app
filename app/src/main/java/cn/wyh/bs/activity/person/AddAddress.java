package cn.wyh.bs.activity.person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.bean.Address;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

/**
 * Created by WYH on 2018/4/20.
 */

public class AddAddress extends BaseActivity {
    private Address address = new Address();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_edit);
        int userId = KeyValueTable.getObject("user", User.class).getId();
        address.setUserId(userId);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final TextView tv1 = (TextView) findViewById(R.id.name);
        final RadioButton man = (RadioButton) findViewById(R.id.man);
        final RadioButton un_man= (RadioButton) findViewById(R.id.un_man);
        final TextView tv4 = (TextView) findViewById(R.id.phone);
        final TextView tv5 = (TextView) findViewById(R.id.address);
        final CheckBox box = (CheckBox) findViewById(R.id.is_default);
        final Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address.setName(tv1.getText().toString());
                if (man.isChecked()) {
                    address.setCall("先生");
                } else if (un_man.isChecked()) {
                    address.setCall("女士");
                }
                address.setPhone(tv4.getText().toString());
                address.setAddress(tv5.getText().toString());
                address.setSing(0);
                if (box.isChecked()) {
                    address.setSing(1);
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TableParam param = new TableParam();
                        try {
                            String paramStr = param.toString(address);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        String resp = Global.httpPost3("/user/app/addAddress.do", param.toString());
                        JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                        int data = obj.getInteger("data");
                        if (data == 1) {
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(AddAddress.this);
                            dialog.setMessage("增加失败");
                            dialog.show();
                        }
                    }
                }).start();
            }
        });
    }
}
