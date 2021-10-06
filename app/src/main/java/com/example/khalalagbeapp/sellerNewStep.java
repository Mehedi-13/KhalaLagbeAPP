package com.example.khalalagbeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.khalalagbeapp.Sellers.SellerLoginActivity;
import com.example.khalalagbeapp.Sellers.SellerRegistrationActivity;

public class sellerNewStep extends AppCompatActivity {
    private Button sellerLoginBegain,registerButton1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_new_step);

        sellerLoginBegain= findViewById(R.id.seller_already_haveAccount);
        registerButton1111= findViewById(R.id.seller_registration1111);

        sellerLoginBegain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(sellerNewStep.this, SellerLoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton1111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sellerNewStep.this, SellerOtpStep1.class);
                startActivity(intent);
            }
        });
    }
}