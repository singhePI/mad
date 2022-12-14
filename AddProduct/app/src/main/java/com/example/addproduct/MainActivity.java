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


public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.model);
        ed3 = findViewById(R.id.detail);
        ed4 = findViewById(R.id.price);

        b1 = findViewById(R.id.bt1);
        b2 = findViewById(R.id.bt2);

        b2.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(),view.class);
            startActivity(i);
        });
        b1.setOnClickListener(v -> insert());
    }

    public void insert()
    {
        try
        {
            String name = ed1.getText().toString();
            String model = ed2.getText().toString();
            String detail = ed3.getText().toString();
            String price = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SlideDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,model VARCHAR,detail VARCHAR,price VARCHAR)");

            String sql = "insert into records(name,model,detail,price)values(?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,model);
            statement.bindString(3,detail);
            statement.bindString(4,price);
            statement.execute();
            Toast.makeText(this,"Record added",Toast.LENGTH_LONG).show();

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