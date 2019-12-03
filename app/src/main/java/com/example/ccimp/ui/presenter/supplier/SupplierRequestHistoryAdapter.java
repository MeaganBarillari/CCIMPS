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

public class SupplierRequestHistoryAdapter extends ArrayAdapter<BusinessRequest> {
    private ArrayList<BusinessRequest> requestArrayList;

    public SupplierRequestHistoryAdapter(@NonNull Context context, int resourceId, ArrayList<BusinessRequest> requestArrayList) {
        super(context,resourceId,requestArrayList);
        this.requestArrayList = requestArrayList;
    }

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }

        // Get businessRequest object at the position
        BusinessRequest businessRequest = requestArrayList.get(position);
        TextView requestID = v.findViewById(R.id.column1);
        TextView requestDateTime = v.findViewById(R.id.column2);
        TextView requestStatus = v.findViewById(R.id.column3);

        if (requestID != null){
            requestID.setText(businessRequest.getRequestID());
        }
        if (requestDateTime != null){
            requestDateTime.setText(businessRequest.getRequestDate());
        }
        if(requestStatus != null){
            requestStatus.setText(businessRequest.getStatus());
        }

        return v;
    }
}
