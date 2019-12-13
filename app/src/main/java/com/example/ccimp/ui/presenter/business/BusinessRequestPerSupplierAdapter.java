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
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;

public class BusinessRequestPerSupplierAdapter extends ArrayAdapter<inventory_supplier> {

    private ArrayList<inventory_supplier> inventoryArrayList;

    public BusinessRequestPerSupplierAdapter(ArrayList<inventory_supplier> inventoryArrayList, @NonNull Context context) {
        super(context,R.layout.activity_request_per_supplier,inventoryArrayList);
        this.inventoryArrayList = inventoryArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.rowtwolines, parent, false);
        }

        // Get request object at the position
        inventory_supplier inventorySupplier = inventoryArrayList.get(position);
        TextView itemName = v.findViewById(R.id.column1);
        TextView price = v.findViewById(R.id.column2);


        if (itemName != null){
            itemName.setText(inventorySupplier.getItemName());
        }

        if (price !=null){
            price.setText("$"+ inventorySupplier.getPrice());
        }


        return v;
    }

}
