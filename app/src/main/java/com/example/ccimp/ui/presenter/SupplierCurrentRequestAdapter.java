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
    private Context context;
    public SupplierCurrentRequestAdapter(@NonNull Context context, int resourceId, ArrayList<Request> RequestList) {
        super(context,resourceId,RequestList);
        this.context = context;
    }
    @NonNull

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }

        Request request = getItem(position);
        TextView businessName = view.findViewById(R.id.column1);
        TextView price = view.findViewById(R.id.column2);
        TextView status = view.findViewById(R.id.column3);
        businessName.setText(request.getBusinessName());
        price.setText(request.getPrice());
        status.setText(request.getStatus());

        return view;
    }

}
