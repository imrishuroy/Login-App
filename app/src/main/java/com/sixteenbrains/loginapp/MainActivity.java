package com.sixteenbrains.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener,View.OnClickListener {

    EditText email , password;
    TextView signInTextView;
    ConstraintLayout layout;

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.layout){
            InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }


    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getDeviceId() == KeyEvent.ACTION_DOWN){
            Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(intent);

        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password= findViewById(R.id.password);
        signInTextView = findViewById(R.id.signInTextView);
        layout = findViewById(R.id.layout);

        password.setOnKeyListener(this);  // for advance keyboard management
        layout.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }





        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
    public void login(View view){

        if(TextUtils.isEmpty(email.getText())){
            email.setText("Email Is Required");
        }else if(TextUtils.isEmpty(password.getText())){
            password.setText("PassWord Is Required");
        }else {
            final ProgressDialog process = new ProgressDialog(this);
            process.setMessage("Loading...");
            process.show();
            ParseUser.logInInBackground(email.getText().toString(),password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    process.dismiss();
                    if (parseUser != null) {
                        Toast.makeText(MainActivity.this,"Welcome!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    public void signUp(View view){

        Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);

    }

    public  void resetPassword(View view){
        Intent intent = new Intent(MainActivity.this,ResetPassWordActivity.class);
        startActivity(intent);

    }
}