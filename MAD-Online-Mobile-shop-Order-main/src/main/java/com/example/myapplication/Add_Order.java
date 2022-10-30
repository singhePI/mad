package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

           // initializing our edittext and button
            final EditText first_name = findViewById(R.id.firstname);
            final EditText last_name = findViewById(R.id.lastname);
            final EditText phone_number = findViewById(R.id.phonenumber);
            final EditText email_address = findViewById(R.id.emailaddress);
            final EditText quantity = findViewById(R.id.quantity);
            final EditText address = findViewById(R.id.address);

        Button add_order_button = findViewById(R.id.addOrder_button);
        Button view_order_button = findViewById(R.id.ViewOrder_button);

            view_order_button.setOnClickListener(v->
            {
                Intent intent = new Intent(this, rv.class);
                startActivity(intent);
            });

            DBOrder DBO = new DBOrder();

            Order order_edit = (Order) getIntent().getSerializableExtra("EDIT");
            if (order_edit != null){
                add_order_button.setText("UPDATE");
                first_name.setText(order_edit.getFirstName());
                last_name.setText(order_edit.getFirstName());
                phone_number.setText(order_edit.getFirstName());
                email_address.setText(order_edit.getFirstName());
                quantity.setText(order_edit.getFirstName());
                address.setText(order_edit.getFirstName());
                view_order_button.setVisibility(View.GONE);
            }
            else {
                add_order_button.setText("SUBMIT");
                view_order_button.setVisibility(View.VISIBLE);
            }

            // adding on click listener for our button.
            add_order_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    // getting text from our edittext fields.
                    Order order = new Order(first_name.getText().toString(),last_name.getText().toString(),phone_number.getText().toString(),email_address.getText().toString(),quantity.getText().toString(),address.getText().toString());

                    DBO.add(order).addOnSuccessListener(suc -> {
                        Toast.makeText(Add_Order.this, "You Made an Order", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(er ->{
                        Toast.makeText(Add_Order.this, "Can not make an Order", Toast.LENGTH_SHORT).show();
                    });
                }
            });


        }


    }
