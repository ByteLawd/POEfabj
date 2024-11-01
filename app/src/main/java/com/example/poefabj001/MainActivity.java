package com.example.poefabj001;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
// MainActivity.java
////package com.example.floatingactionbuttonservice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button launchFABButton = findViewById(R.id.launch_fab_button);
        launchFABButton.setOnClickListener(v -> {
            Toast.makeText(this, "Launching FAB Service...", Toast.LENGTH_SHORT).show();
            Log.d("MainActivity", "Launching FAB Service...");
            startService(new Intent(MainActivity.this, FloatingActionButtonService.class));
        });
    }
}}