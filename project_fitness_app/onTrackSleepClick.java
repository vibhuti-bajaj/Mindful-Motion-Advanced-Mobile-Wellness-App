package com.example.project_fitness_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class onTrackSleepClick extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private boolean isSleeping = false;
    private long sleepStartTime = 0;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_track_sleep_click);

        // Initialize sensor manager and accelerometer
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("SleepData", Context.MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the accelerometer sensor listener
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the accelerometer sensor listener to save power
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for this example
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Handle accelerometer sensor data
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // Calculate the magnitude of acceleration
        double magnitude = Math.sqrt(x * x + y * y + z * z);

        // Adjust the threshold based on your requirements
        double threshold = 1.5;

        // Check if the device is relatively motionless (indicating potential sleep)
        if (magnitude < threshold) {
            if (!isSleeping) {
                // Detected potential sleep
                isSleeping = true;
                sleepStartTime = System.currentTimeMillis();
                Log.d("SleepTracking", "Sleep started at: " + sleepStartTime);
            }
        } else {
            // Device is in motion, indicating wakefulness
            if (isSleeping) {
                // Detected end of sleep
                isSleeping = false;
                long sleepEndTime = System.currentTimeMillis();
                Log.d("SleepTracking", "Sleep ended at: " + sleepEndTime);
                long sleepDuration = sleepEndTime - sleepStartTime;
                Log.d("SleepTracking", "Sleep duration: " + sleepDuration + " milliseconds");

                // Save sleep information using SharedPreferences
                saveSleepData(sleepStartTime, sleepEndTime, sleepDuration);

                // Update TextViews with sleep information
                updateSleepInformationViews(sleepStartTime, sleepEndTime, sleepDuration);
            }
        }
    }
    private void updateSleepInformationViews(long startTime, long endTime, long duration) {
        TextView textViewSleepStart = findViewById(R.id.textViewSleepStart);
        TextView textViewSleepEnd = findViewById(R.id.textViewSleepEnd);
        TextView textViewSleepDuration = findViewById(R.id.textViewSleepDuration);

        // Format the time for display
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedStartTime = dateFormat.format(new Date(startTime));
        String formattedEndTime = dateFormat.format(new Date(endTime));

        // Update TextViews
        textViewSleepStart.setText("Sleep Start: " + formattedStartTime);
        textViewSleepEnd.setText("Sleep End: " + formattedEndTime);
        textViewSleepDuration.setText("Sleep Duration: " + duration + " milliseconds");
    }

    private void saveSleepData(long startTime, long endTime, long duration) {
        // Retrieve existing sleep data from SharedPreferences
        String previousData = sharedPreferences.getString("sleep_data", "");

        // Append the new sleep data
        String newData = previousData + "Start=" + startTime + ", End=" + endTime + ", Duration=" + duration + "\n";

        // Save the updated sleep data to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sleep_data", newData);
        editor.apply();
    }
}
