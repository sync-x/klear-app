package com.syncx.app.Klear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ProceedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed);
    }

    public void goToMenu(View view){
        startActivity(new Intent(ProceedActivity.this,MenuActivity.class));
    }
}
