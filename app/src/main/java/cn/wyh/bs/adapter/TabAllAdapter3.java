package cn.wyh.bs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.home.lease.OrderPayStatus;
import cn.wyh.bs.bean.TabAllOrder;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class TabAllAdapter3 extends RecyclerView.Adapter<TabAllAdapter3.ViewHolder> {
    private Context context;
    private List<TabAllOrder> data;

    public TabAllAdapter3(Context context, List<TabAllOrder> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.o_rv3_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.tv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TabAllOrder order = data.get(position);
        String url = Global.BASE_URL + order.getFarmImg();

        //异步加载图片列表
        Picasso.with(this.context).load(url).into(holder.farmImg);

        holder.tv1.setText(order.getFarmName());
        holder.tv2.setText(order.getStatus());
        holder.tv3.setText(order.getSpec());
        holder.tv4.setText(order.getPrice());

        holder.tv5.setText(order.getNum());
        holder.tv6.setText(order.getTime());
        holder.tv7.setText(order.getCreateTime());

        holder.tv8.setText("订单号：" + order.getOrderId());

        holder.tv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String orderId = holder.tv8.getText().toString().split("：")[1];
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String resp = Global.httpPost3("/block/order/heXiao.do", "orderId=" + orderId);
                        JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                        int data = obj.getInteger("data");
                        String str = "确认失败";
                        if (data == 1) {
                            str = "确认成功";
                        }
                        Intent intent = new Intent(context, OrderPayStatus.class);
                        intent.putExtra("str", str);
                        context.startActivity(intent);
                    }
                }).start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView farmImg;
        View detail;
        TextView tv1, tv2, tv3, tv4,
            tv5, tv6, tv7, tv8,  tv10;
        public ViewHolder(View itemView) {
            super(itemView);
            farmImg = (ImageView) itemView.findViewById(R.id.o_img);
            tv1 = (TextView) itemView.findViewById(R.id.o_all_farm_name);
            detail = itemView.findViewById(R.id.o_detail);
            tv2 = (TextView) itemView.findViewById(R.id.o_all_status);
            tv3 = (TextView) itemView.findViewById(R.id.o_all_spec);
            tv4 = (TextView) itemView.findViewById(R.id.o_all_price);

            tv5 = (TextView) itemView.findViewById(R.id.o_all_num);
            tv6 = (TextView) itemView.findViewById(R.id.o_all_time);
            tv7 = (TextView) itemView.findViewById(R.id.o_all_create_t);

            tv8 = (TextView) itemView.findViewById(R.id.o_id);
            tv10 = (TextView) itemView.findViewById(R.id.o_all_hx);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<TabAllOrder> getData() {
        return data;
    }

    public void setData(List<TabAllOrder> data) {
        this.data = data;
    }
}
