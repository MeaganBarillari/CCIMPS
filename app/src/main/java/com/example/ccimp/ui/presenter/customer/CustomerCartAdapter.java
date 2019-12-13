package com.example.ccimp.ui.presenter.customer;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.User;
import com.example.ccimp.ui.model.inventory_business;
import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public class CustomerCartAdapter extends ArrayAdapter<inventory_business> {
    private ArrayList<inventory_business> itemArrayList;

    public CustomerCartAdapter(ArrayList<inventory_business> itemArrayList, @NonNull Context context) {
        super(context,R.layout.activity_customer_order_cart,itemArrayList);
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.rowtwolines, parent, false);
        }

        // Get request object at the position
        inventory_business item = itemArrayList.get(position);
        TextView itemName = v.findViewById(R.id.column1);
        TextView price = v.findViewById(R.id.column2);


        if (itemName != null){
            itemName.setText(item.getItemName());
        }

        if (price != null){
            price.setText("$" + item.getPrice());
        }

        return v;
    }
}
