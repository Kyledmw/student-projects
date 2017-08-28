package com.hms.common.view.models;

/**
 * Created by alan on 31/03/16.
 */
public class MenuItem {

    private int iconId;
    private String menuText;

    public MenuItem(int iconId, String menuText) {
        this.iconId = iconId;
        this.menuText = menuText;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }
}
