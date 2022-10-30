package com.example.mapprojectbook;

import android.content.Intent;

import android.os.Bundle;

import android.view.MenuItem;


import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.google.android.material.navigation.NavigationView;

import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";

    private FirebaseDatabase db;
    private List<Phone> phoneList;

    private DrawerLayout drawer;

    private String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        Intent thisIntent = getIntent();
        email = thisIntent.getExtras().getString("email");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_phone);
        recyclerView.setLayoutManager((new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)));

        PhoneAdapter adapter = new PhoneAdapter();
        recyclerView.setAdapter(adapter);

        phoneList = new ArrayList<Phone>();
        Phone phone = new Phone("user@gmail.com", "phone", "desc", 5000.0, "new", 1);
        phoneList.add(phone);
        phoneList.add(phone);
        phoneList.add(phone);
        phoneList.add(phone);
        phoneList.add(phone);
//        db.getInstance().getReference().child("Phones").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    phoneList.clear();
//                    for (DataSnapshot ss : snapshot.getChildren()) {
//                        Phone phone = ss.getValue((Phone.class));
//                        phoneList.add(phone);
//                    }
//                    adapter.setPhones(phoneList);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        adapter.setPhones(phoneList);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.nav_home:
                intent = new Intent(this, HomeActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen((GravityCompat.START))) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
