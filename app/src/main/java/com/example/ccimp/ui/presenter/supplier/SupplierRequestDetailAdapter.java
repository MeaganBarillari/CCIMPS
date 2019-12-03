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
import com.example.ccimp.ui.model.request_info;

import java.util.ArrayList;

public class SupplierRequestDetailAdapter extends ArrayAdapter<request_info> {
    private ArrayList<request_info> requestItemArrayList;

    public SupplierRequestDetailAdapter(@NonNull Context context, int resourceId, ArrayList<request_info> requestItemArrayList) {
        super(context, resourceId, requestItemArrayList);
        this.requestItemArrayList = requestItemArrayList;
    }

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }

        // Get request_info object at the position
        request_info item = requestItemArrayList.get(position);
        TextView itemName = v.findViewById(R.id.column1);
        TextView quantity = v.findViewById(R.id.column2);
        TextView totalPrice = v.findViewById(R.id.column3);

        // TODO: NEED THIS TO BE ITEM NAME, must change the request_info DB and how we will save info
        if (itemName != null){
            itemName.setText(item.getItemid());
        }
        if (quantity != null){
            quantity.setText(item.getQuantity());
        }
        if(totalPrice != null){
            totalPrice.setText(item.getTotalPrice());
        }

        return v;
    }
}
