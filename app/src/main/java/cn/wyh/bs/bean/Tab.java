package cn.wyh.bs.bean;

import android.support.v4.app.Fragment;

public class Tab {
    private String tag;
    private String text;
    private int icon;
    private Class<? extends Fragment> mainFragment;

    public Tab(String tag, String text, int icon, Class<? extends Fragment> mainFragment) {
        this.tag = tag;
        this.text = text;
        this.icon = icon;
        this.mainFragment = mainFragment;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class<? extends Fragment> getMainFragment() {
        return mainFragment;
    }

    public void setMainFragment(Class<? extends Fragment> mainFragment) {
        this.mainFragment = mainFragment;
    }
}
