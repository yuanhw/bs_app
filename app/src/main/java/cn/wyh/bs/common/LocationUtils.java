package cn.wyh.bs.common;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by WYH on 2018/1/22.
 */

public class LocationUtils {

    private static LocationClient mLocationClient;
    private static LocationClientOption option = new LocationClientOption();
    static {
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        /**
         * 设置返回经纬度坐标类型，默认gcj02
         */
        option.setCoorType("bd09ll");
        option.setScanSpan(0);
        option.setOpenGps(true);
    }

    private LocationUtils() {
    }

    public static LocationClient getLocationClient(Context context, BDAbstractLocationListener listener) {
        mLocationClient = new LocationClient(context);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(listener);mLocationClient.start();
        return mLocationClient;
    }
}
