package com.example.lab1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageButton mImageButton;
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
            Log.e(ACTIVITY_NAME,"In onActivityResult");

        }
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mImageButton = findViewById(R.id.imageButtonPicture);
        mImageButton.setOnClickListener(b -> dispatchTakePictureIntent());

        Intent dataSent = getIntent();
        String emailSent = dataSent.getStringExtra("EMAIL");
        String formattedString = String.format(emailSent);
        EditText email = findViewById(R.id.editText4);
        email.setText(formattedString);
        Button gotochatButton = findViewById(R.id.buttongotochat);

        gotochatButton.setOnClickListener( v -> {
            Intent chatIntent = new Intent(ProfileActivity.this, ChatRoomActivity.class);
            startActivity(chatIntent);
        });
        Log.e(ACTIVITY_NAME,"In onCreate()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME, "In onStop()" );
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME, "In onStart()");

    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.e(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "In onResume()");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "In onDestroy()");

    }
}