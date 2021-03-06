package cn.wyh.bs.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.SimpleFormatter;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.home.lease.OrderCreate;
import cn.wyh.bs.activity.home.lease.RuleFarmActivity;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.TableParam;
import cn.wyh.bs.entity.BlockRuleShowList;

/**
 * Created by WYH on 2018/3/29.
 */

public class BlockAdapter extends RecyclerView.Adapter<BlockAdapter.ViewHolder> {
    private List<BlockRuleShowList> data;
    private Context context;
    private RuleFarmActivity activity;

    public BlockAdapter(List<BlockRuleShowList> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rule_item, parent, false);
        BlockAdapter.ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView batch = (TextView) v.findViewById(R.id.rule_batch);
                //Log.i("mms_rl", batch.getText().toString());
                final String batchNo = batch.getText().toString();
                activity.showD(batchNo);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BlockRuleShowList item = data.get(position);
        holder.tv1.setText(item.getBatchNo());
        holder.tv2.setText(item.getId() +"");
        holder.tv3.setText(item.getSpec() + "");
        holder.tv4.setText(item.getMaxLease() + item.getLeaseUnit());
        holder.tv5.setText(item.getLeaseUnit());
        holder.tv6.setText(item.getNumber() + "个");
        holder.tv7.setText(item.getHasLease() + "");
        holder.tv8.setText(item.getUnitPrice() + "元");
        int type = item.getType();
        String type_s = "仅自种";
        if (type == 1) {
            type_s = "可代种";
        }
        holder.tv9.setText(type_s);
        SimpleDateFormat format = new SimpleDateFormat(Const.DATE_FORMAT);
        holder.tv10.setText(format.format(item.getValidityEnd()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.rule_batch);
            tv2 = (TextView) itemView.findViewById(R.id.rule_id);
            tv3 = (TextView) itemView.findViewById(R.id.rule_spec);
            tv4 = (TextView) itemView.findViewById(R.id.max_lease);
            tv5 = (TextView) itemView.findViewById(R.id.lease_unit);
            tv6 = (TextView) itemView.findViewById(R.id.rule_num);
            tv7 = (TextView) itemView.findViewById(R.id.has_lease);
            tv8 = (TextView) itemView.findViewById(R.id.unit_price);
            tv9 = (TextView) itemView.findViewById(R.id.type);
            tv10 = (TextView) itemView.findViewById(R.id.time_end);
        }
    }

    public List<BlockRuleShowList> getData() {
        return data;
    }

    public void setData(List<BlockRuleShowList> data) {
        this.data = data;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public RuleFarmActivity getActivity() {
        return activity;
    }

    public void setActivity(RuleFarmActivity activity) {
        this.activity = activity;
    }
}
