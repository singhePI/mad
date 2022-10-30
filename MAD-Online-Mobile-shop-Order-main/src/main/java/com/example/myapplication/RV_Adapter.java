package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RV_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    ArrayList<Order> list = new ArrayList<>();
    public RV_Adapter(Context ctx){
     this.context = ctx;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OrderVH vh = (OrderVH) holder;
        Order order = list.get(position);
        vh.txt_firstname.setText(order.getFirstName());
        vh.txt_lastname.setText(order.getLastName());
        vh.txt_phonenumber.setText(order.getPhoneNumber());
        vh.txt_emailaddress.setText(order.getEmailAddress());
        vh.txt_quantity.setText(order.getQuantity());
        vh.txt_address.setText(order.getAddress());

        vh.txt_option.setOnClickListener(v->
        {
            PopupMenu popupMenu = new PopupMenu(context, vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);

            popupMenu.setOnMenuItemClickListener(item ->
            {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        Intent intent = new Intent(context, View_Order.class);
                        intent.putExtra("EDIT",order);
                        context.startActivity(intent);
                        break;
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItems(ArrayList<Order> orders) {
    }
}
