package cn.wyh.bs.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.activity.home.lease.OrderPayStatus;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
/**
 * Created by WYH on 2018/4/9.
 */

public class RefundActivity extends BaseActivity {

    private TableParam param = new TableParam();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refund_activity);
        String orderId = getIntent().getStringExtra("orderId");
        final String price =getIntent().getStringExtra("price");
        final double pricess = Double.parseDouble(price.substring(1, price.length()));

        param.add("orderId", orderId);

        ImageView back = (ImageView) findViewById(R.id.o_info_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView orderIds = (TextView) findViewById(R.id.orderId);
        TextView prices = (TextView) findViewById(R.id.price);
        Spinner spinner = (Spinner) findViewById(R.id.tk_ys);
        TextView zd = (TextView) findViewById(R.id.zd);
        final EditText je = (EditText) findViewById(R.id.tk_je);
        final EditText sm = (EditText) findViewById(R.id.tk_sm);
        Button sumbit = (Button) findViewById(R.id.submit);

        orderIds.setText("订单编号：" + orderId);
        prices.setText("订单金额：" + price);
        zd.setText("退款金额：（最多" + price + "）");

        final List<String> list = new ArrayList<String>();
        list.add("不想买了");
        list.add("买错了");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                param.add("refundYs", list.get(position));
                //refund.setRefundYs(list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String je_0 = je.getText().toString();
                param.add("refundSm", sm.getText().toString());
                if (je_0.equals("")) {
                    Toast.makeText(RefundActivity.this, "退款金额不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    double je_1 = Double.parseDouble(je_0);
                    if (je_1 - pricess > 0) {
                        Toast.makeText(RefundActivity.this, "退款金额超过已付款", Toast.LENGTH_SHORT).show();
                    } else {
                        param.add("refundAmt", je_0);
                        param.add("orderAmt", pricess + "");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String resp = Global.httpPost3("/block/order/applyRefund.do", param.toString());
                                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                                int data = obj.getInteger("data");
                                String str = "发送申请失败";
                                if (data == 1) {
                                    str = "已发送申请，请等待商家处理";
                                }
                                Intent intent = new Intent(RefundActivity.this, OrderPayStatus.class);
                                intent.putExtra("str", str);
                                startActivity(intent);
                            }
                        }).start();
                    }
                }
            }
        });
    }
}
