package cn.wyh.bs.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import cn.wyh.bs.R;

/**
 * Created by WYH on 2018/1/24.
 */

public class CityAdapter extends ArrayAdapter<String> {

    private int resourceId;

    public CityAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String city = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView cityName = (TextView) view.findViewById(R.id.list_city_name);
        cityName.setText(city);
        return view;
    }
}
