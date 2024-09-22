package com.example.project_fitness_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StressAssessmentQuizActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private int questionIndex = 0; // Index of the current question
    private int totalScore = 0;

    // Questions and their corresponding options
    private String[][] questions = {
            {"How often do you feel stressed?", "Never", "Sometimes", "Often", "Always"},
            {"Do you have trouble sleeping due to stress?", "Never", "Sometimes", "Often", "Always"},
//            {"Are you able to effectively manage your stress levels?", "Never", "Sometimes", "Often", "Always"},
//            {"Do you engage in regular physical activity to cope with stress?", "Never", "Sometimes", "Often", "Always"},
//            {"How would you rate your work-related stress?", "Never", "Sometimes", "Often", "Always"},
//            {"Do you take breaks and practice relaxation techniques during the day to reduce stress?", "Never", "Sometimes", "Often", "Always"},
//            {"Is there a specific aspect of your life that contributes most to your stress?", "Never", "Sometimes", "Often", "Always"},
            {"Do you find it difficult to concentrate when you're stressed?", "Never", "Sometimes", "Often", "Always"},
            {"How often do you experience physical symptoms of stress (e.g., headaches, muscle tension)?", "Never", "Sometimes", "Often", "Always"},
            {"Do you feel irritable or easily angered when stressed?", "Never", "Sometimes", "Often", "Always"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_assessment_quiz);

        radioGroup = findViewById(R.id.radioGroup);

        // Display the first question
        displayQuestion();

        Button btnNextQuestion = findViewById(R.id.btnNextQuestion);
        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Process the selected answer and move to the next question
                processAnswer();
            }
        });
    }

    private void displayQuestion() {
        if (questionIndex < questions.length) {
            TextView questionTextView = findViewById(R.id.questionTextView);
            questionTextView.setText(questions[questionIndex][0]);

            // Clear previous radio button options
            radioGroup.removeAllViews();

            // Display radio button options
            for (int i = 1; i < questions[questionIndex].length; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(questions[questionIndex][i]);
                radioGroup.addView(radioButton);
            }
        } else {
            // Quiz completed, show the final recommendation
            showFinalRecommendation();
        }
    }

    private void processAnswer() {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId == -1) {
            // No option selected
            Toast.makeText(this, "Please select an option.", Toast.LENGTH_SHORT).show();
        } else {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int score = calculateScore(selectedRadioButton.getText().toString());
            totalScore += score;

            // Move to the next question
            questionIndex++;
            displayQuestion();
        }
    }

    private int calculateScore(String answer) {
        // Assign scores based on answers. Adjust these values as needed.
        switch (answer) {
            case "Never":
                return 1;
            case "Sometimes":
                return 2;
            case "Often":
                return 3;
            case "Always":
                return 4;
            default:
                return 0;
        }
    }

    private void showFinalRecommendation() {
        // Determine overall stress level based on the total score
        String recommendation;
        if (totalScore <= 5) {
            recommendation = "Low Stress";
            launchLowStressRecommendation();
        } else if (totalScore <= 10) {
            recommendation = "Moderate Stress";
            launchModerateStressRecommendation();
        } else {
            recommendation = "High Stress";
            launchHighStressRecommendation();
        }

        // Show the final recommendation
        Toast.makeText(this, "Final Recommendation: " + recommendation, Toast.LENGTH_LONG).show();
    }

    private void launchLowStressRecommendation() {
        Intent intent = new Intent(this, LowStressRecommendationActivity.class);
        startActivity(intent);
    }

    private void launchModerateStressRecommendation() {
        Intent intent = new Intent(this, ModerateStressRecommendationActivity.class);
        startActivity(intent);
    }

    private void launchHighStressRecommendation() {
        Intent intent = new Intent(this, HighStressRecommendationActivity.class);
        startActivity(intent);
    }
        // Show the final recommendation
    }
