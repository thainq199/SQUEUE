package com.example.squeue.model;

public class HomeMenu {
    private String menuImg;
    private String menuImgName;

    public HomeMenu() {

    }

    public HomeMenu(String menuImg, String menuImgName) {
        this.menuImg = menuImg;
        this.menuImgName = menuImgName;
    }

    public String getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(String menuImg) {
        this.menuImg = menuImg;
    }

    public String getMenuImgName() {
        return menuImgName;
    }

    public void setMenuImgName(String menuImgName) {
        this.menuImgName = menuImgName;
    }
}
