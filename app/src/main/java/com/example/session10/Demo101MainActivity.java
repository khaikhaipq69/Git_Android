package com.example.session10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.session10.R;
import com.squareup.picasso.Picasso;

public class Demo101MainActivity extends AppCompatActivity {
    private TextView tvStyleID, tvBrand,tvPrice,tvAddInfo;
    private ImageView img;
    Intent intent;
    Product91 product;
    Demo10CartManager cartManager;//gio hang
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo101_main);
        img=findViewById(R.id.demo101ImageView1);
        tvStyleID=findViewById(R.id.demo101TvStyleID);
        tvBrand=findViewById(R.id.demo101TvBrand);
        tvPrice=findViewById(R.id.demo101TvPrice);
        tvAddInfo=findViewById(R.id.demo101TvAddInfo);
        btn=findViewById(R.id.demo101BtnAddToCart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCartClicked();
            }
        });
        //---
        cartManager=Demo10CartManager.getInstance();
        //---
        intent=getIntent();//get data from listProduct
        product=intent.getParcelableExtra("PRODUCT");
        if(product!=null){
            Picasso.get().load(product.getSearchImage()).into(img);
            tvStyleID.setText(product.getStyleId());
            tvBrand.setText(product.getBrand());
            tvPrice.setText(product.getPrice());
            tvAddInfo.setText(product.getInfo());
        }
    }
    private void addToCartClicked(){
        Intent intent1=getIntent();
        Product91 product1=intent1.getParcelableExtra("PRODUCT");
        if(product1!=null){
            cartManager.addProductToCart(product1);//them san pham vao gio hang
            //mo cart activity
            Intent intent2=new Intent(this,Demo101CartActivity.class);
            startActivity(intent2);
        }

    }
}