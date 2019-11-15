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
import com.example.ccimp.ui.model.Item;

import java.util.ArrayList;

public class BusinessRequestFromMenuAdapter extends ArrayAdapter<Item> {
    private ArrayList<Item> itemArrayList;

    public BusinessRequestFromMenuAdapter(@NonNull Context context, int resourceId, ArrayList<Item> itemArrayList) {
        super(context,resourceId,itemArrayList);
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.rowoneline, parent, false);
        }

        // Get request object at the position
        Item item = itemArrayList.get(position);
        TextView name = v.findViewById(R.id.column1);

        if (name != null){
            name.setText(item.getName());
        }

        return v;
    }
}
