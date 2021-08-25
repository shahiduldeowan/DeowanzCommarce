package com.deowan.deowanzcommarce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText userNameId, emailTextID, phoneId, password, confirmPasswordId;
    private Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userNameId = findViewById(R.id.editTextSignUpUserNameId);
        emailTextID = findViewById(R.id.editTextSignUpEmailTextID);
        phoneId = findViewById(R.id.editTextSignUpPhoneId);
        password = findViewById(R.id.signUpPasswordEditTextID);
        confirmPasswordId = findViewById(R.id.EditTextConfirmSignUpPasswordId);
        signUpButton = findViewById(R.id.signUpButtonId);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpActivity.this, "Sign up button is clicked", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.signUpSignInId).setOnClickListener(view -> onBackPressed());
        findViewById(R.id.backSpaceImageViewId).setOnClickListener(view -> onBackPressed());
    }
}