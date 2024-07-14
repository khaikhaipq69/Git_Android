package com.example.session09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PictureAdapter extends BaseAdapter {

    private Context mContext;
    private List<Product> mList;

    public PictureAdapter(Context mContext, List<Product> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.itemview, parent, false);
            holder = new ViewHolder();
            holder.imageview = convertView.findViewById(R.id.itemview_searchImage);
            holder.styleIDTV = convertView.findViewById(R.id.itemview_tvStyleId);
            holder.brandTv = convertView.findViewById(R.id.itemview_tvBrand);
            holder.priceTv = convertView.findViewById(R.id.itemview_tvPrice);
            holder.infoTv = convertView.findViewById(R.id.itemview_tvInfo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = mList.get(position);
        if (product != null) {
            Picasso.get().load(product.getSearchImage()).into(holder.imageview);
            holder.styleIDTV.setText(product.getStyleId());
            holder.brandTv.setText(product.getBrand());
            holder.priceTv.setText(product.getPrice());
            holder.infoTv.setText(product.getInfo());
        }
        return convertView;

    }

    static class ViewHolder {
        ImageView imageview;
        TextView styleIDTV;
        TextView brandTv;
        TextView priceTv;
        TextView infoTv;
    }
}
