package com.example.ccimp.ui.presenter.supplier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.Item;
import com.example.ccimp.ui.model.inventory_supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierInventoryAdapter extends ArrayAdapter<Item> {
    private List<Item> inventoryArrayList;
    private Context ctx;

    public SupplierInventoryAdapter(List<Item> inventoryArrayList, @NonNull Context context) {
        super(context,R.layout.activity_supplier_inventory,inventoryArrayList);
        this.inventoryArrayList = inventoryArrayList;
        this.ctx = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.rowtwolines, parent, false);
        }

        // Get request object at the position
        Item item = inventoryArrayList.get(position);
        TextView itemName = v.findViewById(R.id.column1);
        TextView price = v.findViewById(R.id.column2);

        if (itemName != null){
            itemName.setText(item.getName());
        }
        if (price != null){
            price.setText(item.getPrice());
        }

        return v;
    }
}
