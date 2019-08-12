package com.syncx.app.Klear;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.syncx.app.Klear.adapters.OrdersRecyclerAdapter;
import com.syncx.app.Klear.models.Orders;
import com.syncx.app.Klear.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity implements OrdersRecyclerAdapter.OnOrderListener {

    private RecyclerView mRecyclerView;
    private ArrayList<Orders> mOrders = new ArrayList<>();
    private OrdersRecyclerAdapter mOrdersRecyclerAdapter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        mAuth = FirebaseAuth.getInstance();
        mRecyclerView = findViewById(R.id.orderHistoryRecyclerview);
        
        insertNotes();
        initRecyclerView();
    }

    private void insertNotes() {
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Order Details")
                .whereEqualTo("UID", user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Orders order = new Orders();
                                order.setWaste(documentSnapshot.getString("Order"));
                                order.setCost(documentSnapshot.getString("Cost"));
                                order.setDate(documentSnapshot.getString("Date"));
                                order.setTime(documentSnapshot.getString("Time"));
                                mOrders.add(order);
                            }
                            mOrdersRecyclerAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(OrderHistoryActivity.this, "Error getting documents", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mOrdersRecyclerAdapter = new OrdersRecyclerAdapter(mOrders, this);
        mRecyclerView.setAdapter(mOrdersRecyclerAdapter);
    }

    @Override
    public void onOrderClick(int position) {
        Intent intent = new Intent(this, ViewHistoryActivity.class);
        intent.putExtra("selected_order", mOrders.get(position));
        startActivity(intent);
    }

    public void goToPickup(View view){
        startActivity(new Intent(OrderHistoryActivity.this, PickupActivity.class));
    }
}
