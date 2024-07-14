package com.example.session09;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.lang.*;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private PictureAdapter adapter;
    private List<Product> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new PictureAdapter(this, list);
        listView.setAdapter(adapter);
        new FetchProduct().execute();
    }

    private class FetchProduct extends AsyncTask<Void, Void, String> {
        //read data from server
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder builder = new StringBuilder();
            try {
                URL url = new URL("http://192.168.1.7/aphp/select.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                reader.close();
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return builder.toString();
        }

        //return data to client
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null && !s.isEmpty()) {
                try {
                    JSONObject json = new JSONObject(s);
                    JSONArray array = json.getJSONArray("products");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject pObject = array.getJSONObject(i);
                        String styleId = pObject.getString("styleid");
                        String brand = pObject.getString("brands_filter_facet");
                        String price = pObject.getString("price");
                        String info = pObject.getString("product_additional_info");
                        String searchImage = pObject.getString("search_image");
                        Product product = new Product(styleId, brand, price, info, searchImage);
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