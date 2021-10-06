package com.example.khalalagbeapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.khalalagbeapp.Buyers.HomeActivity;
import com.example.khalalagbeapp.Buyers.MainActivity;
import com.example.khalalagbeapp.R;
import com.example.khalalagbeapp.Sellers.SellerAddNewMaidActivity;
import com.example.khalalagbeapp.Sellers.SellerCategoryActivity;

public class AdminHomeActivity extends AppCompatActivity {

    private ImageView maid;
    private Button LogoutBtn, CheckOrderBtn, maintainMaidsBtn , checkApporveMaidBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        LogoutBtn= findViewById(R.id.admin_logout_Btn);
        CheckOrderBtn= findViewById(R.id.check_order_Btn);
        maintainMaidsBtn= findViewById(R.id.maintain_Btn);
        checkApporveMaidBtn= findViewById(R.id.check_approve_order_Btn);

        maid = findViewById(R.id.maid);

        maid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAddNewMaidActivity.class);
                intent.putExtra("category", "maid");
                startActivity(intent);
            }
        });


        maintainMaidsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(AdminHomeActivity.this, HomeActivity.class);
                intent.putExtra("Admin","Admin");
                startActivity(intent);

            }
        });



        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(AdminHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        CheckOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(AdminHomeActivity.this, AdminNewOrdersActivity.class);

                startActivity(intent);

            }
        });

        checkApporveMaidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent= new Intent(AdminHomeActivity.this, CheckNewMaidsActivity.class);

                startActivity(intent);

            }
        });

    }
}