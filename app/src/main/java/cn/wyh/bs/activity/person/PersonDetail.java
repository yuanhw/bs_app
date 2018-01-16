package cn.wyh.bs.activity.person;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.custom.CircleImageView;

/**
 * Created by WYH on 2018/1/16.
 */

public class PersonDetail extends BaseActivity {

    private static int CAMERA_REQUEST_CODE = 0;
    private static int GALLERY_REQUEST_CODE = 1;

    private ImageView w_back;
    private View w_item1, w_item2;
    private CircleImageView w_tou_img;
    private TextView w_name, w_phone;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail);

        /* 获取控件 */
        this.w_back = (ImageView) findViewById(R.id.detail_back);
        this.w_item1 = findViewById(R.id.detail_item1);
        this.w_item2 = findViewById(R.id.detail_item2);
        this.w_tou_img = (CircleImageView) findViewById(R.id.detail_tou_img);
        this.w_name = (TextView) findViewById(R.id.detail_name);
        this.w_phone = (TextView) findViewById(R.id.detail_phone);

        initAlertDialog();

        /* 设置控件事件 */
        this.w_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.w_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });
    }

    private void initAlertDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择");
        //设置图标
        builder.setIcon(R.mipmap.upload_icon);
        builder.setItems(new String[] {"拍照", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0 :
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAMERA_REQUEST_CODE);
                        break;
                    case 1 :
                        Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
                        intent2.setType("image/*");
                        startActivityForResult(intent2, GALLERY_REQUEST_CODE);
                        break;
                }
            }
        });
        builder.setCancelable(true);
        builder.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (data == null) {
                return;
            }
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap bm = extras.getParcelable("data");
                this.w_tou_img.setImageBitmap(bm);
            }
        } else if (requestCode == GALLERY_REQUEST_CODE) {
            if (data == null) {
                return;
            }
            Uri uri = this.convertUri(data.getData());
            this.w_tou_img.setImageURI(uri);
        }
    }

    private Uri saveBitmap(Bitmap bitmap) {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/cn.wyh.bs");
        Log.i("mms_rui0", tmpDir.exists() + "");
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        Log.i("mms_rui1", tmpDir.exists() + "");
        File img = new File(tmpDir.getAbsolutePath() + "/tou.png");
        Log.i("mms_1", img.getAbsolutePath());
        try {
            FileOutputStream output = new FileOutputStream(img);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, output);
            output.flush();
            output.close();
            return Uri.fromFile(img);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Uri convertUri(Uri uri) {
        InputStream in;
        try {
            in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            in.close();
            return this.saveBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
