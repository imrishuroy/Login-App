package com.sixteenbrains.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.drm.ProcessedData;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {

    TextView name;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null){
            name.setText(currentUser.getString("name"));
            email.setText(currentUser.getEmail());
        }
    }

    public  void logout(View view){
        ProgressDialog process = new ProgressDialog(this);
        process.setMessage("Loading...");
        process.show();
        ParseUser.logOut();
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        process.dismiss();

    }
}