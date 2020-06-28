package com.sixteenbrains.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText password,email,name,confirmPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        confirmPassWord = findViewById(R.id.confirmPassword);





    }

    public  void signUp(View view){

        if(TextUtils.isEmpty(email.getText())){
            email.setText("Email Is Required");
        }else if(TextUtils.isEmpty(password.getText())) {
            password.setText("PassWord Is Required");

        }else if (TextUtils.isEmpty(name.getText())) {
            name.setText("Name Is Required");
        }else if(TextUtils.isEmpty((confirmPassWord.getText()))) {
            confirmPassWord.setText("Confirm Your Password");
        }else if(!password.getText().toString().equals(confirmPassWord.getText().toString())){
            Toast.makeText(SignUpActivity.this,"Both Password Must Be Same",Toast.LENGTH_LONG).show();

        }else {
            final ProgressDialog process = new ProgressDialog(this);
            process.setMessage("Loading...");
            process.show();
            ParseUser user = new ParseUser();
            // Set the user's username and password, which can be obtained by a forms
            user.setUsername(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.setEmail(email.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        process.dismiss();
                        Toast.makeText(SignUpActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUpActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }
}