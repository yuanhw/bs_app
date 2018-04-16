package cn.wyh.bs.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.bean.SelfBDetail;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class TabSelfAdapter extends RecyclerView.Adapter<TabSelfAdapter.ViewHolder> {
    private Activity context;
    private List<SelfBDetail> data;

    public TabSelfAdapter(Activity context, List<SelfBDetail> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.self_plant_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SelfBDetail detail = data.get(position);
        holder.tv1.setText(detail.getId() + "");
        holder.tv2.setText(detail.getStatus());
        holder.tv3.setText(detail.getOrderId());
        holder.tv4.setText(detail.getSpec());
        holder.tv5.setText(Global.convertDate(detail.getNoValid(), Const.DATE_FORMAT));
        holder.tv6.setText("地址：" + detail.getAddress());
        if (!detail.getStatus().equals("正常")) {
            holder.del.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3, tv4, tv5, tv6;
        Button del;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.id);
            tv2 = (TextView) itemView.findViewById(R.id.status);
            tv3 = (TextView) itemView.findViewById(R.id.o_id);
            tv4 = (TextView) itemView.findViewById(R.id.spec);
            tv5 = (TextView) itemView.findViewById(R.id.time);
            tv6 = (TextView) itemView.findViewById(R.id.address);

            del = (Button) itemView.findViewById(R.id.del);
        }
    }
}
