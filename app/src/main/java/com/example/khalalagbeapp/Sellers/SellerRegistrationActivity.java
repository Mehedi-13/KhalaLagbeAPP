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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.khalalagbeapp.Buyers.MainActivity;
import com.example.khalalagbeapp.Model.Sellers;
import com.example.khalalagbeapp.Prevalent.PrevalentS;
import com.example.khalalagbeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class SellerRegistrationActivity extends AppCompatActivity {
    private Button registerButton;
    //private Button sellerLoginBegain,registerButton;
    private ImageView notImportant;
    private EditText nameInput,phoneInput,sellerpresentAddress,sellerpermanentAddress,passwordInput,sellerIdentity,workingAreas;
    private FirebaseAuth mAuth;
    private  String sellerID= "" , state="Normal";
   // private String sellerRandomKey;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration);

        sellerID = getIntent().getStringExtra("sid");

        mAuth=FirebaseAuth.getInstance();

        loadingBar= new ProgressDialog(this);

       // sellerLoginBegain= findViewById(R.id.seller_already_haveAccount);
        nameInput=findViewById(R.id.seller_name);
        phoneInput= findViewById(R.id.seller_phone);
        passwordInput= findViewById(R.id.seller_password);
        sellerpresentAddress= findViewById(R.id.seller_presentAddress);
        sellerpermanentAddress= findViewById(R.id.seller_permanentAddress);
        registerButton= findViewById(R.id.seller_register);
        notImportant=findViewById(R.id.img_seller);
        sellerIdentity=findViewById(R.id.seller_identity);
        workingAreas=findViewById(R.id.seller_working_area);



//        sellerLoginBegain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(SellerRegistrationActivity.this, SellerLoginActivity.class);
//                startActivity(intent);
//            }
//        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSeller();
            }
        });

    }

    private void registerSeller()
    {
        String name=nameInput.getText().toString();
        String phone=phoneInput.getText().toString();
        String password=passwordInput.getText().toString();
        String presentAddress=sellerpresentAddress.getText().toString();
        String permanentAddress= sellerpermanentAddress.getText().toString();
        String identity=sellerIdentity.getText().toString();
        String workingArea= workingAreas.getText().toString();

//        if(!name.equals("") && !phone.equals("") && !password.equals("") && !presentAddress.equals("") && !permanentAddress.equals("") && !identity.equals(""))
//        {
//
//
//        }
//        else
//        {
//            Toast.makeText(this, "Please complete the registration form", Toast.LENGTH_SHORT).show();
//        }


        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please write your name...",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Please write your phone number...",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please write your password...",Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(presentAddress))
        {
            Toast.makeText(this,"Please write your present address...",Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(permanentAddress))
        {
            Toast.makeText(this,"Please write your permanent address...",Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(identity))
        {
            Toast.makeText(this,"Please write your NID/Passport/BirthCertificate number...",Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(workingArea))
        {
            Toast.makeText(this,"Please write your working area...",Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber(name,phone,password,presentAddress,permanentAddress,identity,workingArea);

        }



    }

    private void ValidatephoneNumber(final String name, final String phone, final String password, final String presentAddress, final String permanentAddress, final String identity, final String workingArea) {

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String saveCurrentTime,saveCurrentDate;

                Calendar calEorDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, YYYY");
                saveCurrentDate= currentDate.format(calEorDate.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime= currentTime.format(calEorDate.getTime());

                if (!(snapshot.child("Sellers").child(phone).exists()))
                {
                   // Sellers sellers
                    HashMap<String,Object> sellerdataMap = new HashMap<>();
                    sellerdataMap.put("phone", phone);
                    sellerdataMap.put("password",password );
                    sellerdataMap.put("name", name);
                    sellerdataMap.put("presentAddress", presentAddress);
                    sellerdataMap.put("permanentAddress", permanentAddress);
                    sellerdataMap.put("identity", identity);
                    sellerdataMap.put("workingAreas", workingArea);
                    sellerdataMap.put("date",saveCurrentDate);
                    sellerdataMap.put("time",saveCurrentTime);
                    sellerdataMap.put("sid",sellerID);

                    RootRef.child("Sellers").child(phone).updateChildren(sellerdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SellerRegistrationActivity.this,"Congratulations, Your account has been created.",Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
  ////////////////////////////////////////////////////
                                        Intent intent = new Intent(SellerRegistrationActivity.this, SellerLoginActivity.class);
   //////////////////////////////////////////////////                                     /////////////////////////////////////SellerHomeActivity                                    // PrevalentS.currentOnlineUsers =
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {

                                        Toast.makeText(SellerRegistrationActivity.this,"Network Error: Please try again after some time...",Toast.LENGTH_SHORT ).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(SellerRegistrationActivity.this,"This" + phone + "already exists.",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(SellerRegistrationActivity.this,"Please try again using another Phone Number.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SellerRegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}