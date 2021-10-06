package com.example.khalalagbeapp.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khalalagbeapp.Model.Sellers;
import com.example.khalalagbeapp.Prevalent.PrevalentS;
import com.example.khalalagbeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SellerLoginActivity extends AppCompatActivity {
    private Button logSellerBtn;
    private EditText phoneInput, passwordInput;
    ProgressDialog loadingBar;
    String parentDbName = "Sellers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);

        loadingBar= new ProgressDialog(this);
        phoneInput=findViewById(R.id.seller_login_phone);
        passwordInput=findViewById(R.id.seller_login_password);
        logSellerBtn=findViewById(R.id.seller_login_btn);

        logSellerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginSeller();
            }
        });
    }

    private void LoginSeller() {
        final String phone = phoneInput.getText().toString();
        final String password = passwordInput.getText().toString();

        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Please write your phone number...",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please write your password...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Seller Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            AllowAccessToAccount(phone, password);
        }
    }

    private void AllowAccessToAccount(final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.child(parentDbName).child(phone).exists())
                {
                    Sellers sellersData = snapshot. child(parentDbName).child(phone).getValue(Sellers.class);
                    if (sellersData.getPhone().equals(phone))
                    {
                        if (sellersData.getPassword().equals(password))
                        {

                             if (parentDbName.equals("Sellers"))
                            {
                                Toast.makeText(SellerLoginActivity.this,"Logged in Successfully...",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(SellerLoginActivity.this, SellerHomeActivity.class);

                                PrevalentS.currentOnlineUsers = sellersData;
                                //System.out.println(sellersData.getName()+"77777777777777777777777777777777777777");
                               // System.out.println(sellersData.getPhone()+"88888888888888888888888888888888");
                                startActivity(intent);
                            }

                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(SellerLoginActivity.this,"Password is incorrect.",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    loadingBar.dismiss();
                    Toast.makeText(SellerLoginActivity.this,"Account with this " + phone + " number do not exists.",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}