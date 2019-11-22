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
import com.example.ccimp.ui.model.BusinessRequest;

import java.util.ArrayList;

public class SupplierCurrentRequestAdapter extends ArrayAdapter<BusinessRequest> {
    private ArrayList<BusinessRequest> businessRequestArrayList;

    public SupplierCurrentRequestAdapter(@NonNull Context context, int resourceId, ArrayList<BusinessRequest> businessRequestArrayList) {
        super(context,resourceId, businessRequestArrayList);
        this.businessRequestArrayList = businessRequestArrayList;
    }

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }

        // Get businessRequest object at the position
        BusinessRequest businessRequest = businessRequestArrayList.get(position);
        TextView businessName = v.findViewById(R.id.column1);
        TextView price = v.findViewById(R.id.column2);
        TextView status = v.findViewById(R.id.column3);

        if (businessName != null){
            businessName.setText(businessRequest.getBusinessName());
        }
        if (price != null){
            price.setText(businessRequest.getPrice());
        }
        if(status != null){
            status.setText(businessRequest.getStatus());
        }

        return v;
    }
}
