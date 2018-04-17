package cn.wyh.bs.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.bean.BlockPlantDto;
import cn.wyh.bs.bean.TillageDto;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private Activity context;
    List<TillageDto> data;

    public RecordAdapter(Activity context, List<TillageDto> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TillageDto dto = data.get(position);
        holder.tv1.setText(Global.convertDate(dto.getCreateTime(), Const.DATE_TIME_FORMAT));
        holder.tv2.setText(dto.getStatus());
        holder.tv3.setText(dto.getOperate());
        holder.tv4.setText(dto.getVideo());

        List<String> list = dto.getImgList();
        int i = 0;
        for (String src : list) {
            Picasso.with(context).load(Global.BASE_URL + src).into(holder.imgs[i++]);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView[] imgs = new ImageView[3];
        TextView tv1, tv2, tv3, tv4;
        Button bt1;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.time);
            tv2 = (TextView) itemView.findViewById(R.id.status);
            tv3 = (TextView) itemView.findViewById(R.id.last_operate);
            tv4 = (TextView) itemView.findViewById(R.id.video_src);

            imgs[0] = (ImageView) itemView.findViewById(R.id.img1);
            imgs[1] = (ImageView) itemView.findViewById(R.id.img2);
            imgs[2] = (ImageView) itemView.findViewById(R.id.img3);

            bt1 = (Button) itemView.findViewById(R.id.video);
        }
    }
}
