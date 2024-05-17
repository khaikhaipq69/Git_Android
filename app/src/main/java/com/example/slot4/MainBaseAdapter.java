package com.example.slot4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.List;


public class MainBaseAdapter extends BaseAdapter {

    private Context context;
    private List<Student> list;

    public MainBaseAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //Create a blank view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1. Create view
        //2. set data
        MainViewHolder holder;
        if (convertView == null){//not exist view => create a new view
        //create a blank view
        convertView= LayoutInflater.from(context)
                .inflate(R.layout.activity_main, parent, false);
        //refer to the view holder
            holder=new MainViewHolder();
            holder.itemview=convertView.findViewById(R.id.itemview);
            holder.nameString=convertView.findViewById(R.id.nameString);
            holder.ageString=convertView.findViewById(R.id.ageInt);
            //create a template for later
            convertView.setTag(holder);
        }
        else {//exist view -> get older view
            holder= (MainViewHolder) convertView.getTag();
        }
        //2. Set data
        Student student = list.get(position);//get an object
        holder.itemview.setImageResource(student.getItemview());
        holder.nameString.setText(student.getNameString());
        holder.ageString.setText(String.valueOf(student.ageString()));
        return convertView;

    }

//create a class for refer to components of item view: view holder
    static class MainViewHolder{
        ImageView itemview;
        TextView nameString, ageString;
    }
}
