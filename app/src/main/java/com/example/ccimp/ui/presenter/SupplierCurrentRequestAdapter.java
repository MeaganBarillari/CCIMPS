package com.example.ccimp.ui.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ccimp.R;
import com.example.ccimp.ui.model.Request;

import java.util.ArrayList;

public class SupplierCurrentRequestAdapter extends ArrayAdapter<Request> {
    private ArrayList<Request> requestArrayList;

    public SupplierCurrentRequestAdapter(@NonNull Context context, int resourceId, ArrayList<Request> requestArrayList) {
        super(context,resourceId,requestArrayList);
        this.requestArrayList = requestArrayList;
    }
    @NonNull

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }

        // Get request object at the position
        Request request = requestArrayList.get(position);
        TextView businessName = v.findViewById(R.id.column1);
        TextView price = v.findViewById(R.id.column2);
        TextView status = v.findViewById(R.id.column3);

        if (businessName != null){
            businessName.setText(request.getBusinessName());
        }
        if (price != null){
            price.setText(request.getPrice());
        }
        if(status != null){
            status.setText(request.getStatus());
        }

        return v;
    }

}
