package com.example.ccimp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ccimp.R;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.Order;
import com.example.ccimp.ui.model.inventory_business;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BusinessInventoryActivity extends AppCompatActivity {


    ListView listView;
    List<Item> itemList;
//    inventory_business inventory1 = new inventory_business("Bread", "231", "312", "30", "20", "600");
//    inventory_business[] values = new inventory_business[]{inventory1};

    Button btnRequestItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_inventory);

        btnRequestItems = findViewById(R.id.requestItem);

        btnRequestItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessInventoryActivity.this, BusinessSupplierListActivity.class));
            }
        });

        listView = findViewById(R.id.listitem);
        itemList = new ArrayList<>();
        showList();

//        CustomAdapter customAdapter = new CustomAdapter();
//
//        listView.setAdapter(customAdapter);

        BottomNavigationView navigation = findViewById(R.id.businessNavigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent c = new Intent(BusinessInventoryActivity.this,BusinessHomeActivity.class);
                        startActivity(c);
                        break;
                    case R.id.navigation_requests:
                        Intent a = new Intent(BusinessInventoryActivity.this,BusinessRequestsActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_inventory:
                        Intent b = new Intent(BusinessInventoryActivity.this,BusinessInventoryActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_business_profile:
                        Intent d = new Intent(BusinessInventoryActivity.this,BusinessProfileActivity.class);
                        startActivity(d);
                        break;
                }
                return false;
            }
        });
    }
//    class CustomAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return values.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = getLayoutInflater().inflate(R.layout.rowfourlines, null);
//            TextView column1 = view.findViewById(R.id.column1);
//            TextView column2 = view.findViewById(R.id.column2);
//            TextView column3 = view.findViewById(R.id.column3);
//            TextView column4 = view.findViewById(R.id.column4);
//            column1.setText(values[position].getItemName());
//            column2.setText(values[position].getPrice());
//            column3.setText(values[position].getQuantity());
//            column4.setText(values[position].getAvailQuantity());
//
//            return view;
//        }
//    }

    class businessInventoryAdapter extends ArrayAdapter<Item> {
        private List<Item> itemList;
        private Context ctx;
        public businessInventoryAdapter(List<Item> P, Context c){
            super(c, R.layout.activity_business_inventory, P);
            this.itemList = P;
            this.ctx = c;
        }

        @Override
        public View getView(int position, View mView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = mView;

            if(view == null) {
                view = inflater.inflate(R.layout.rowtwolines, parent, false);

            }
            TextView itemName = view.findViewById(R.id.column1);
            TextView price = view.findViewById(R.id.column2);


            Item item = itemList.get(position);
            itemName.setText(item.getName());
            price.setText(item.getPrice());


            return view;
        }
    }

    private static class Handler{
        public  static Handler mInstance;
        private RequestQueue requestQueue;
        private static Context ctx;

        private Handler(Context context) {
            ctx = context;
            requestQueue = getRequestQueue();
        }

        public RequestQueue getRequestQueue(){
            if(requestQueue == null) {
                requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
            }

            return requestQueue;
        }

        public static synchronized Handler getInstance (Context context) {
            if(mInstance == null) {
                mInstance = new Handler(context);
            }

            return mInstance;
        }

        public  <T> void addToRequestQue(Request<T> request){
            requestQueue.add(request);
        }
    }

    private void showList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getSupplierInventory.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("supplierInventory");

                            for(int i = 0; i< array.length();i++){
                                JSONObject itemObj = array.getJSONObject(i);

                                Item item = new Item(itemObj.getString("itemID"), itemObj.getString("supplierID"),itemObj.getString("name"), itemObj.getString("price"), itemObj.getString("customDetail"));
                                itemList.add(item);
                            }

                            businessInventoryAdapter adapter = new businessInventoryAdapter(itemList, getApplicationContext());
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }

}
