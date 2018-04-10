package cn.wyh.bs.activity.home.lease;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.ActivityManager;
import cn.wyh.bs.bean.ShareOrderDto;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
import cn.wyh.bs.custom.AmountView;
import cn.wyh.bs.custom.payui.PayFragment;
import cn.wyh.bs.custom.payui.PayPwdView;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.KeyValueTable;

/**
 * Created by WYH on 2018/4/5.
 */

public class OrderCreate extends FragmentActivity implements PayPwdView.InputCallBack{

    private ShareOrderDto obj;
    private PayFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_create);
        ActivityManager.addActivity(this);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        obj = JSONObject.parseObject(data, ShareOrderDto.class);

        ImageView back = (ImageView) findViewById(R.id.back_o1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView blockId = (TextView) findViewById(R.id.o_block_id);
        TextView title = (TextView) findViewById(R.id.o_title);
        TextView address = (TextView) findViewById(R.id.o_address);
        TextView blockType = (TextView) findViewById(R.id.o_block_type);
        TextView leaseUnit = (TextView) findViewById(R.id.o_lease_unit);
        TextView spec = (TextView) findViewById(R.id.o_spec);
        final AmountView num = (AmountView) findViewById(R.id.o_num);
        final TextView unitPrice = (TextView) findViewById(R.id.o_unit_price);
        AmountView time = (AmountView) findViewById(R.id.o_time);
        final TextView amt = (TextView) findViewById(R.id.o_amount);
        final TextView amt2 = (TextView) findViewById(R.id.o_amount_2);
        TextView submit = (TextView) findViewById(R.id.o_submit);

        blockId.setText(obj.getOrderId());
        title.setText(obj.getFarmName());
        address.setText(obj.getFarmAddress());
        blockType.setText(obj.getBlockTypeValue());
        leaseUnit.setText(obj.getUnitLease());
        spec.setText(obj.getSpec());
        unitPrice.setText(obj.getUnitPrice() + "元");
        num.setGoods_storage(obj.getMaxNum());
        num.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                String price_0 = unitPrice.getText().toString();
                String price_1 = price_0.substring(0, price_0.length() - 1);
                double amts = Double.parseDouble(price_1) * amount * num.getAmount();
                obj.setAmount(amts);
                obj.setNum(amount);
                amt.setText(amts + "元");
                amt2.setText(amts + "元");
            }
        });
        time.setGoods_storage(obj.getMaxLease());
        time.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                //Log.i("mms_amt", amount + "");
                String price_0 = unitPrice.getText().toString();
                String price_1 = price_0.substring(0, price_0.length() - 1);
                double amts = Double.parseDouble(price_1) * amount * num.getAmount();
                obj.setAmount(amts);
                obj.setLeaseTime(amount);
                amt.setText(amts + "元");
                amt2.setText(amts + "元");
            }
        });
        amt.setText(obj.getAmount() + "元");
        amt2.setText(obj.getAmount() + "元");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(PayFragment.EXTRA_CONTENT,  "余额支付：¥ " + obj.getAmount());

                fragment = new PayFragment();
                fragment.setArguments(bundle);
                fragment.setPaySuccessCallBack(OrderCreate.this);
                fragment.show(OrderCreate.this.getSupportFragmentManager(), "Pay");
            }
        });
    }

    @Override
    public void onInputFinish(String result) {
        //Log.i("mms_r", result);
        final User user = KeyValueTable.getObject("user", User.class);
        if (Global.getMD5(result).equals(user.getPayPassword())) {
            //this.fragment.dismiss();
            if (user.getAccount() - obj.getAmount() < 0) {
                this.fragment.showError(true, "余额不足");
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject request = new JSONObject();
                        request.put("user", user);
                        request.put("shareOrder", obj);
                        TableParam param = new TableParam();
                        param.add("jsonStr", request.toJSONString());
                        String respStr = Global.httpPost3("/block/order/submitBlockOrder.do", param.toString());
                        JSONObject resp = JSONObject.parseObject(respStr, JSONObject.class);
                        //Log.i("mms_res", resp.toJSONString());
                        int code = resp.getInteger("status");
                        Intent intent = new Intent(OrderCreate.this, OrderPayStatus.class);
                        String str = "支付成功，地块订单已生效";
                        if (code == 0) {
                            str = "订单提交失败";
                        } else {
                            param.clear();
                            param.add("phone", user.getUserPhone());
                            String respStr2 = Global.httpPost3("/user/findByPhone.do", param.toString());
                            User user = JSONObject.parseObject(respStr2, User.class);
                            KeyValueTable.updateObject("user", user);
                        }
                        fragment.dismiss();
                        intent.putExtra("str", str);
                        startActivity(intent);
                        finish();
                    }
                }).start();
            }
        } else {
            this.fragment.showError(true, "密码错误");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }
}
