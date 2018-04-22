package cn.wyh.bs.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Iterator;
import java.util.List;

import cn.wyh.bs.R;
import cn.wyh.bs.bean.Info;
import cn.wyh.bs.common.Const;
import cn.wyh.bs.common.Global;

/**
 * Created by WYH on 2017/12/24.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private Activity context;
    private List<Info> data;

    public InfoAdapter(Activity context, List<Info> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = holder.tv4.getText().toString();
                String hasRead = holder.tv5.getText().toString();
                String text = holder.tv3.getText().toString();
                if (hasRead.equals("1")) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setMessage(text);
                    dialog.show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setMessage(text);
                    dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Global.httpPost3("/info/app/setHasRead.do", "id=" + id);
                                    Iterator<Info> iter = data.iterator();
                                    while (iter.hasNext()) {
                                        Info tmp = iter.next();
                                        if (id.equals(tmp.getId() + "")) {
                                            tmp.setHasRead(1);
                                            break;
                                        }
                                    }
                                    context.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            InfoAdapter.this.notifyDataSetChanged();
                                            holder.show.setVisibility(View.INVISIBLE);
                                        }
                                    });
                                }
                            }).start();
                    }
                });
                dialog.show();
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setMessage("删除？");
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final String id = holder.tv4.getText().toString();
                                Global.httpPost3("/info/app/delInfo.do", "id=" + id);
                                Iterator<Info> iter = data.iterator();
                                while (iter.hasNext()) {
                                    Info tmp = iter.next();
                                    if (id.equals(tmp.getId() + "")) {
                                        iter.remove();
                                        break;
                                    }
                                }
                                context.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        InfoAdapter.this.notifyDataSetChanged();
                                    }
                                });
                            }
                        }).start();
                    }
                });
                dialog.show();
                return false;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Info info = data.get(position);
        holder.tv1.setText(info.getTitle());
        holder.tv2.setText(Global.convertDate(info.getCreateTime(), Const.DATE_TIME_FORMAT_2));
        holder.tv3.setText(info.getContext());
        holder.tv4.setText(info.getId() + "");
        holder.tv5.setText(info.getHasRead() + "");
        if (info.getHasRead() == 0) {
            holder.show.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView show;
        TextView tv1, tv2, tv3, tv4, tv5;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.title);
            tv2 = (TextView) itemView.findViewById(R.id.time);
            tv3 = (TextView) itemView.findViewById(R.id.context);

            tv4 = (TextView) itemView.findViewById(R.id.id);
            tv5 = (TextView) itemView.findViewById(R.id.has_red);
            show = (ImageView) itemView.findViewById(R.id.show);
        }
    }
}
