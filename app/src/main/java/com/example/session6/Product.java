package com.example.session6;

public class Product {
    private String maSP;
    private String tenSP;
    private int slSP;

    public Product() {
    }

    public Product(String maSP, String tenSP, int slSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.slSP = slSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSlSP() {
        return slSP;
    }

    public void setSlSP(int slSP) {
        this.slSP = slSP;
    }


}
