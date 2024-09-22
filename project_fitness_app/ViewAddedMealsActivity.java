package com.example.project_fitness_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewAddedMealsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_added_meals);

        ListView listViewAddedMeals = findViewById(R.id.listViewAddedMeals);

        // Get added meals from the intent
        ArrayList<String> addedMeals = getIntent().getStringArrayListExtra("addedMeals");

        // Create an ArrayAdapter to populate the ListView with added meals
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addedMeals);

        // Set the adapter to the ListView
        listViewAddedMeals.setAdapter(adapter);
    }
}
