package cn.wyh.bs.activity.fragment.show;

/**
 * Created by WYH on 2017/12/24.
 *  首页表格设置
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.home.lease.SearchFarmActivity;
import cn.wyh.bs.activity.order.OrderInfo;
import cn.wyh.bs.activity.plant.BlockDetailActivity;
import cn.wyh.bs.activity.plant.OperateStatus;
import cn.wyh.bs.activity.plant.PlantActivity;

public class TabHomeTable {
    private GridView gridView; //表格控件
    private Context context; //上下文
    private String[] from = { "image", "title" };
    private int[] to = { R.id.gv_img, R.id.gv_title };

    public TabHomeTable(Context context, GridView gridView) {
        this.gridView = gridView;
        this.context = context;
    }

    public void exec() {
        /* 适配器 */
        SimpleAdapter pictureAdapter = new SimpleAdapter(this.context, getList(), R.layout.gv, from, to);
        this.gridView.setAdapter(pictureAdapter);
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(context, "编号：" + id, Toast.LENGTH_LONG).show();
                switch ((int)id) {
                    case 0:
                        Intent intent0 = new Intent(context, OrderInfo.class);
                        context.startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(context, OperateStatus.class);
                        context.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(context, SearchFarmActivity.class);
                        context.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(context, BlockDetailActivity.class);
                        context.startActivity(intent3);
                        break;
                }
            }
        });
    }

    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map;

        String[] titles = new String[] { "订单信息", "指令信息", "租赁地块", "我的地块"};
        Integer[] images = { R.drawable.order, R.drawable.plant, R.drawable.farm, R.drawable.dk};

        for (int i = 0; i < images.length; i++) {
            map = new HashMap<>();
            map.put("image", images[i]);
            map.put("title", titles[i]);
            list.add(map);
        }
        return list;
    }
}
