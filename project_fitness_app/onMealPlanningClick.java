package com.example.project_fitness_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class onMealPlanningClick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_meal_planning_click);

        // Button to view meal plans
        Button btnViewMealPlans = findViewById(R.id.btnViewMealPlans);
        btnViewMealPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMealPlans();
            }
        });

        // Button to view recipes
        Button btnViewRecipes = findViewById(R.id.btnViewRecipes);
        btnViewRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewRecipes();
            }
        });

        // Button to add own meals
        Button btnAddOwnMeals = findViewById(R.id.btnAddOwnMeals);
        btnAddOwnMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOwnMeals();
            }
        });
    }

    private void viewMealPlans() {
        Intent intent = new Intent(this, ViewMealPlansActivity.class);
        startActivity(intent);
    }


    private void viewRecipes() {
        // Implement logic to view recipes
        Intent intent = new Intent(this, ViewRecipesActivity.class);
        startActivity(intent);    }

    private void addOwnMeals() {
        // Implement logic to allow users to add their own meals
        Intent intent = new Intent(this, AddOwnMealsActivity.class);
        startActivity(intent);    }
}
