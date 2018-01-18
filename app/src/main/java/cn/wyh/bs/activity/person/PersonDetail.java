package cn.wyh.bs.activity.person;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.io.File;

import cn.wyh.bs.R;
import cn.wyh.bs.activity.BaseActivity;
import cn.wyh.bs.common.Global;
import cn.wyh.bs.common.ImgProcess;
import cn.wyh.bs.common.PermissionUtils;
import cn.wyh.bs.common.Status;
import cn.wyh.bs.custom.CircleImageView;
import cn.wyh.bs.entity.User;
import cn.wyh.bs.storage.DBHelper;
import cn.wyh.bs.storage.KeyValueTable;

/**
 * Created by WYH on 2018/1/16.
 */

public class PersonDetail extends BaseActivity {

    private static final int CAMERA_REQUEST_CODE = 0;
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final int CROP_REQUEST_CODE = 2;

    private static String tmpImgName = "tou_tmp.jpg"; //临时头像文件名称
    private static String realImgName = "tou.jpg"; //真实头像文件名称

    private ImageView w_back;
    private View w_item1, w_item2, w_item3, w_item4, w_item5, w_item6;
    private CircleImageView w_tou_img;
    private TextView w_name, w_password, w_pay_password, w_gender, w_phone;

    private AlertDialog.Builder builder;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail);

        /* 获取控件 */
        this.w_back = (ImageView) findViewById(R.id.detail_back);
        this.w_item1 = findViewById(R.id.detail_item1);
        this.w_item2 = findViewById(R.id.detail_item2);
        this.w_item3 = findViewById(R.id.detail_item3);
        this.w_item4 = findViewById(R.id.detail_item4);
        this.w_item5 = findViewById(R.id.detail_item5);
        this.w_item6 = findViewById(R.id.detail_item6);
        this.w_tou_img = (CircleImageView) findViewById(R.id.detail_tou_img);
        this.w_name = (TextView) findViewById(R.id.detail_name);
        this.w_password = (TextView) findViewById(R.id.detail_password);
        this.w_pay_password = (TextView) findViewById(R.id.detail_pay_password);
        this.w_gender = (TextView) findViewById(R.id.detail_gender);
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

        this.w_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonDetail.this, "修改昵称" + w_name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        this.w_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonDetail.this, "修改登录密码" + w_password.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        this.w_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonDetail.this, "修改支付密码" + w_pay_password.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        this.w_item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonDetail.this, "修改性别" + w_gender.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        this.w_item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PersonDetail.this, "修改电话号码" + w_phone.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        initData(); //组件赋值
        //Log.i("mms_sss", KeyValueTable.getObject("user", User.class).toString());
    }

    private void initData() {
        this.user = KeyValueTable.getObject("user", User.class);
        this.w_name.setText(user.getUserName());
        this.w_password.setText(user.getPassword());
        this.w_pay_password.setText(user.getPayPassword());
        this.w_gender.setText(user.getGender());
        this.w_phone.setText(user.getUserPhone());

        String[] imgName = user.getTouImgPath().split("/");
        Uri uri = ImgProcess.getImgPath(imgName[imgName.length - 1]);
        this.w_tou_img.setImageURI(uri);
    }

    /* 弹框 */
    private void initAlertDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择");
        builder.setIcon(R.mipmap.upload_icon);
        builder.setItems(new String[] {"拍照", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //询问用户获取权限
                PermissionUtils.verifyStoragePermissions(PersonDetail.this);
                switch (i) {
                    case 0 :
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, ImgProcess.getUriByFileProvider(PersonDetail.this, user.getUserPhone()+'-' + tmpImgName, true));
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
        switch (requestCode) {
            case CAMERA_REQUEST_CODE :
                if (resultCode == 0) {
                    return;
                }
                startImageZoom( ImgProcess.getUriByFileProvider(this, this.user.getUserPhone()+'-' + tmpImgName, true));
                break;
            case GALLERY_REQUEST_CODE :
                if (data == null) {
                    return;
                }
                ImgProcess.convertUri(PersonDetail.this, data.getData(), this.user.getUserPhone()+'-' + tmpImgName); //保存文件到本地
                startImageZoom( ImgProcess.getUriByFileProvider(this, this.user.getUserPhone()+'-' + tmpImgName, true));
                break;
            case CROP_REQUEST_CODE :
                if (data == null) {
                    return;
                }
                Bundle extras2 = data.getExtras();
                if (extras2 != null) {
                    Bitmap bm = extras2.getParcelable("data");
                    Uri uri = ImgProcess.saveBitmap(bm, this.user.getUserPhone()+'-' + realImgName);
                    //Log.i("PersonDetail_uri", uri.toString());
                    uploadImg(uri);
                    this.w_tou_img.setImageBitmap(bm);
                }
                break;
        }
    }

    /* 上传图片 */
    private void uploadImg(final Uri uri) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject response = Global.uploadImg("/user/uploadImg.do", new File(uri.getPath()),
                        w_phone.getText().toString());
                //Log.i("mms__", response.toJSONString());
                int code = response.getInteger("code");
                String msg = response.getString("msg");
                if (code != 1) {
                     ImgProcess.delImgFile(realImgName);
                } else {
                    String respStr = response.getString("respStr");
                    JSONObject resp = JSONObject.parseObject(respStr);
                    msg = resp.getString("msg");
                    if (resp.getInteger("status") != 1) {
                        ImgProcess.delImgFile(realImgName);
                    } else {
                        Status.isImgUpdate = true;
                    }
                }
                  /* 非UI线程错误提示 */
                Looper.prepare();
                Toast.makeText(PersonDetail.this, msg, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();
    }

    /* 调用系统裁剪功能 */
    private void startImageZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 99998);
        intent.putExtra("aspectY", 99999);
        intent.putExtra("outputX", 125);
        intent.putExtra("outputY", 125);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

}
