package com.example.khalalagbeapp.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.khalalagbeapp.R;

public class SellerCategoryActivity extends AppCompatActivity {
    private ImageView maid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_category);




        maid = findViewById(R.id.maid1);

        maid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SellerCategoryActivity.this, SellerAddNewMaidActivity.class);
                intent.putExtra("category", "maid");
                startActivity(intent);
            }
        });
    }
}