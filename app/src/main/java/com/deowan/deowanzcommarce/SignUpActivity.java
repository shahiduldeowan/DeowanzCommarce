package com.deowan.deowanzcommarce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private EditText userNameId, phoneId, password, confirmPasswordId;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userNameId = findViewById(R.id.editTextSignUpUserNameId);
        phoneId = findViewById(R.id.editTextSignUpPhoneId);
        password = findViewById(R.id.signUpPasswordEditTextID);
        confirmPasswordId = findViewById(R.id.ConfirmSignUpPasswordId);
        Button signUpButton = findViewById(R.id.signUpButtonId);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

        findViewById(R.id.signUpSignInId).setOnClickListener(view -> onBackPressed());
        findViewById(R.id.backSpaceImageViewId).setOnClickListener(view -> onBackPressed());
    }

    private void createAccount() {
        String name = userNameId.getText().toString();
        String phoneNumber = phoneId.getText().toString();
        String password1 = password.getText().toString();
        String password2 = confirmPasswordId.getText().toString();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Enter your name here...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(this, "Enter your phone number here...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password1)){
            Toast.makeText(this, "Enter your password here...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password2)){
            if (!password1.equals(password2)){
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
        }else{
            progressDialog.setTitle("Create Account");
            progressDialog.setMessage("Please wait, while we are checking the credentials.");
            progressDialog.show();

            validatePhoneNumber(name, phoneNumber, password1, password2);

        }

    }

    private void validatePhoneNumber(String name, String phone, String password1, String password2) {
        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child("phone").exists())){

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("name", name);
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password1);
                    userdataMap.put("confirmPassword", password2);

                    rootRef.child("Users").child("phone").updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }

                                }
                            });
                }else{
                    Toast.makeText(SignUpActivity.this, "This "+phone+"is already exists.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "Please try again another phone number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}