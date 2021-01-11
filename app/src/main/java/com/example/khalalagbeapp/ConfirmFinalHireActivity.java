package com.example.khalalagbeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khalalagbeapp.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalHireActivity extends AppCompatActivity {
    private EditText nameEditText, phoneEditText,addressEditText,cityEditText, startWorkEditText;
    private Button confirmHireBtn;
    private String totalSalary = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_hire);

        totalSalary= getIntent().getStringExtra("Total Salary");
        Toast.makeText(this, "Total Salary " + totalSalary + "TK", Toast.LENGTH_SHORT).show();

        confirmHireBtn = findViewById(R.id.confirm_final_hire_btn);
        nameEditText = findViewById(R.id.consignment_name);
        phoneEditText = findViewById(R.id.consignment_phone_number);
        addressEditText = findViewById(R.id.consignment_address);
        cityEditText = findViewById(R.id.consignment_city);
        startWorkEditText= findViewById(R.id.consignment_working_date);


        confirmHireBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });

    }

    private void Check()
    {
        if(TextUtils.isEmpty(nameEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your full name.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phoneEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your phone number.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(startWorkEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide the date when you want to assign the made to work.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your address.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cityEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your city name.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ConfirmHire();
        }
    }

    private void ConfirmHire()
    {
        final String saveCurrentDate, saveCurrentTime;
        Calendar calEorDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, YYYY");
        saveCurrentDate= currentDate.format(calEorDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime= currentTime.format(calEorDate.getTime());

        final DatabaseReference hiresRef= FirebaseDatabase.getInstance().getReference()
                .child("Hire")
                .child(Prevalent.currentOnlineUsers.getPhone());

        HashMap<String,Object> hireMap =new HashMap<>();

        hireMap.put("totalSalary",totalSalary);
        hireMap.put("mname",nameEditText.getText().toString());
        hireMap.put("phone",phoneEditText.getText().toString());
        hireMap.put("startingDayForWork",startWorkEditText.getText().toString());
        hireMap.put("address",addressEditText.getText().toString());
        hireMap.put("city",cityEditText.getText().toString());
        hireMap.put("date",saveCurrentDate);
        hireMap.put("time",saveCurrentTime);
        hireMap.put("state","not consignment");

        hiresRef.updateChildren(hireMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference().child("Cart List")
                            .child("User View")
                            .child(Prevalent.currentOnlineUsers.getPhone())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(ConfirmFinalHireActivity.this, "Your final order(hire of maid) has been placed successfully.", Toast.LENGTH_SHORT).show();
                                        Intent intent= new Intent(ConfirmFinalHireActivity.this,HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();

                                    }

                                }
                            });
                }

            }
        });

    }
}