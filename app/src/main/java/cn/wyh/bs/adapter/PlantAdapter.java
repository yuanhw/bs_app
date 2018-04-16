package cn.wyh.bs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.plant.SelectPlantActivity;
import cn.wyh.bs.bean.Green;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {
    private SelectPlantActivity context;
    private List<Green> data;

    public PlantAdapter(SelectPlantActivity context, List<Green> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_plant_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = holder.tv3.getText().toString();
                context.updateSelected(id);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Green plant = data.get(position);
        holder.tv1.setText(plant.getTitle());
        holder.tv2.setText("生命周期约" + plant.getLife() + "天");
        holder.tv3.setText(plant.getId() + "");
        String url = Global.BASE_URL + plant.getImg();
        Picasso.with(context).load(url).into(holder.greenImg);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView greenImg;
        TextView tv1, tv2, tv3;
        public ViewHolder(View itemView) {
            super(itemView);
            greenImg = (ImageView) itemView.findViewById(R.id.img_p);
            tv1 = (TextView) itemView.findViewById(R.id.cai_name);
            tv2 = (TextView) itemView.findViewById(R.id.cai_life);
            tv3 = (TextView) itemView.findViewById(R.id.cai_id);
        }
    }
}
