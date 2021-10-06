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

public class entermobilenumber1 extends AppCompatActivity {

    private EditText enternumber;
    private Button getTObtn;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entermobilenumber1);
        enternumber= findViewById(R.id.input_mobile_number);
        getTObtn= findViewById(R.id.btngetoto);

        progressBar= findViewById(R.id.progress_sending_otp);

        getTObtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(!enternumber.getText().toString().trim().isEmpty()){
                    if((enternumber.getText().toString().trim()).length() ==11){

                        progressBar.setVisibility(v.VISIBLE);
                        getTObtn.setVisibility(v.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+88" + enternumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                entermobilenumber1.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        progressBar.setVisibility(v.GONE);
                                        getTObtn.setVisibility(v.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        progressBar.setVisibility(v.GONE);
                                        getTObtn.setVisibility(v.VISIBLE); //"Error please check internet connection"
                                        Toast.makeText(entermobilenumber1.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        progressBar.setVisibility(v.GONE);
                                        getTObtn.setVisibility(v.VISIBLE);

                                        Intent intent= new Intent(getApplicationContext(),verifiotp2.class);
                                        intent.putExtra("mobile",enternumber.getText().toString());
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
                        Toast.makeText(entermobilenumber1.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(entermobilenumber1.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}