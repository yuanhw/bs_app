package cn.wyh.bs.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by WYH on 2018/1/17.
 * 用户头像处理
 */

public class ImgProcess {

    public static Uri getImgPath(String fileName) {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/cn.wyh.bs/img");
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        File img = new File(tmpDir.getAbsolutePath() + File.separator + fileName);
        return Uri.fromFile(img);
    }

    /* 保存位图到本地并返回file开头的uri */
    public static Uri saveBitmap(Bitmap bitmap, String fileName) {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/cn.wyh.bs/img");
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        File img = new File(tmpDir.getAbsolutePath() + "/" + fileName);
        try {
            FileOutputStream output = new FileOutputStream(img);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
            output.flush();
            output.close();
            return Uri.fromFile(img);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 转化uri格式为file开头  */
    public static Uri convertUri(Context context, Uri uri, String fileName) {
        InputStream in;
        try {
            in = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            in.close();
            return saveBitmap(bitmap, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 图片保存位置&&文件提供者 */
    public static Uri getUriByFileProvider(Context context, String fileName, boolean tag) {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/cn.wyh.bs/img");
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        Uri photoUri;
        if (tag) {
            photoUri = FileProvider.getUriForFile(context, "cn.wyh.bs.fileprovider", new File(tmpDir.getAbsolutePath() + "/" + fileName));
        } else {
            photoUri = Uri.fromFile(new File(tmpDir.getAbsolutePath() + "/" + fileName));
        }
        return photoUri;
    }

    /* 删除头像 */
    public static void delImgFile(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory() + "/cn.wyh.bs/img/" + fileName);
        if (file.exists()) {
            file.delete();
        }
    }

}
