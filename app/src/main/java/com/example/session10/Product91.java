package com.example.session10;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product91 implements Parcelable {
    private String styleId;
    private String brand;
    private String price;
    private String info;
    private String searchImage;

    public Product91() {
    }

    public Product91(String styleId, String brand, String price, String info, String searchImage) {
        this.styleId = styleId;
        this.brand = brand;
        this.price = price;
        this.info = info;
        this.searchImage = searchImage;
    }

    protected Product91(Parcel in) {
        styleId = in.readString();
        brand = in.readString();
        price = in.readString();
        info = in.readString();
        searchImage = in.readString();
    }

    public static final Creator<Product91> CREATOR = new Creator<Product91>() {
        @Override
        public Product91 createFromParcel(Parcel in) {
            return new Product91(in);
        }

        @Override
        public Product91[] newArray(int size) {
            return new Product91[size];
        }
    };

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSearchImage() {
        return searchImage;
    }

    public void setSearchImage(String searchImage) {
        this.searchImage = searchImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(styleId);
        dest.writeString(brand);
        dest.writeString(price);
        dest.writeString(info);
        dest.writeString(searchImage);
    }
}
