package cn.wyh.bs.show;

/**
 * Created by WYH on 2017/12/24.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import cn.wyh.bs.R;

public class Gv {
    private GridView gridView;
    private Context context;
    private String[] from = { "image", "title" };
    private int[] to = { R.id.gv_img, R.id.gv_title };

    public Gv(Context context, GridView gridView) {
        this.gridView = gridView;
        this.context = context;
    }

    public void exec() {
        SimpleAdapter pictureAdapter = new SimpleAdapter(this.context, getList(),
                R.layout.gv, from, to);
        this.gridView.setAdapter(pictureAdapter);
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("mms_Gv_click", "position = " + position + ", id = " + id);
            }
        });
    }

    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;

        String[] titles = new String[] { "订单信息", "耕种记录", "租赁地块", "我的地块"};
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
