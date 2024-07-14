package com.example.session10;

import java.util.ArrayList;
import java.util.List;

public class Demo10CartManager {
    private static Demo10CartManager instance;//bien toan cuc=gio hang
    private List<Product91> cartItems;//danh sach mat hang o trong gio hang
    Demo10CartManager(){
        cartItems=new ArrayList<>();//khoi tao
    }
    //phuong thuc xu ly bien tinh => xu ly de du lieu khong mat di khi chuyen form
    public static synchronized Demo10CartManager getInstance(){
        if(instance==null){
            instance=new Demo10CartManager();
        }
        return instance;
    }
    //them san pham vao gio hang
    public void addProductToCart(Product91 product){
        cartItems.add(product);
    }
    //lay ve 1 item trong gio hang
    public List<Product91> getCartItems(){
        return cartItems;
    }
}
