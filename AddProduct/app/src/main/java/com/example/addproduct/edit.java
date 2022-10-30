package com.example.addproduct;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class edit extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.model);
        ed3 = findViewById(R.id.detail);
        ed4 = findViewById(R.id.price);
        ed5 = findViewById(R.id.id);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);
        b3 = findViewById(R.id.bt3);


        Intent i = getIntent();

        String t1 = i.getStringExtra("id");
        String t2 = i.getStringExtra("name");
        String t3 = i.getStringExtra("model");
        String t4 = i.getStringExtra("detail");
        String t5 = i.getStringExtra("price");

        ed5.setText(t1);
        ed1.setText(t2);
        ed2.setText(t3);
        ed3.setText(t4);
        ed4.setText(t5);


        b2.setOnClickListener(v -> Delete());


        b3.setOnClickListener(v -> {

            Intent i1 = new Intent(getApplicationContext(),view.class);
            startActivity(i1);

        });




        b1.setOnClickListener(v -> Edit());

    }

    public void Delete()
    {
        try
        {
            String id = ed5.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SlideDb", Context.MODE_PRIVATE,null);


            String sql = "delete from records where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted", Toast.LENGTH_LONG).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }
    }
    public void Edit()
    {
        try
        {
            String name = ed1.getText().toString();
            String model = ed2.getText().toString();
            String detail = ed3.getText().toString();
            String price = ed4.getText().toString();
            String id = ed5.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SlideDb",Context.MODE_PRIVATE,null);


            String sql = "update records set name = ?,model=?,detail=?,price=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,model);
            statement.bindString(3,detail);
            statement.bindString(4,price);
            statement.bindString(5,id);
            statement.execute();
            Toast.makeText(this,"Record Updated",Toast.LENGTH_LONG).show();


            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }

    }

}