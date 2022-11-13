package com.example.jobcnx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import java.util.Scanner;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class emailActivity extends AppCompatActivity {
    public String email;
    Button mButton;
    EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_email);

        mButton = (Button)findViewById(R.id.newNextButton);
        mEdit = (EditText)findViewById(R.id.newEnterText);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        email = String.valueOf(Log.v("EditText", String.valueOf(mEdit.getText())));
                        final Intent i =new Intent(emailActivity.this, fieldActivity.class);
                    }
                });
    }
}