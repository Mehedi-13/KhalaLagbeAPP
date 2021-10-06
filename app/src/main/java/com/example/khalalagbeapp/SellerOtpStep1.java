package com.example.khalalagbeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SellerOtpStep1 extends AppCompatActivity {

    private EditText enternumber1;
    private Button getTObtn1;
    private ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_otp_step1);


        enternumber1= findViewById(R.id.input_mobile_number1);
        getTObtn1= findViewById(R.id.btngetoto1);

        progressBar1= findViewById(R.id.progress_sending_otp1);

        getTObtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(!enternumber1.getText().toString().trim().isEmpty()){
                    if((enternumber1.getText().toString().trim()).length() ==11){

                        progressBar1.setVisibility(v.VISIBLE);
                        getTObtn1.setVisibility(v.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+88" + enternumber1.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                SellerOtpStep1.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        progressBar1.setVisibility(v.GONE);
                                        getTObtn1.setVisibility(v.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        progressBar1.setVisibility(v.GONE);
                                        getTObtn1.setVisibility(v.VISIBLE); //"Error please check internet connection"
                                        Toast.makeText(SellerOtpStep1.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        progressBar1.setVisibility(v.GONE);
                                        getTObtn1.setVisibility(v.VISIBLE);

                                        Intent intent= new Intent(getApplicationContext(),SellerOtpStep2.class);
                                        intent.putExtra("mobile",enternumber1.getText().toString());
                                        intent.putExtra("backendotp",s);
                                        startActivity(intent);

                                        //super.onCodeSent(s, forceResendingToken);
                                    }
                                }
                        );

//                        Intent intent= new Intent(getApplicationContext(),verifiotp2.class);
//                        intent.putExtra("mobile",enternumber.getText().toString());
//                        startActivity(intent);
                    } else {
                        Toast.makeText(SellerOtpStep1.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(SellerOtpStep1.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}