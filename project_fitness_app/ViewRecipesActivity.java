package com.example.project_fitness_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipes);

        // Create a list of recipes (replace this with your actual recipes)
        ArrayList<String> recipes = new ArrayList<>();
        recipes.add("Recipe 1");
        recipes.add("Recipe 2");
        recipes.add("Recipe 3");

        // Create an ArrayAdapter to populate the ListView with recipes
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipes);

        // Find the ListView in the layout
        ListView listView = findViewById(R.id.listViewRecipes);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);
    }
}
