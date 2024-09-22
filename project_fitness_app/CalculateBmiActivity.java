package com.example.project_fitness_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculateBmiActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private TextView resultTextView;
    private TextView recommendationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);

        // Initialize UI elements
        weightEditText = findViewById(R.id.editTextWeight);
        heightEditText = findViewById(R.id.editTextHeight);
        resultTextView = findViewById(R.id.textViewResult);
        recommendationTextView = findViewById(R.id.textViewRecommendation);
    }

    public void calculateBMI(View view) {
        // Get weight and height from user input
        String weightString = weightEditText.getText().toString();
        String heightString = heightEditText.getText().toString();

        if (!weightString.isEmpty() && !heightString.isEmpty()) {
            // Convert weight and height to float values
            float weight = Float.parseFloat(weightString);
            float height = Float.parseFloat(heightString);

            // Calculate BMI
            float bmi = calculateBMIValue(weight, height);

            // Display the result and recommendation
            displayResult(bmi);
        } else {
            // Handle empty input
            resultTextView.setText("Please enter weight and height");
            recommendationTextView.setText("");
        }
    }

    private float calculateBMIValue(float weight, float height) {
        // BMI formula: weight / (height * height)
        return weight / (height * height);
    }

    private void displayResult(float bmi) {
        // Display the result
        String resultText = "Your BMI is: " + bmi;
        resultTextView.setText(resultText);

        // Provide recommendations based on BMI range
        String recommendation = getRecommendation(bmi);
        recommendationTextView.setText(recommendation);
    }

    private String getRecommendation(float bmi) {
        if (bmi < 18.5) {
            return "Your BMI is underweight. Consider consulting with a healthcare professional.";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Your BMI is in the normal range. Keep up the good work!";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Your BMI is overweight. Consider adopting a healthier diet and exercise routine.";
        } else {
            return "Your BMI is in the obese range. Consult with a healthcare professional for guidance.";
        }
    }
}
