package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderVH extends RecyclerView.ViewHolder {
//
    public TextView txt_firstname, txt_lastname, txt_phonenumber, txt_emailaddress, txt_quantity, txt_address, txt_option;

    public OrderVH(@NonNull View itemView) {
        super(itemView);

        txt_firstname = itemView.findViewById(R.id.txt_firstname);
        txt_lastname = itemView.findViewById(R.id.txt_lastname);
        txt_phonenumber = itemView.findViewById(R.id.txt_phonenumber);
        txt_emailaddress = itemView.findViewById(R.id.txt_emailaddress);
        txt_quantity = itemView.findViewById(R.id.txt_quantity);
        txt_address = itemView.findViewById(R.id.txt_address);
        txt_option = itemView.findViewById(R.id.txt_option);

    }
}
