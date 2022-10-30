package com.example.myapplication;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DBOrder {

    private DatabaseReference databaseReference;

    public DBOrder() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Order.class.getSimpleName());
    }

    public Task<Void> add(Order order) {
        return databaseReference.push().setValue(order);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove (String key) {
        return databaseReference.child(key).removeValue();
    }

    public Query get() {
        return databaseReference.orderByKey();
    }
}

