package com.example.project_fitness_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTrackStepsClick(View view) {
        // Handle the "Track Steps" button click
        Intent intent = new Intent(this, TrackStepsActivity.class);
        startActivity(intent);
    }

    public void onCalculateBmiClick(View view) {
        // Handle the "Calculate BMI" button click
        Intent intent = new Intent(this, CalculateBmiActivity.class);
        startActivity(intent);
    }

    public void onTrackSleepClick(View view) {
        // Handle the "Track Sleep" button click
        Intent intent = new Intent(this, onTrackSleepClick.class);
        startActivity(intent);
    }

    public void onStressManagementClick(View view) {
        // Handle the "Stress Management" button click
        Intent intent = new Intent(this, onStressManagementClick.class);
        startActivity(intent);
    }

    public void onMealPlanningClick(View view) {
        // Handle the "Meal Planning" button click
        Intent intent = new Intent(this, onMealPlanningClick.class);
        startActivity(intent);
    }
//
//    public void startLocationService(View view) {
//        Intent intent = new Intent(this, LocationService.class);
//        startService(intent);
//    }


}
