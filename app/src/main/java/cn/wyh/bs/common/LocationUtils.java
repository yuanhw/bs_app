package cn.wyh.bs.common;

import android.content.Context;
import android.location.Geocoder;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.inner.GeoPoint;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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
    /**
     *  定位
     * @param context
     * @param listener
     * @return
     */
    public static LocationClient getLocationClient(Context context, BDAbstractLocationListener listener) {
        mLocationClient = new LocationClient(context);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(listener);mLocationClient.start();
        return mLocationClient;
    }
    /**
     * 通过城市获取坐标
     * @param context
     * @param str
     * @return
     */
    public static GeoPoint getGeoPointBystr(Context context, String str) {
        GeoPoint gpGeoPoint = null;
        if (str!=null) {
            Geocoder gc = new Geocoder(context, Locale.CHINA);
            List<android.location.Address> addressList;
            try {
                addressList = gc.getFromLocationName(str, 1);
                if (!addressList.isEmpty()) {
                    android.location.Address address_temp = addressList.get(0);
                    //计算经纬度
                    double Latitude=address_temp.getLatitude();
                    double Longitude=address_temp.getLongitude();
                    //生产GeoPoint
                    gpGeoPoint = new GeoPoint(Latitude, Longitude);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gpGeoPoint;
    }
}
