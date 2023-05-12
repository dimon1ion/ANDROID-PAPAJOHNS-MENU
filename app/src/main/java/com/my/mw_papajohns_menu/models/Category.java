package com.my.mw_papajohns_menu.models;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private String name;
    private int imageId;

    private List<Product> products;

    public Category() {
    }

    public Category(String name, int imageId, List<Product> products) {
        this.name = name;
        this.imageId = imageId;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
