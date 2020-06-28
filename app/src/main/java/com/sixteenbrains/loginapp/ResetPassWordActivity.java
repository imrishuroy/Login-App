package com.sixteenbrains.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ResetPassWordActivity extends AppCompatActivity {
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_word);
        email = findViewById(R.id.email);
    }
    public  void reset(View view) {
        if (TextUtils.isEmpty(email.getText())) {
            email.setText("Email Is Required");
        } else {
            ParseUser.requestPasswordResetInBackground(email.getText().toString(), new RequestPasswordResetCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // An email was successfully sent with reset instructions.
                        Toast.makeText(ResetPassWordActivity.this,"An Email Was Successfully Sent",Toast.LENGTH_LONG).show();
                    } else {
                        // Something went wrong. Look at the ParseException to see what's up
                          Toast.makeText(ResetPassWordActivity.this,"SomeThing Went Wrong",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}