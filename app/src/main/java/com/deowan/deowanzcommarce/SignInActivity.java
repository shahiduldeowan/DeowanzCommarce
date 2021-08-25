package com.deowan.deowanzcommarce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    Button signInButton;
    private EditText editTextEmailId, EditTextPasswordId;
    private TextView forgotPasswordId, createAAccountId, adminId, notAdminId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editTextEmailId = findViewById(R.id.editTextEmailId);
        EditTextPasswordId = findViewById(R.id.EditTextPasswordId);
        forgotPasswordId = findViewById(R.id.forgotPasswordId);
        createAAccountId = findViewById(R.id.createAAccountId);
        adminId = findViewById(R.id.adminId);
        notAdminId = findViewById(R.id.notAdminId);
        signInButton = findViewById(R.id.signInButtonId);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignInActivity.this, "Sign in button is click", Toast.LENGTH_LONG).show();
            }
        });


        createAAccountId.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

    }
}