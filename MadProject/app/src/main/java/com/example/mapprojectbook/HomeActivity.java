package com.example.mapprojectbook;

import android.content.Intent;
<<<<<<< HEAD

import android.os.Bundle;

import android.view.MenuItem;


import androidx.annotation.NonNull;

=======
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
>>>>>>> 0b1bcbd06bb6eddba55dff7f8f9ccd21762eaf0a
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
<<<<<<< HEAD

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.google.android.material.navigation.NavigationView;

=======
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapprojectbook.databinding.ActivityHome2Binding;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
>>>>>>> 0b1bcbd06bb6eddba55dff7f8f9ccd21762eaf0a
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

<<<<<<< HEAD

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "HomeActivity";
=======
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
>>>>>>> 0b1bcbd06bb6eddba55dff7f8f9ccd21762eaf0a

    private FirebaseDatabase db;
    private List<Phone> phoneList;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHome2Binding binding;
    private DrawerLayout drawer;

    private DrawerLayout drawer;

    private String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

<<<<<<< HEAD
        Intent thisIntent = getIntent();
        email = thisIntent.getExtras().getString("email");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

=======
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

>>>>>>> 0b1bcbd06bb6eddba55dff7f8f9ccd21762eaf0a
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
<<<<<<< HEAD
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
=======
        switch(item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.nav_profile:
                startActivity((new Intent(this, ProfileActivity.class)));
>>>>>>> 0b1bcbd06bb6eddba55dff7f8f9ccd21762eaf0a
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
