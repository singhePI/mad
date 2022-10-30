package com.example.mapprojectbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneHolder> {
    private List<Phone> phones = new ArrayList<>();
    int row_index;

    @NonNull
    @Override
    public PhoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.static_rv_phone, parent, false);

        return new PhoneHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneHolder holder, int position) {
        Phone currentPhone = phones.get(position);

        holder.textViewName.setText(currentPhone.getPhone_name());
        holder.textViewPrice.setText(String.valueOf(currentPhone.getPrice()));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
            }
        });

        if(row_index == position) {
            holder.linearLayout.setBackgroundResource(R.drawable.rv_selected_bg);
        } else {
            holder.linearLayout.setBackgroundResource(R.drawable.rv_static_bg);
        }
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
        notifyDataSetChanged();
    }

    static class PhoneHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewPhone;
        private TextView textViewName;
        private TextView textViewDesc;
        private TextView textViewPrice;
        LinearLayout linearLayout;

        public PhoneHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.ll_phone);
            textViewName = itemView.findViewById(R.id.rv_textview_phone);
            imageViewPhone = itemView.findViewById(R.id.rv_phone_img);
            textViewPrice = itemView.findViewById(R.id.rv_textview_price);
        }
    }
}
