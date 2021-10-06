package com.example.khalalagbeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khalalagbeapp.Sellers.SellerRegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SellerOtpStep2 extends AppCompatActivity {

    private EditText input11,input21,input31,input41,input51,input61;
    private TextView textView1;
    private Button button1;
    private String getotpbackend1;
    private ProgressBar progressBarVerifiOtp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_otp_step2);


        button1= findViewById(R.id.btnsubmit1);

        input11= findViewById(R.id.input11);
        input21= findViewById(R.id.input21);
        input31= findViewById(R.id.input31);
        input41= findViewById(R.id.input41);
        input51= findViewById(R.id.input51);
        input61= findViewById(R.id.input61);

        textView1= findViewById(R.id.textmobile1);
        textView1.setText(String.format("+88%s", getIntent().getStringExtra("mobile")));

        getotpbackend1= getIntent().getStringExtra("backendotp");

        progressBarVerifiOtp1= findViewById(R.id.progress_verify_otp1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(!input11.getText().toString().trim().isEmpty() && !input21.getText().toString().trim().isEmpty() &&
                        !input31.getText().toString().trim().isEmpty() && !input41.getText().toString().trim().isEmpty() &&
                        !input51.getText().toString().trim().isEmpty() && !input61.getText().toString().trim().isEmpty() ){
                    String entercodeotp= input11.getText().toString() +
                            input21.getText().toString() +
                            input31.getText().toString() +
                            input41.getText().toString() +
                            input51.getText().toString() +
                            input61.getText().toString() ;


                    if(getotpbackend1!=null){
                        progressBarVerifiOtp1.setVisibility(v.VISIBLE);
                        button1.setVisibility(v.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                                getotpbackend1, entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBarVerifiOtp1.setVisibility(v.GONE);
                                        button1.setVisibility(v.VISIBLE);

                                        if(task.isSuccessful()){
                                            Intent intent= new Intent(getApplicationContext(), SellerRegistrationActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(SellerOtpStep2.this, "Enter the correct OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else {
                        Toast.makeText(SellerOtpStep2.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }


                    //Toast.makeText(SellerOtpStep2.this, "OTO verify", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SellerOtpStep2.this, "Please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberotomove();

//         findViewById(R.id.txtresndotp).setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//
//             }
//         });

        TextView resendlable1 = findViewById(R.id.txtresndotp1);
        resendlable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+88" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        SellerOtpStep2.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(SellerOtpStep2.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newBackendOTP , @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                getotpbackend1 =newBackendOTP;
                                Toast.makeText(SellerOtpStep2.this, "OTP send successfully", Toast.LENGTH_SHORT).show();



                                //super.onCodeSent(s, forceResendingToken);
                            }
                        }
                );
            }
        });

    }

    private void numberotomove() {

        input11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input21.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input21.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input31.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input31.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input41.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input41.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input51.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input51.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input61.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }
}