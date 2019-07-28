package com.syncx.app.Klear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.TextView;

import com.syncx.app.Klear.models.Orders;

public class ViewHistoryActivity extends AppCompatActivity {
    TextView wasteText;
    TextView costText;
    TextView dateText;
    TextView timeText;
    private Orders mOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        wasteText = findViewById(R.id.text_waste);
        costText = findViewById(R.id.text_cost);
        dateText = findViewById(R.id.text_date);
        timeText = findViewById(R.id.text_time);

        if (getIntent().hasExtra("selected_order")){
            mOrders = getIntent().getParcelableExtra("selected_order");
            setNoteProperties();
        }
    }

    private void setNoteProperties() {
        wasteText.setText("Waste: " + mOrders.getWaste());
        costText.setText("Cost: " + mOrders.getCost());
        dateText.setText("Date: " + mOrders.getDate());
        timeText.setText("Time: "+ mOrders.getTime());
    }
}
