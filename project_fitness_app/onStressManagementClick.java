package com.example.project_fitness_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class onStressManagementClick extends AppCompatActivity {

    private String[] affirmations = {
            "You are strong and capable.",
            "Every day is a new opportunity for success.",
            "Your potential is limitless.",
            "Challenges are opportunities for growth.",
            "You have the power to create change in your life.",
            "Success is the result of your persistent efforts.",
            "You attract positive energy into your life.",
            "You are deserving of all the good things life has to offer.",
            "Confidence is your natural state of being.",
            "Your mind is full of brilliant ideas.",
            "You radiate positivity and kindness.",
            "Your dreams are achievable with dedication and hard work.",
            "You are resilient in the face of adversity.",
            "Each step you take brings you closer to your goals.",
            "You are a magnet for success and prosperity.",
            "Your attitude determines your direction.",
            "You are a beacon of light and inspiration.",
            "You let go of what you can't control and focus on what you can.",
            "You are becoming the best version of yourself.",
            "Your journey is unique and valuable.",

            // Add more affirmations as needed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_stress_management_click);

        // Display daily affirmation
        TextView textViewAffirmation = findViewById(R.id.textViewAffirmation);
        String randomAffirmation = getRandomAffirmation();
        textViewAffirmation.setText("Today's Affirmation: " + randomAffirmation);

        // Button to go to the Stress Assessment Quiz
        Button btnGoToQuiz = findViewById(R.id.btnGoToQuiz);
        btnGoToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStressAssessmentQuiz();
            }
        });
    }

    private String getRandomAffirmation() {
        Random random = new Random();
        int index = random.nextInt(affirmations.length);
        return affirmations[index];
    }

    private void goToStressAssessmentQuiz() {
        Intent intent = new Intent(this, StressAssessmentQuizActivity.class);
        startActivity(intent);
    }
}
