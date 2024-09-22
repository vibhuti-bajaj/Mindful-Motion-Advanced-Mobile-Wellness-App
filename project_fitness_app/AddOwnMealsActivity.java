package com.example.project_fitness_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddOwnMealsActivity extends AppCompatActivity {

    private ArrayList<String> addedMeals;
    private EditText etMealName;
    private EditText etMealDescription;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_own_meals);

        addedMeals = new ArrayList<>();
        etMealName = findViewById(R.id.etMealName);
        etMealDescription = findViewById(R.id.etMealDescription);

        sharedPreferences = getSharedPreferences("MyMeals", MODE_PRIVATE);

        Button btnSaveMeal = findViewById(R.id.btnSaveMeal);
        btnSaveMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMeal();
            }
        });

        Button btnViewAddedMeals = findViewById(R.id.btnViewAddedMeals);
        btnViewAddedMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAddedMeals();
            }
        });

        // Retrieve saved meals from SharedPreferences
        Set<String> savedMealsSet = sharedPreferences.getStringSet("addedMeals", new HashSet<String>());
        addedMeals.addAll(savedMealsSet);
    }

    private void saveMeal() {
        String mealName = etMealName.getText().toString().trim();
        String mealDescription = etMealDescription.getText().toString().trim();

        if (!mealName.isEmpty() && !mealDescription.isEmpty()) {
            addedMeals.add(mealName + ": " + mealDescription);
            Toast.makeText(this, "Meal added successfully", Toast.LENGTH_SHORT).show();

            // Clear input fields
            etMealName.getText().clear();
            etMealDescription.getText().clear();

            // Save the updated list of added meals to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet("addedMeals", new HashSet<>(addedMeals));
            editor.apply();
        } else {
            Toast.makeText(this, "Please enter meal details", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewAddedMeals() {
        Intent intent = new Intent(this, ViewAddedMealsActivity.class);
        intent.putStringArrayListExtra("addedMeals", addedMeals);
        startActivity(intent);
    }
}
