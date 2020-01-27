package com.example.lab1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_relative);
        String undo = getString(R.string.undo);
        CheckBox checkBox;
        Button but;
        // these are the IDs from the xml files. Check them.
        but = findViewById(R.id.button);
        but.setOnClickListener( (click) ->  {
            Toast.makeText(MainActivity.this, getString(R.string.info), Toast.LENGTH_LONG).show();

        }  );
        checkBox = findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener( (compoundButton, b) -> {

            if (checkBox.isChecked()) {
                Snackbar.make(checkBox, getString(R.string.checkboxIsOn) , Snackbar.LENGTH_LONG)
                        .setAction(undo, click -> compoundButton.setChecked(!b))
                        .show();
            }
            else {
                Snackbar.make(checkBox, getString(R.string.checkboxIsOff) , Snackbar.LENGTH_LONG)
                        .setAction(undo, click -> compoundButton.setChecked(!b))
                        .show();
            }
        });
        Switch sw = findViewById(R.id.swi);
        sw.setOnCheckedChangeListener( (compoundButton, b) -> {

            if (sw.isChecked()) {
                Snackbar.make(sw, getString(R.string.switchIsOn) , Snackbar.LENGTH_LONG)
                        .setAction(undo, click -> compoundButton.setChecked(!b))
                        .show();
            }
            else {
                Snackbar.make(sw, getString(R.string.switchIsOff) , Snackbar.LENGTH_LONG)
                        .setAction(undo, click -> compoundButton.setChecked(!b))
                        .show();
            }
        });
    }
}