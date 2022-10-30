package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.ObjectInputStream;
import java.util.ArrayList;

public class rv extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    DBOrder dbOrder;
    RV_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        swipeRefreshLayout = findViewById(R.id.view);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        RV_Adapter adapter = new RV_Adapter(this);
        recyclerView.setAdapter(adapter);
        dbOrder = new DBOrder();
        loadData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
           public void onScrolled(@NonNull RecyclerView recyclerView, int dx,int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();

            }
        });
    }

    private void loadData() {

        ObjectInputStream.GetField dborder = null;
        assert dborder != null;

        dborder.get().addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<Order> orders = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Order order = data.getValue(Order.class);
                    orders.add(order);
                }
                adapter.setItems(orders);
                adapter.notifyDataSetChanged();


            }
            public void onCancelled (DatabaseError error){

            }
        });
    };
}