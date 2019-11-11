package com.example.ccimp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ccimp.R;

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

public class SupplierAddToInventoryActivity extends AppCompatActivity {

    EditText etItemName, etPrice;
    Button btn_addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_add_to_inventory);

        etItemName = findViewById(R.id.input_itemName);
        etPrice = findViewById(R.id.input_price);
        btn_addItem = findViewById(R.id.btn_addItem);

        btn_addItem.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String itemName = etItemName.getText().toString();
                String price = etPrice.getText().toString();
                String supplierID = "13";
                String customDetail = itemName;
                backgroundWorker bgWorker = new backgroundWorker(SupplierAddToInventoryActivity.this);
                bgWorker.execute(itemName, price, supplierID, customDetail);
            }
        });

    }


    private class backgroundWorker extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context context;
        backgroundWorker(Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            String addItem_url = "http://shifanzhou.com/addItem.php";
                try {
                    String itemName = params[0];
                    String price = params[1];
                    String supplierID = params[2];
                    String customDetail = params[3];

                    URL url = new URL(addItem_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(itemName, "UTF-8") + "&"
                            + URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8") + "&"
                            + URLEncoder.encode("supplierID", "UTF-8") + "=" + URLEncoder.encode(supplierID, "UTF-8") + "&"
                            + URLEncoder.encode("customDetail", "UTF-8") + "=" + URLEncoder.encode(customDetail, "UTF-8");
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
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Item status");
        }

        @Override
        protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
        Intent intent = new Intent(SupplierAddToInventoryActivity.this, SupplierHomeActivity.class);
        startActivity(intent);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }
}

