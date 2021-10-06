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

import com.example.khalalagbeapp.Buyers.MainActivity;
import com.example.khalalagbeapp.Buyers.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verifiotp2 extends AppCompatActivity {

    private EditText input1,input2,input3,input4,input5,input6;
    private TextView textView;
    private Button button;
    private String getotpbackend;
    private ProgressBar progressBarVerifiOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifiotp2);

        button= findViewById(R.id.btnsubmit);

        input1= findViewById(R.id.input1);
        input2= findViewById(R.id.input2);
        input3= findViewById(R.id.input3);
        input4= findViewById(R.id.input4);
        input5= findViewById(R.id.input5);
        input6= findViewById(R.id.input6);

        textView= findViewById(R.id.textmobile);
        textView.setText(String.format("+88%s", getIntent().getStringExtra("mobile")));

        getotpbackend= getIntent().getStringExtra("backendotp");

        progressBarVerifiOtp= findViewById(R.id.progress_verify_otp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(!input1.getText().toString().trim().isEmpty() && !input2.getText().toString().trim().isEmpty() &&
                        !input3.getText().toString().trim().isEmpty() && !input4.getText().toString().trim().isEmpty() &&
                        !input5.getText().toString().trim().isEmpty() && !input6.getText().toString().trim().isEmpty() ){
                    String entercodeotp= input1.getText().toString() +
                            input2.getText().toString() +
                            input3.getText().toString() +
                            input4.getText().toString() +
                            input5.getText().toString() +
                            input6.getText().toString() ;


                    if(getotpbackend!=null){
                        progressBarVerifiOtp.setVisibility(v.VISIBLE);
                        button.setVisibility(v.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                                getotpbackend, entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBarVerifiOtp.setVisibility(v.GONE);
                                        button.setVisibility(v.VISIBLE);

                                        if(task.isSuccessful()){
                                            Intent intent= new Intent(getApplicationContext(), RegisterActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(verifiotp2.this, "Enter the correct OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else {
                        Toast.makeText(verifiotp2.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }


                    //Toast.makeText(verifiotp2.this, "OTO verify", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(verifiotp2.this, "Please enter all number", Toast.LENGTH_SHORT).show();
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

        TextView resendlable = findViewById(R.id.txtresndotp);
        resendlable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+88" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        verifiotp2.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(verifiotp2.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newBackendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                getotpbackend =newBackendOTP;
                                Toast.makeText(verifiotp2.this, "OTP send successfully", Toast.LENGTH_SHORT).show();



                                //super.onCodeSent(s, forceResendingToken);
                            }
                        }
                );
            }
        });

    }

    private void numberotomove() {

        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        input5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}