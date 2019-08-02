package com.syncx.app.Klear;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.maps.model.LatLng;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProceedActivity extends FragmentActivity implements OnMapReadyCallback, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    Button pickdate;
    int day, month, year, hours, minute;
    int dayFinal, monthFinal, yearFinal, hoursFinal, minuteFinal;
    String dateFinal, timeFinal;
    TextView timetv, datetv;
    String waste, cost;
    GoogleMap map;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed);

        datetv = findViewById(R.id.dateTV);
        mAuth = FirebaseAuth.getInstance();

        if (getIntent().hasExtra("waste_materials")){
            waste = getIntent().getStringExtra("waste_materials");
        }
        if (getIntent().hasExtra("cost_of_waste")){
            cost = getIntent().getStringExtra("cost_of_waste");
        }

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void selectDate(View view){
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ProceedActivity.this,ProceedActivity.this,
                year, month, day);
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hours = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(ProceedActivity.this,ProceedActivity.this,
                hours, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hoursFinal = hourOfDay;
        minuteFinal = minute;
        dateFinal = dayFinal + "/" + monthFinal + "/" + yearFinal;
        timeFinal = hoursFinal + ":" + minuteFinal;
        datetv.setText("Scheduling Pickup at " + hoursFinal + ":" + minuteFinal + ", on " + dayFinal + "/" + monthFinal + "/" + yearFinal + ".");

    }

    public void goToMenu(View view){
        //startActivity(new Intent(ProceedActivity.this,MenuActivity.class));
        //Toast.makeText(this, "Pickup scheduled successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ProceedActivity.this, MenuActivity.class));
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("Order", waste);
        orderDetails.put("Cost", cost);
        orderDetails.put("Date", dateFinal);
        orderDetails.put("Time", timeFinal);
        FirebaseUser user = mAuth.getCurrentUser();
        orderDetails.put("UID", user.getUid());
        //Log.d(TAG, "goToProceed: "+ metal + "end");
        db.collection("Order Details").add(orderDetails)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(ProceedActivity.this, "Pickup scheduled successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProceedActivity.this, MenuActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProceedActivity.this, "Order not saved. Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng latLng = new LatLng(28.7043722,77.1378358);
        map.addMarker(new MarkerOptions().position(latLng).title("Rukmini Devi Public School, Pitampura"));
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
