package com.example.khalalagbeapp.Buyers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khalalagbeapp.Model.Users;
import com.example.khalalagbeapp.Prevalent.Prevalent;
import com.example.khalalagbeapp.R;
import com.example.khalalagbeapp.Sellers.SellerRegistrationActivity;
import com.example.khalalagbeapp.entermobilenumber1;
import com.example.khalalagbeapp.sellerNewStep;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
   private Button joinNowButton, loginButton;
   private ProgressDialog loadingBar;
   private TextView sellerbegain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinNowButton= findViewById(R.id.main_join_now_btn);
        loginButton= findViewById(R.id.main_login_btn);
        sellerbegain= findViewById(R.id.seller_begain);
        loadingBar = new ProgressDialog(this);

        Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


///////////////////////////////////////////////// Intent intent = new Intent(MainActivity.this, SellerRegistrationActivity.class);
        sellerbegain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, sellerNewStep.class);
                startActivity(intent);
            }
        });

        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, entermobilenumber1.class);
                startActivity(intent);
            }
        });


        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);
        if(UserPhoneKey != "" && UserPasswordKey != "")
        {
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey))
            {
                AllowAccess(UserPhoneKey, UserPasswordKey);

                loadingBar.setTitle("Already Logged in");
                loadingBar.setMessage("Please wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void AllowAccess(final String phone, final String password)
    {

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.child("Users").child(phone).exists())
                {
                    Users usersData = snapshot. child("Users").child(phone).getValue(Users.class);
                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(MainActivity.this,"Please wait, you are already logged in...",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            Prevalent.currentOnlineUsers=usersData;
                            startActivity(intent);

                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this,"Password is incorrect.",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    loadingBar.dismiss();
                    Toast.makeText(MainActivity.this,"Account with this " + phone + " number do not exists.",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}