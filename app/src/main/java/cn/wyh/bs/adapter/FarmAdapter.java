package cn.wyh.bs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.wyh.bs.activity.home.FarmDetailedActivity;
import cn.wyh.bs.bean.LateLySimplyFarm;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.R;
/**
 * Created by WYH on 2017/12/24.
 */

public class FarmAdapter extends RecyclerView.Adapter<FarmAdapter.ViewHolder> {
    private Context context;
    private List<LateLySimplyFarm> farms;

    public FarmAdapter(Context context, List<LateLySimplyFarm> farms) {
        this.context = context;
        this.farms = farms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ((TextView) v.findViewById(R.id.item_farm_id)).getText().toString();
                //Log.i("mms_FarmAdapter_click", "id = " + id);
                Intent intent = new Intent(context, FarmDetailedActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
                //Toast.makeText(context, "id = " + id, Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LateLySimplyFarm farm = farms.get(position);
        String url = Global.BASE_URL + farm.getFmImg();

        //异步加载图片列表
        Picasso.with(this.context).load(url).into(holder.farmImg);

        holder.tv0.setText(String.valueOf(farm.getId()));
        holder.tv1.setText(farm.getFmTitle());
        holder.tv2.setText(farm.getSpec());
        holder.tv3.setText(String.valueOf(farm.getConsumerNum()));
        holder.tv4.setText(farm.getDistance());
    }

    @Override
    public int getItemCount() {
        return farms.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView farmImg;
        TextView tv0, tv1, tv2, tv3, tv4;
        public ViewHolder(View itemView) {
            super(itemView);
            farmImg = (ImageView) itemView.findViewById(R.id.item_img_farm);
            tv0 = (TextView) itemView.findViewById(R.id.item_farm_id);
            tv1 = (TextView) itemView.findViewById(R.id.item_farm_name);
            tv2 = (TextView) itemView.findViewById(R.id.item_spec);
            tv3 = (TextView) itemView.findViewById(R.id.item_consumer_num);
            tv4 = (TextView) itemView.findViewById(R.id.item_distance);
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<LateLySimplyFarm> getFarms() {
        return farms;
    }

    public void setFarms(List<LateLySimplyFarm> farms) {
        this.farms = farms;
    }
}
