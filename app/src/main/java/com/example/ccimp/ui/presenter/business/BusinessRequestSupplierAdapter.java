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
import com.example.ccimp.ui.model.User;

import java.util.ArrayList;

public class BusinessRequestSupplierAdapter extends ArrayAdapter<User> {
    private ArrayList<User> userArrayList;

    public BusinessRequestSupplierAdapter(@NonNull Context context, int resourceId, ArrayList<User> userArrayList) {
        super(context,resourceId,userArrayList);
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.rowoneline, parent, false);
        }

        // Get request object at the position
        User user = userArrayList.get(position);
        TextView name = v.findViewById(R.id.column1);

        if (name != null){
            name.setText(user.getUsername());
        }

        return v;
    }
}
