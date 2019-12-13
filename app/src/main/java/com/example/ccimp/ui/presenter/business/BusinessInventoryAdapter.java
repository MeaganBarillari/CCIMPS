package com.example.ccimp.ui.presenter.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public class BusinessInventoryAdapter extends ArrayAdapter<inventory_business> {
    private ArrayList<inventory_business> inventoryArrayList;

    public BusinessInventoryAdapter(ArrayList<inventory_business> inventoryArrayList, @NonNull Context context) {
        super(context,R.layout.activity_business_inventory,inventoryArrayList);
        this.inventoryArrayList = inventoryArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.rowfourlines, parent, false);
        }

        // Get request object at the position
        inventory_business inventoryBusiness = inventoryArrayList.get(position);
        TextView itemName = v.findViewById(R.id.column1);
        TextView price = v.findViewById(R.id.column2);
        TextView Q = v.findViewById(R.id.column3);
        TextView AvQ = v.findViewById(R.id.column4);

        if (itemName != null){
            itemName.setText(inventoryBusiness.getItemName());
        }
        if (price != null){
            price.setText(inventoryBusiness.getPrice());
        }
        if (Q != null){
            Q.setText(inventoryBusiness.getQuantity());
        }
        if (AvQ != null){
            AvQ.setText(inventoryBusiness.getAvailQuantity());
        }

        return v;
    }
}
