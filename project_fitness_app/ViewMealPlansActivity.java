package com.example.project_fitness_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewMealPlansActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meal_plans);

        // Create a list of meal plans (replace this with your actual meal plans)
        ArrayList<String> mealPlans = new ArrayList<>();
        mealPlans.add("Meal Plan 1");
        mealPlans.add("Meal Plan 2");
        mealPlans.add("Meal Plan 3");

        // Create an ArrayAdapter to populate the ListView with meal plans
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mealPlans);

        // Find the ListView in the layout
        ListView listView = findViewById(R.id.listViewMealPlans);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);
    }
}
