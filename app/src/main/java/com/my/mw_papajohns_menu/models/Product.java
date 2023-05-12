package com.my.mw_papajohns_menu.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private String name;
    private String info;
    private float price;
    private int count;
    private int imageId;
    private ProductCategory selectedCategory;
    private List<ProductCategory> productCategoryList;

    static public class ProductCategory implements Serializable{
        private String categoryName;
        private float price;

        public ProductCategory(String categoryName, float totalPrice) {
            this.categoryName = categoryName;
            this.price = totalPrice;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public float getPrice() {
            return price;
        }

        @NonNull
        @Override
        public String toString() {
            return categoryName;
        }
    }

    public Product(String name, String info, int imageId, List<ProductCategory> productCategoryList) {
        this.name = name;
        this.info = info;
        this.imageId = imageId;
        this.productCategoryList = productCategoryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public ProductCategory getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(ProductCategory selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public void addCategory(String name, float price){
        productCategoryList.add(new ProductCategory(name, price));
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Product){
            Product product = (Product) obj;
            if (product.getName().equals(this.name) &&
                    product.getSelectedCategory().categoryName.equals(selectedCategory.categoryName)){
                return true;
            }
        }
        return false;
    }
}
