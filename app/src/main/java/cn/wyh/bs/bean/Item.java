package cn.wyh.bs.bean;

/**
 * Created by WYH on 2018/1/15.
 */

public class Item {
    private int imgId;
    private String title;

    public Item(int imgId, String title) {
        this.imgId = imgId;
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
