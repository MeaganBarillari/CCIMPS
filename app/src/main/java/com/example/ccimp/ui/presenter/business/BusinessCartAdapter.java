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
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;

public class BusinessCartAdapter extends ArrayAdapter<inventory_supplier> {
    private ArrayList<inventory_supplier> itemArrayList;

    public BusinessCartAdapter(ArrayList<inventory_supplier> itemArrayList, @NonNull Context context) {
        super(context,R.layout.activity_customer_order_cart,itemArrayList);
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.rowfourlines, parent, false);
        }

        // Get request object at the position
        inventory_supplier inventorySupplier = itemArrayList.get(position);
        TextView itemName = v.findViewById(R.id.column1);
        TextView supplier = v.findViewById(R.id.column2);
        TextView Q = v.findViewById(R.id.column3);
        TextView price = v.findViewById(R.id.column4);


        if (itemName != null){
            itemName.setText(inventorySupplier.getItemName());
        }
        if (supplier != null){
            supplier.setText(inventorySupplier.getSupplierID());
        }
        if (Q != null){
            Q.setText(inventorySupplier.getQuantity());
        }
        if (price != null){
            price.setText(inventorySupplier.getPrice());
        }


        return v;
    }
}
