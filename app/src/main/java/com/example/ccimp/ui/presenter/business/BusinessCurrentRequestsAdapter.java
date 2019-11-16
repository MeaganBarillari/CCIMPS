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
import com.example.ccimp.ui.model.Request;
import com.example.ccimp.ui.model.inventory_business;

import java.util.ArrayList;

public class BusinessCurrentRequestsAdapter extends ArrayAdapter<Request> {
    private ArrayList<Request> requestsArrayList;

    public BusinessCurrentRequestsAdapter(@NonNull Context context, int resourceId, ArrayList<Request> requestsArrayList) {
        super(context,resourceId,requestsArrayList);
        this.requestsArrayList = requestsArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.rowtwolines, parent, false);
        }

        // Get request object at the position
        Request request = requestsArrayList.get(position);
        TextView date = v.findViewById(R.id.column1);
        TextView status = v.findViewById(R.id.column2);

        if (date != null){
            date.setText(request.getRequestDate());
        }
        if (status != null){
            status.setText(request.getStatus());
        }

        return v;
    }
}
