// Import statements
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.example.project_fitness_app.R;


public class LocationDisplayActivity extends AppCompatActivity {

    private TextView textLatitude;
    private TextView textLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_display);

        textLatitude = findViewById(R.id.textLatitude);
        textLongitude = findViewById(R.id.textLongitude);

        // Register a BroadcastReceiver to receive location updates
        LocalBroadcastManager.getInstance(this).registerReceiver(
                locationReceiver, new IntentFilter(LocationService.LOCATION_BROADCAST_ACTION)
        );

        // Start the LocationService
        Intent locationServiceIntent = new Intent(this, LocationService.class);
        startService(locationServiceIntent);

        // Now, you can update the UI with location data when it changes
        // For simplicity, you may want to use LiveData or other mechanisms for real-time updates.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the BroadcastReceiver to avoid memory leaks
        LocalBroadcastManager.getInstance(this).unregisterReceiver(locationReceiver);
    }

    // Add a BroadcastReceiver to receive location updates
    private BroadcastReceiver locationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(LocationService.LOCATION_BROADCAST_ACTION)) {
                double latitude = intent.getDoubleExtra("latitude", 0.0);
                double longitude = intent.getDoubleExtra("longitude", 0.0);

                // Update the UI with the new location data
                updateLocationUI(latitude, longitude);
            }
        }
    };

    // Add a method to update the UI with new location data
    private void updateLocationUI(double latitude, double longitude) {
        textLatitude.setText("Latitude: " + latitude);
        textLongitude.setText("Longitude: " + longitude);
    }
}
