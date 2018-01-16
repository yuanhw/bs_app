package cn.wyh.bs.bean;

/**
 * Created by WYH on 2018/1/15.
 */

public class Item {
    private int id;
    private int imgId;
    private String title;

    public Item(int id, int imgId, String title) {
        this.id = id;
        this.imgId = imgId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
