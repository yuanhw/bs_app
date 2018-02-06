package cn.wyh.bs.activity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import cn.wyh.bs.R;
import cn.wyh.bs.common.LocationUtils;
import cn.wyh.bs.common.PermissionUtils;

public class LocationTest extends Activity {

    TextView textView;
    Button button;
    boolean tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_test);

        textView = (TextView) findViewById(R.id.test_show);
        button = (Button) findViewById(R.id.test_bt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location();
            }
        });
        PermissionUtils.verifyLocationPermissions(this);
        location();
    }


    private void location() {
        LocationUtils.getLocationClient(getApplicationContext(), new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                textView.setText("纬度：" + bdLocation.getLatitude() + " - 经度" + bdLocation.getLongitude() +
                        bdLocation.getCity());
                Log.i("mms_l", bdLocation.getAddrStr() + " _ " + bdLocation.getProvince() + " - " + bdLocation.getLocType());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 102) {
            boolean code = true;
            for (int tmp : grantResults) {
                if (tmp != PackageManager.PERMISSION_GRANTED) {
                    code = false;
                    break;
                }
            }
            if (code) {
                tag = true;
                //location();
            }
        }
    }
}
