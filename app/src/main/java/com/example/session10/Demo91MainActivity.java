package com.example.session10;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.session10.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Demo91MainActivity extends AppCompatActivity {
    private ListView listView;
    private Demo91Adapter adapter;
    private List<Product91> list;
    Demo10CartManager cartManager;//gio hang
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo91_main);
        listView=findViewById(R.id.demo91Listview);
        cartManager=Demo10CartManager.getInstance();
        list=new ArrayList<>();
        adapter=new Demo91Adapter(this,list);
        listView.setAdapter(adapter);
        new FetchProduct91().execute();
    }
    private class FetchProduct91 extends AsyncTask<Void,Void,String>{
        //read data from server
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder response=new StringBuilder();
            try {
                URL url=new URL("http://192.168.1.7/aphp/select.php");
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;//doc theo dong
                while ((line=reader.readLine())!=null){
                    response.append(line);
                }
                reader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return response.toString();
        }
        //return data to client
        @Override
        protected void onPostExecute(String s) {
            if(s!=null && !s.isEmpty()){
                try {
                    JSONObject json=new JSONObject(s);
                    JSONArray productsArray=json.getJSONArray("products");
                    for(int i=0;i<productsArray.length();i++){
                        JSONObject pObject=productsArray.getJSONObject(i);
                        String styleId=pObject.getString("styleid");
                        String brand=pObject.getString("brands_filter_facet");
                        String price=pObject.getString("price");
                        String info=pObject.getString("product_additional_info");
                        String searchImage=pObject.getString("search_image");
                        Product91 product=new Product91(styleId,brand,price,info,searchImage);
                        list.add(product);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}