package com.example.slot5;

import static androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior.setTag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

public class Adapter extends BaseAdapter {
    private List<Product> productList;
    private Context context;

    public Adapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false);
            holder = new BaseViewHolder();
            holder.img = convertView.findViewById(R.id.itemview_image);
            holder.id = convertView.findViewById(R.id.itemview_id);
            holder.name = convertView.findViewById(R.id.itemview_name);
            holder.price = convertView.findViewById(R.id.itemview_price);
            convertView.setTag(holder);

        }else{
            holder = (BaseViewHolder) convertView.getTag();
        }
        Product product = productList.get(position);
        if(product != null){
            holder.img.setImageResource(R.drawable.ic_launcher_background);
            holder.id.setText(product.getId());
            holder.name.setText(product.getName());
            holder.price.setText(String.valueOf(product.getPrice()));
        }
        return convertView;
    }
    static class BaseViewHolder {
        ImageView img;
        TextView id, name, price;
    }
}


