package com.example.addproduct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class view extends AppCompatActivity {


    ListView lst1;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("SlideDb",Context.MODE_PRIVATE,null);

        lst1 = findViewById(R.id.lst1);
        @SuppressLint("Recycle") final Cursor c = db.rawQuery("select * from records",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int model = c.getColumnIndex("model");
        int detail = c.getColumnIndex("detail");
        int price= c.getColumnIndex("price");
        titles.clear();


        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final  ArrayList<student> stud = new ArrayList<>();


        if(c.moveToFirst())
        {
            do{
                student stu = new student();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.model = c.getString(model);
                stu.detail = c.getString(detail);
                stu.price = c.getString(price);
                stud.add(stu);

                titles.add(c.getString(id) + " \t " + c.getString(name) + " \t "  + c.getString(model) + " \t "  + c.getString(detail) + c.getString(price) + " \t ");

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();



        }

        lst1.setOnItemClickListener((parent, view, position, id1) -> {
            student stu = stud.get(position);
            Intent i = new Intent(getApplicationContext(),edit.class);
            i.putExtra("id",stu.id);
            i.putExtra("name",stu.name);
            i.putExtra("course",stu.model);
            i.putExtra("fee",stu.detail);
            i.putExtra("fee",stu.price);
            startActivity(i);

        });

    }
}