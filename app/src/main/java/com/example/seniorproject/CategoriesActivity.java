package com.example.seniorproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {
    Categories_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        RecyclerView rv = (RecyclerView) findViewById(R.id.Category_recView);
   adapter = new Categories_Adapter();
    rv.setAdapter(adapter);
    rv.setLayoutManager(new LinearLayoutManager(this));

    loadData();
    }

    void loadData(){
        List<Category> categories = new ArrayList<>();
        Category cat1 = new Category();
        cat1.setName("Cat1");
        cat1.setId(1);
        cat1.setImage("https://firebasestorage.googleapis.com/v0/b/seniorproject-5ec74.appspot.com/o/1.png?alt=media&token=31156913-4012-4c9c-b00b-f3378f0d9676");

        Category cat2 = new Category();
        cat2.setName("Cat2");
        cat2.setId(2);
        cat2.setImage("https://firebasestorage.googleapis.com/v0/b/seniorproject-5ec74.appspot.com/o/1.png?alt=media&token=31156913-4012-4c9c-b00b-f3378f0d9676");


        categories.add(cat1);
        categories.add(cat2);
        adapter.submitList(categories);
    }
}