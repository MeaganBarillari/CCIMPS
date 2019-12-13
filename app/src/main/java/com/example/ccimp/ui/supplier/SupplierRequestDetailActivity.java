package com.example.ccimp.ui.supplier;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ccimp.R;
import com.example.ccimp.ui.interfaces.supplier.SupplierRequestDetailInterface;
import com.example.ccimp.ui.model.BusinessRequest;
import com.example.ccimp.ui.model.Handler;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.request_info;
import com.example.ccimp.ui.presenter.supplier.SupplierCurrentRequestAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestDetailAdapter;
import com.example.ccimp.ui.presenter.supplier.SupplierRequestDetailPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SupplierRequestDetailActivity extends AppCompatActivity implements SupplierRequestDetailInterface.SupplierRequestDetailView {

    ArrayList<request_info> itemList;
    private TextView businessName, requestID, status, totalPrice;
    private ListView requestItemListView;
    BottomNavigationView navigation;
    Spinner spinner;
    private User supplier;
//    private SupplierRequestDetailAdapter supplierRequestDetailAdapter;
//    private SupplierRequestDetailInterface.SupplierRequestDetailPresenter supplierRequestDetailPresenter;
    Button btnChangeStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_request_detail);

        requestItemListView = findViewById(R.id.requestiems);
        navigation = findViewById(R.id.supplierNavigation);
        businessName = findViewById(R.id.business_name);
        requestID = findViewById(R.id.request_number);
        status = findViewById(R.id.request_status);
        totalPrice = findViewById(R.id.totalPrice);
        itemList = new ArrayList<>();
        setupRequestItemList();

        String[] items = new String[] {"Decline", "Accept", "Complete", "Ready", "In Progress"};
        spinner = (Spinner) findViewById(R.id.changestatus);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setSelection(adapter.getPosition(status.toString()));
        spinner.setAdapter(adapter);

        // Gets request object and sets text view's based on request fields
        // returns the request object that we use to get the supplierID and get the requestItem Listview
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        supplier = intent.getParcelableExtra("supplier");
        businessName.setText(bundle.getString("businessName"));
        requestID.setText(bundle.getString("requestID"));

        status.setText(bundle.getString("status"));
        totalPrice.setText(bundle.getString("price"));

//        supplierRequestDetailPresenter = new SupplierRequestDetailPresenter(this, tempRequest);
//        supplierRequestDetailPresenter.onViewCreate();

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return callSupplierNavigation(item);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int count = 0;
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(count >= 1){
                    status.setText(parentView.getItemAtPosition(position).toString());
                    UpdateStatus updateStatus = new UpdateStatus(SupplierRequestDetailActivity.this);

                    updateStatus.execute(requestID.getText().toString(), status.getText().toString());
                }
                count++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    @Override
    public boolean callSupplierNavigation(MenuItem supplierMenuItem) {
        switch (supplierMenuItem.getItemId()) {
            case R.id.supplier_navigation_home:
                Intent c = new Intent(SupplierRequestDetailActivity.this, SupplierHomeActivity.class);
                c.putExtra("supplier", supplier);
                startActivity(c);
                break;
            case R.id.navigation_supplier_inventory:
                Intent d = new Intent(SupplierRequestDetailActivity.this, SupplierInventoryActivity.class);
                d.putExtra("supplier", supplier);
                startActivity(d);
                break;
            case R.id.navigation_supplier_profile:
                Intent b = new Intent(SupplierRequestDetailActivity.this, SupplierProfileActivity.class);
                b.putExtra("supplier", supplier);
                startActivity(b);
                break;
        }
        return false;
    }

    @Override
    public BusinessRequest getIntentData(Intent intent) {
     return null;
    }

    @Override
    public void setSupplierUser(User supplier) {
        this.supplier = supplier;
    }

    @Override
    public void setupRequestItemList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://shifanzhou.com/getRequestInfo.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("request_info");
                            for(int i = 0; i< array.length();i++){
                                JSONObject orderObj = array.getJSONObject(i);
                                request_info item = new request_info(orderObj.getString("requestId"), orderObj.getString("itemName"), orderObj.getString("quantity"), orderObj.getString("price"));
                                if(item.getRequestid().equals(requestID.getText())){
                                    itemList.add(item);
                                }
                            }

                            SupplierRequestDetailAdapter adapter = new SupplierRequestDetailAdapter(itemList, getApplicationContext());
                            requestItemListView.setAdapter(adapter);
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

    private class UpdateStatus extends AsyncTask<String, Void, String> {
        Context context;

        UpdateStatus(Context ctx) {
            this.context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String update_url = "http://shifanzhou.com/updateRequestStatus.php";
            try {
                String id = params[0];
                String status = params[1];
                URL url = new URL(update_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("requestID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&"
                        + URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");
                System.out.println(id);
                System.out.println(status);


                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("Status updated")) {
                Toast.makeText(context, "Status updated", Toast.LENGTH_LONG).show();
            }
        }
    }

}
