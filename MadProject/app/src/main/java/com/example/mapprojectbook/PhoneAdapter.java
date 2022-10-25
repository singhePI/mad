package com.example.mapprojectbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneHolder> {
    private List<Phone> phones = new ArrayList<>();

    @NonNull
    @Override
    public PhoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_items, parent, false);

        return new PhoneHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneHolder holder, int position) {
        Phone currentPhone = phones.get(position);
        holder.textViewName.setText(currentPhone.getPhone_name());
        holder.textViewPrice.setText(String.valueOf(currentPhone.getPrice()));
        holder.textViewDesc.setText(currentPhone.getDescription());
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
        notifyDataSetChanged();
    }

    class PhoneHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDesc;
        private TextView textViewPrice;

        public PhoneHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.phone_textview_name);
            textViewDesc = itemView.findViewById(R.id.phone_textview_description);
            textViewPrice = itemView.findViewById(R.id.phone_textview_price);
        }
    }
}
