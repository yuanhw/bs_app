package cn.wyh.bs.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.bean.CaiOneDto;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class CaiAdapter4 extends RecyclerView.Adapter<CaiAdapter4.ViewHolder> {
    private Activity context;
    private List<CaiOneDto> data;

    public CaiAdapter4(Activity context, List<CaiOneDto> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cai_4_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String caiId = holder.tv2.getText().toString().split("：")[1];
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setMessage("是否删除？");
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String resp = Global.httpPost3("/cai/web/updateStatus.do", "id=" + caiId + "&status=4");
                                JSONObject obj = JSONObject.parseObject(resp, JSONObject.class);
                                int i = obj.getInteger("data");
                                if (i == 1) {
                                    Iterator<CaiOneDto> iter = data.iterator();
                                    while (iter.hasNext()) {
                                        CaiOneDto tmp = iter.next();
                                        if (caiId.equals(tmp.getCaiId() + "")) {
                                            iter.remove();
                                            break;
                                        }
                                    }
                                    context.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                                            dialog.setMessage("已删除");
                                            dialog.show();
                                            CaiAdapter4.this.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }
                        }).start();
                    }
                });
                dialog.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CaiOneDto order = data.get(position);
        String url = Global.BASE_URL + order.getCaiImg();
        //异步加载图片列表
        Picasso.with(this.context).load(url).into(holder.caiImg);
        holder.tv1.setText(order.getName());
        holder.tv2.setText("采摘编号：" + order.getCaiId());
        holder.tv3.setText("种植编号：" + order.getPlantId());
        holder.tv4.setText("生成时间：" + Global.convertDate(order.getCreateTime(), Const.DATE_TIME_FORMAT));
        holder.tv6.setText("完成时间：" + Global.convertDate(order.getSendTime(), Const.DATE_TIME_FORMAT));
        holder.tv7.setText("负责人：" + order.getOperate());
        holder.tv8.setText(order.getPhone());
        holder.tv9.setText("收货地址：" + order.getAddress());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView caiImg;
        TextView tv1, tv2, tv3, tv4, tv6, tv7, tv8, tv9;
        Button bt;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.name);
            tv2 = (TextView) itemView.findViewById(R.id.cai_id);
            tv3 = (TextView) itemView.findViewById(R.id.plant_id);
            tv4 = (TextView) itemView.findViewById(R.id.time);
            caiImg = (ImageView) itemView.findViewById(R.id.img);

            tv6 = (TextView) itemView.findViewById(R.id.update_time);
            tv7 = (TextView) itemView.findViewById(R.id.operate);
            tv8 = (TextView) itemView.findViewById(R.id.phone);
            tv9 = (TextView) itemView.findViewById(R.id.address);

            bt = (Button) itemView.findViewById(R.id.del);
        }
    }
}
