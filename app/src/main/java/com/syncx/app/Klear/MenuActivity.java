package com.syncx.app.Klear;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
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

        mAuth = FirebaseAuth.getInstance();
    }
    public void goToDIY(View view){
        startActivity(new Intent(MenuActivity.this, BlogActivity.class));
    }
    public void goToPickup(View view){
        startActivity(new Intent(MenuActivity.this, PickupActivity.class));
    }
    public void goToClassify(View view){
        startActivity(new Intent(MenuActivity.this, CameraActivity.class));
    }

    public void goToDonate(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSeMi6jaDEGQhE0_UIuP_2aDUQIJ5GRBQRWYvnK8FMkqZzUgiQ/viewform?usp=sf_link"));
        startActivity(browserIntent);
    }

    public void logout(View view) {
        mAuth.signOut();
        Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MenuActivity.this, LoginActivity.class));
        finish();
    }
}
