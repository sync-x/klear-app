package com.syncx.app.Klear;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends Activity {
    Button classify;
    Button diy;
    Button donate;
    Button pickup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth=FirebaseAuth.getInstance();

        classify = (Button)findViewById(R.id.classify);
        classify.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,CameraActivity.class));
            }
        });
        diy = (Button)findViewById(R.id.diy);
        diy.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this,BlogActivity.class);
            startActivity(intent);
        });
        donate = (Button)findViewById(R.id.donate);
        donate.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSeMi6jaDEGQhE0_UIuP_2aDUQIJ5GRBQRWYvnK8FMkqZzUgiQ/viewform?usp=sf_link"));
            startActivity(browserIntent);
        });
        pickup = (Button)findViewById(R.id.pickup);
        pickup.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this,PickupActivity.class)));
    }

    public void logout(View view) {
        mAuth.signOut();
        Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MenuActivity.this, LoginActivity.class));
        finish();
    }
}
