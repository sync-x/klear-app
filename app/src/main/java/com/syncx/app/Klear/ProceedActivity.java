package com.syncx.app.Klear;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ProceedActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    Button pickdate;
    int day, month, year, hours, minute;
    int dayFinal, monthFinal, yearFinal, hoursFinal, minuteFinal;
    TextView timetv, datetv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed);

        datetv = findViewById(R.id.dateTV);
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
        datetv.setText("Scheduling Pickup at " + hoursFinal + ":" + minuteFinal + ", on " + dayFinal + "/" + monthFinal + "/" + yearFinal + ".");

    }

    public void goToMenu(View view){
        startActivity(new Intent(ProceedActivity.this,MenuActivity.class));
        Toast.makeText(this, "Pickup scheduled successfully", Toast.LENGTH_SHORT).show();
    }
}
