package com.example.khalalagbeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SellerNewHomeActivity extends AppCompatActivity {

    private TextView newHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_new_home);

        newHome= findViewById(R.id.seller_new_home);
    }
}