package com.example.khalalagbeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khalalagbeapp.Prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Properties;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity
{
    private CircleImageView profileImageView;
    private EditText fullNameEditText, userPhoneEditText, addressEditText,
            genderEditText,emailEditText,identityEditText;
    private TextView profileChangeTextBtn,closeTextBtn,saveTextButton;
    private Button securityQuestionBtn;

    private Uri imageUri;
    private String myUrl= "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePictureRef;
    private String checker = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("Profile pictures");

        profileImageView= findViewById(R.id.settings_profile_image);
        fullNameEditText= findViewById(R.id.settings_full_Name);
        userPhoneEditText= findViewById(R.id.settings_phone_number);
        addressEditText= findViewById(R.id.settings_address);
        genderEditText= findViewById(R.id.settings_gender_address);
        emailEditText= findViewById(R.id.settings_email_address);
        identityEditText= findViewById(R.id.settings_identify_address);
        profileChangeTextBtn= findViewById(R.id.profile_image_change_btn);
        closeTextBtn= findViewById(R.id.close_settings_btn);
        saveTextButton= findViewById(R.id.update_account_setting);
        securityQuestionBtn= findViewById(R.id.security_question_btn);

        userInfoDisplay(profileImageView,fullNameEditText,userPhoneEditText,addressEditText,genderEditText,emailEditText,identityEditText);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        securityQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SettingsActivity.this,ResetPasswordActivity.class);
                intent.putExtra("check", "settings");
                startActivity(intent);
            }
        });


        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checker.equals("clicked"))
                {
                    userInfoSaved();
                }
                else
                {
                    updateOnlyUserInfo();
                }
            }
        });

        profileChangeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker= "clicked";

                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(SettingsActivity.this);

            }
        });


    }

    private void updateOnlyUserInfo()
    {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users");
        HashMap<String,Object> userMap = new HashMap<>();
        userMap. put("name",fullNameEditText.getText().toString());
        userMap. put("address",addressEditText.getText().toString());
        userMap. put("PhoneOrder",userPhoneEditText.getText().toString());
        userMap. put("gender",genderEditText.getText().toString());
        userMap. put("email",emailEditText.getText().toString());
        userMap. put("identityNo",identityEditText.getText().toString());
        ref.child(Prevalent.currentOnlineUsers.getPhone()).updateChildren(userMap);


        startActivity(new Intent(SettingsActivity.this,HomeActivity.class));
        Toast.makeText(SettingsActivity.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null)
        {
            CropImage.ActivityResult result= CropImage.getActivityResult(data);
            imageUri = result.getUri();

            //Toast.makeText(this, "imageUri"+ imageUri, Toast.LENGTH_SHORT).show();
            profileImageView.setImageURI(imageUri);



        }
        else {
            Toast.makeText(this,"Error, Try Again.",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
            finish();
        }


    }

    private void userInfoSaved()
    {
        if(TextUtils.isEmpty(fullNameEditText.getText().toString()))
        {
            Toast.makeText(this,"Name is mandatory.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(userPhoneEditText.getText().toString()))
        {
            Toast.makeText(this,"Phone Number is mandatory.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this,"Address is mandatory.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(genderEditText.getText().toString()))
        {
            Toast.makeText(this,"Gender is mandatory.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(emailEditText.getText().toString()))
        {
            Toast.makeText(this,"Email is mandatory.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(identityEditText.getText().toString()))
        {
            Toast.makeText(this,"Identification Number is mandatory.",Toast.LENGTH_SHORT).show();
        }
        else if (checker.equals("clicked"))
        {
            uploadImage();
        }


    }

    private void uploadImage()
    {
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Update Profile");
        progressDialog.setMessage("Please wait, while we are updating your account information");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        if(imageUri != null)
        {
            final StorageReference fileRef = storageProfilePictureRef
                    .child(Prevalent.currentOnlineUsers.getPhone() + ".jpg");
            uploadTask= fileRef.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception
                {
                    if(!task.isSuccessful())
                    {
                        throw task.getException();
                    }

                    return fileRef.getDownloadUrl();
                }
            })
            .addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful())
                    {
                        Uri downloadUrl = task.getResult();
                        myUrl= downloadUrl.toString();
                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users");
                        HashMap<String,Object> userMap = new HashMap<>();
                        userMap. put("name",fullNameEditText.getText().toString());
                        userMap. put("address",addressEditText.getText().toString());
                        userMap. put("PhoneOrder",userPhoneEditText.getText().toString());
                        userMap. put("gender",genderEditText.getText().toString());
                        userMap. put("email",emailEditText.getText().toString());
                        userMap. put("identityNo",identityEditText.getText().toString());
                        userMap.put("image", myUrl);
                        ref.child(Prevalent.currentOnlineUsers.getPhone()).updateChildren(userMap);

                        progressDialog.dismiss();

                        startActivity(new Intent(SettingsActivity.this,HomeActivity.class));
                        Toast.makeText(SettingsActivity.this, "Profile Info update successfully.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SettingsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            })
            ;
        }
        else
        {
            Toast.makeText(this, "Image isn't selected.", Toast.LENGTH_SHORT).show();
        }


    }

    private void userInfoDisplay(final CircleImageView profileImageView, final EditText fullNameEditText, final EditText userPhoneEditText, final EditText addressEditText, final EditText genderEditText, final EditText emailEditText, final EditText identityEditText)
    {
        DatabaseReference UsersRef= FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUsers.getPhone());
        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    if(snapshot.child("image").exists())
                    {
                        String image= snapshot.child("image").getValue().toString();
                        String name= snapshot.child("name").getValue().toString();
                        String phone= snapshot.child("phone").getValue().toString();
                        String address= snapshot.child("address").getValue().toString();
                        String gender= snapshot.child("gender").getValue().toString();
                        String email= snapshot.child("email").getValue().toString();
                        String identityNo= snapshot.child("identityNo").getValue().toString();


                        Picasso.get().load(image).into(profileImageView);
                        fullNameEditText.setText(name);
                        userPhoneEditText.setText(phone);
                        addressEditText.setText(address);
                        genderEditText.setText(gender);
                        emailEditText.setText(email);
                        identityEditText.setText(identityNo);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}