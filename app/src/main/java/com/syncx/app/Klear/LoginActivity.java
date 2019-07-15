package com.syncx.app.Klear;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class  LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
    }

    public void goToSignUp(View view){
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        finish();
    }

    public void logIn(View view){
        TextInputEditText loginet = findViewById(R.id.email);
        TextInputEditText passwordet = findViewById(R.id.password);
        String email = loginet.getText().toString();
        String password = passwordet.getText().toString();

        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
