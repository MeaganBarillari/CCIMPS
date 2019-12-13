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
import com.example.ccimp.ui.model.Order;

import java.util.ArrayList;

public class BusinessCurrentOrderAdapter extends ArrayAdapter<Order> {
    private ArrayList<Order> orderArrayList;

    public BusinessCurrentOrderAdapter(ArrayList<Order> orderArrayList, @NonNull Context context) {
        super(context,R.layout.activity_business_home,orderArrayList);
        this.orderArrayList = orderArrayList;
    }

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }

        // Get request object at the position
        Order order = orderArrayList.get(position);
        TextView customerName = v.findViewById(R.id.column1);
        TextView dateTime = v.findViewById(R.id.column2);
        TextView status = v.findViewById(R.id.column3);

        if (customerName != null){
            customerName.setText(order.getCustomerName());
        }
        if (dateTime != null){
            dateTime.setText(order.getCreateDateTime());
        }
        if(status != null){
            status.setText(order.getStatus());
        }

        return v;
    }
}
