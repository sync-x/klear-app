package com.syncx.app.Klear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class  LoginActivity extends Activity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private ImageView Klear;
    private TextView tvInfo;
    private int counter =5;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btLogin);
        Klear = (ImageView) findViewById(R.id.image);
        tvInfo = (TextView) findViewById(R.id.tvInfo);

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        if (user!=null){
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
            finish();
        }
    }

    public void signUp(View view){
        String name=Name.getText().toString();
        String password=Password.getText().toString();
        mAuth.createUserWithEmailAndPassword(name, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user=mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(View view){
        String name=Name.getText().toString();
        String password=Password.getText().toString();
        mAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user=mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    counter--;
                    tvInfo.setText("No. of attempts remaining: " + String.valueOf(counter));

                    if(counter ==0){
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }


        /* Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void validate (String userName, String userPassword){
        if(userName.equals("Admin") && userPassword.equals("1234")){
            Intent intent = new Intent (LoginActivity.this,MenuActivity.class);
            startActivity(intent);
        }else{
            counter--;

            tvInfo.setText("No. of attempts remaining: " + String.valueOf(counter));

            if(counter ==0){
                Login.setEnabled(false);
            }
        }
    } */
}
