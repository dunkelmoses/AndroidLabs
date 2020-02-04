package com.example.lab1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);

        prefs = getSharedPreferences("FileName", Context.MODE_PRIVATE);
        String savedString = prefs.getString("ReserveName", "");

        EditText typeField = findViewById(R.id.editTextEmail);
        typeField.setText(savedString);
        Button saveButton = findViewById(R.id.login);

        saveButton.setOnClickListener( v -> {
            Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
            String emailTyped = typeField.getText().toString();
            goToProfile.putExtra("EMAIL", emailTyped);
            saveSharedPrefs( emailTyped );
            startActivity(goToProfile);
        });

    }

    private void saveSharedPrefs(String stringToSave)
    {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ReserveName", stringToSave);
        editor.apply();
    }
}