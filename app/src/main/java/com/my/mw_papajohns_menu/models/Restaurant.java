package com.my.mw_papajohns_menu.models;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String address;
    private String time;
    private int imageId;

    public Restaurant(String address, String time, int imageId) {
        this.address = address;
        this.time = time;
        this.imageId = imageId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
