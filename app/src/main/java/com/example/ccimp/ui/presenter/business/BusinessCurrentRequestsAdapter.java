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
import com.example.ccimp.ui.model.BusinessRequest;

import java.util.ArrayList;

public class BusinessCurrentRequestsAdapter extends ArrayAdapter<BusinessRequest> {
    private ArrayList<BusinessRequest> requestsArrayList;

    public BusinessCurrentRequestsAdapter(ArrayList<BusinessRequest> requestsArrayList, @NonNull Context context) {
        super(context,R.layout.activity_business_requests,requestsArrayList);
        this.requestsArrayList = requestsArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.rowtwolines, parent, false);
        }

        // Get businessRequest object at the position
        BusinessRequest businessRequest = requestsArrayList.get(position);
        TextView date = v.findViewById(R.id.column1);
        TextView status = v.findViewById(R.id.column2);

        if (date != null){
            date.setText(businessRequest.getRequestDate());
        }
        if (status != null){
            status.setText(businessRequest.getStatus());
        }

        return v;
    }
}
