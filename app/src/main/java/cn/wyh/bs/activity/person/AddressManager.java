package cn.wyh.bs.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.adapter.AddressAdapter;
import cn.wyh.bs.bean.Address;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

/**
 * Created by WYH on 2018/4/20.
 */

public class AddressManager extends BaseActivity {
    private List<Address> data = new ArrayList<>();
    private AddressAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_manager);
        ImageView back = (ImageView) findViewById(R.id.back);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        adapter = new AddressAdapter(this, data);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        Button add = (Button) findViewById(R.id.add);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressManager.this, AddAddress.class);
                startActivityForResult(intent, 200);
            }
        });

        loadData();
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int id = KeyValueTable.getObject("user", User.class).getId();
                String resp = Global.httpPost3("/user/app/loadAddressList.do", "userId=" + id);
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                List<Address> list = JSONArray.parseArray(obj.getString("data"), Address.class);
                data.clear();
                data.addAll(list);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                loadData();
            }
        }
    }

    public void del(final String id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resp = Global.httpPost3("/user/app/delAddress.do", "id=" + id);
                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                final int tag = obj.getInteger("data");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (tag == 1) {
                                Toast.makeText(AddressManager.this, "已删除", Toast.LENGTH_SHORT).show();
                                Iterator<Address> iter = data.iterator();
                                while (iter.hasNext()) {
                                    Address item = iter.next();
                                    if (id.equals(item.getId() + "")) {
                                        iter.remove();
                                        break;
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(AddressManager.this);
                                dialog.setMessage("删除失败");
                                dialog.show();
                            }
                        }
                    });
            }
        }).start();
    }
}
