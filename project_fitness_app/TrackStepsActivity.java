package com.example.project_fitness_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class TrackStepsActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private boolean isWorkingOut = false;
    private int stepCount = 0;
    private TextView stepCountTextView;
    private Button startButton;
    private List<Integer> stepLogs;

    private static final String PREFS_NAME = "StepPrefs";
    private static final String STEP_COUNT_KEY = "StepCount";
    private static final String STEP_LOGS_KEY = "StepLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_steps);

        stepCountTextView = findViewById(R.id.stepCountTextView);
        startButton = findViewById(R.id.startButton);
        stepLogs = new ArrayList<>();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        // Restore step count and logs from SharedPreferences
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        stepCount = preferences.getInt(STEP_COUNT_KEY, 0);
        stepLogs = loadStepLogsFromPrefs(preferences);
        updateUI();

        startButton.setOnClickListener(v -> toggleWorkout());
    }

    private void toggleWorkout() {
        isWorkingOut = !isWorkingOut;

        if (isWorkingOut) {
            stepCount = 0;
            startButton.setText(R.string.stop_workout);
            registerSensorListener();
        } else {
            startButton.setText(R.string.start_workout);
            unregisterSensorListener();
            updateStepLogs(stepCount);
            saveStepLogsToPrefs();
            updateUI();
        }
    }

    private void updateUI() {
        stepCountTextView.setText(String.valueOf(stepCount));
        displayStepLogs();
    }

    private void updateStepLogs(int count) {
        // Add the step count to the list
        stepLogs.add(0, count);

        // Keep only the last 5 logs
        if (stepLogs.size() > 5) {
            stepLogs = stepLogs.subList(0, 5);
        }
    }

    private void displayStepLogs() {
        // Display the last 5 step logs in the TextView
        StringBuilder logs = new StringBuilder();
        for (int i = 0; i < stepLogs.size(); i++) {
            logs.append("session").append(i + 1).append(": ").append(stepLogs.get(i)).append("\n");
        }
        stepCountTextView.setText(logs.toString());
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            float magnitude = (float) Math.sqrt(x * x + y * y + z * z);

            if (magnitude > 10) {
                stepCount++;
                stepCountTextView.setText(String.valueOf(stepCount));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes if needed
    }

    private void registerSensorListener() {
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void unregisterSensorListener() {
        sensorManager.unregisterListener(this);
    }

    private void saveStepLogsToPrefs() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(STEP_COUNT_KEY, stepCount);
        saveStepLogsToPrefs(editor);
        editor.apply();
    }

    private void saveStepLogsToPrefs(SharedPreferences.Editor editor) {
        editor.putString(STEP_LOGS_KEY, TextUtils.join(",", stepLogs));
    }

    private List<Integer> loadStepLogsFromPrefs(SharedPreferences preferences) {
        String logsString = preferences.getString(STEP_LOGS_KEY, "");
        String[] logsArray = logsString.split(",");
        List<Integer> logs = new ArrayList<>();
        for (String log : logsArray) {
            try {
                logs.add(Integer.parseInt(log));
            } catch (NumberFormatException e) {
                // Handle parsing error if needed
            }
        }
        return logs;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterSensorListener();
    }
}

//package com.example.project_fitness_app;// TrackStepsActivity.java
//
//import android.content.Context;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class TrackStepsActivity extends AppCompatActivity implements SensorEventListener {
//
//    private SensorManager sensorManager;
//    private Sensor accelerometer;
//    private boolean isWorkingOut = false;
//    private int stepCount = 0;
//    private TextView stepCountTextView;
//    private Button startButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_track_steps);
//
//        stepCountTextView = findViewById(R.id.stepCountTextView);
//        startButton = findViewById(R.id.startButton);
//
//        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        if (sensorManager != null) {
//            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        }
//
//        startButton.setOnClickListener(v -> toggleWorkout());
//    }
//
//    private void toggleWorkout() {
//        isWorkingOut = !isWorkingOut;
//
//        if (isWorkingOut) {
//            stepCount = 0;
//            startButton.setText(R.string.stop_workout);
//            registerSensorListener();
//        } else {
//            startButton.setText(R.string.start_workout);
//            unregisterSensorListener();
//        }
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            float[] values = event.values;
//            float x = values[0];
//            float y = values[1];
//            float z = values[2];
//
//            float magnitude = (float) Math.sqrt(x * x + y * y + z * z);
//
//            if (magnitude > 10) {  // You might need to adjust this threshold
//                stepCount++;
//                stepCountTextView.setText(String.valueOf(stepCount));
//            }
//        }
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//        // Handle accuracy changes if needed
//    }
//
//    private void registerSensorListener() {
//        if (accelerometer != null) {
//            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
//        }
//    }
//
//    private void unregisterSensorListener() {
//        sensorManager.unregisterListener(this);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterSensorListener();
//    }
//
//}
