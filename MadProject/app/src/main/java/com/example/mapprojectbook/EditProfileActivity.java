package com.example.mapprojectbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class EditProfileActivity extends AppCompatActivity {
    EditText username, fname, lname, email, password, address, contactNo;
    Button button;
    String userEmail;
    Intent intent;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initViews();
        intent = getIntent();

        button.setText("Update");

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {


            }
        });

    }

    private void populateUI(User user) {

        if (user == null) {
            return;
        }
        username.setText((user.getUsername()));
        fname.setText(user.getFname());
        lname.setText(user.getLname());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        contactNo.setText(user.getContactNo());
        address.setText(user.getAddress());
    }

    private void initViews() {
        username = findViewById(R.id.edit_username);
        fname = findViewById(R.id.edit_fname);
        lname = findViewById(R.id.edit_lname);
        password = findViewById(R.id.edit_pass);
        email = findViewById(R.id.edit_email);
        address = findViewById(R.id.edit_address);
        contactNo = findViewById(R.id.edit_contactNo);

        button = findViewById(R.id.save_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked() {
        User user = new User(
                email.getText().toString(),
                username.getText().toString(),
                fname.getText().toString(),
                lname.getText().toString(),
                password.getText().toString(),
                address.getText().toString(),
                contactNo.getText().toString()
        );

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

}