package cn.wyh.bs.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.home.lease.OrderPayStatus;
import cn.wyh.bs.bean.RefundListDto;
import cn.wyh.bs.bean.TabAllOrder;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class TabAllAdapter5 extends RecyclerView.Adapter<TabAllAdapter5.ViewHolder> {
    private Activity context;
    private List<RefundListDto> data;

    public TabAllAdapter5(Activity context, List<RefundListDto> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.o_rv5_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String orderId = holder.tv8.getText().toString().split("：")[1];
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String resp = Global.httpPost3("/block/order/delRefund.do", "orderId=" + orderId);
                        JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                        final int data = obj.getInteger("data");
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String str = "删除失败";
                                if (data == 1) {
                                    if (data == 1) {
                                        str = "删除成功";
                                        List<RefundListDto> lists = TabAllAdapter5.this.data;
                                        Iterator<RefundListDto> iter = lists.iterator();
                                        while (iter.hasNext()) {
                                            RefundListDto refund = iter.next();
                                            if (refund.getOrderId().equals(orderId)) {
                                                iter.remove();
                                            }
                                        }
                                        TabAllAdapter5.this.notifyDataSetChanged();
                                    }
                                }
                                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RefundListDto order = data.get(position);
        String url = Global.BASE_URL + order.getFarmImg();

        //异步加载图片列表
        Picasso.with(this.context).load(url).into(holder.farmImg);

        holder.tv1.setText(order.getFarmName());
        String status = "";
        switch (order.getStatus()) {
            case 0: status = "退款中";
                holder.tv9.setVisibility(View.GONE);
                break;
            case 1: status = "已取消"; break;
            case 2: status = "退款成功"; break;
            case 3: status = "商家拒绝"; break;
        }

        holder.tv2.setText(status);
        holder.tv3.setText("订单金额：￥" + order.getOrderAmt());
        holder.tv4.setText("退款金额：￥" + order.getRefundAmt());

        holder.tv7.setText("申请时间：" + Global.convertDate(order.getCreateTime(), Const.DATE_TIME_FORMAT));

        holder.tv8.setText("订单号：" + order.getOrderId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView farmImg;
        TextView tv1, tv2, tv3, tv4, tv7, tv8, tv9;
        public ViewHolder(View itemView) {
            super(itemView);
            farmImg = (ImageView) itemView.findViewById(R.id.o_img);
            tv1 = (TextView) itemView.findViewById(R.id.o_all_farm_name);

            tv2 = (TextView) itemView.findViewById(R.id.o_all_status);
            tv3 = (TextView) itemView.findViewById(R.id.o_amt);
            tv4 = (TextView) itemView.findViewById(R.id.o_je);

            tv7 = (TextView) itemView.findViewById(R.id.o_all_create_t);
            tv8 = (TextView) itemView.findViewById(R.id.o_id);
            tv9 = (TextView) itemView.findViewById(R.id.o_all_del);

        }
    }
}
