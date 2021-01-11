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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddNewMaidActivity extends AppCompatActivity {

    private String categoryName, Description,PresentAddress,Mname,Price1,Price2,Price3,saveCurrentDate, saveCurrentTime,PhoneNumber;
    TextView maidInfoPic;
    ImageView InputMaidInfoImage;
    EditText InputMaidName, InputMaidPresentAddress,InputMaidDescription,InputMaidPrice1,InputMaidPrice2,InputMaidPrice3,InputMaidNumber;
    Button AddNewMaidButton;
    private static final int GalleryPick =1;
    Uri ImageUri;
    String maidRandomKey,downloadImageUrl;
    StorageReference MaidInfoImagesRef;
    DatabaseReference MaidsRef;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_maid);


        categoryName = getIntent().getExtras().get("category").toString();
        MaidInfoImagesRef = FirebaseStorage.getInstance().getReference().child("Maid's Info Images");
        MaidsRef = FirebaseDatabase.getInstance().getReference().child("Maids");

        InputMaidNumber= findViewById(R.id.maid_phone_number);

        AddNewMaidButton = findViewById(R.id.add_new_maid);
        maidInfoPic = findViewById(R.id.infoPic);
        InputMaidInfoImage = findViewById(R.id.select_maid_info_image);
        InputMaidName  = findViewById(R.id.maid_name);
        InputMaidPresentAddress  = findViewById(R.id.maid_present_Address);
        InputMaidDescription  = findViewById(R.id.maid_description);
        InputMaidPrice1  = findViewById(R.id.maid_price_category1);
        InputMaidPrice2  = findViewById(R.id.maid_price_category2);
        InputMaidPrice3  = findViewById(R.id.maid_price_category3);
        loadingBar= new ProgressDialog(this);


        InputMaidInfoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                OpenGallery();
            }
        });

        AddNewMaidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ValidateMaidData();
            }
        });

    }

    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GalleryPick && resultCode==RESULT_OK && data!=null)
        {
            ImageUri = data.getData();
            InputMaidInfoImage.setImageURI(ImageUri);
        }

    }
    private void ValidateMaidData()
    {
        Description = InputMaidDescription.getText().toString();
        Mname = InputMaidName.getText().toString();
        PresentAddress = InputMaidPresentAddress.getText().toString();
        Price1= InputMaidPrice1.getText().toString();
        Price2= InputMaidPrice2.getText().toString();
        Price3= InputMaidPrice3.getText().toString();
        PhoneNumber= InputMaidNumber.getText().toString();

        if(ImageUri == null)
        {
            Toast.makeText(this,"Maid's Info image is mandatory...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Description))
        {
            Toast.makeText(this,"Please write Maid's description...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Mname))
        {
            Toast.makeText(this,"Please write Maid's name...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(PresentAddress))
        {
            Toast.makeText(this,"Please write Maid's present address...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(PhoneNumber))
        {
            Toast.makeText(this,"Please write Maid's phone number...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Price1))
        {
            Toast.makeText(this,"Please write Maid's Type1 salary...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Price2))
        {
            Toast.makeText(this,"Please write Maid's Type2 salary...",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Price3))
        {
            Toast.makeText(this,"Please write Maid's Type3 salary...",Toast.LENGTH_SHORT).show();
        }

        else
        {
            StoreMaidInformation();
        }


    }

    private void StoreMaidInformation()
    {
        loadingBar.setTitle("Add New Maid");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new maid.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, YYYY ");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        maidRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filepath = MaidInfoImagesRef.child(ImageUri.getLastPathSegment() + maidRandomKey + ".jpg");

        final UploadTask uploadTask = filepath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(AdminAddNewMaidActivity.this,"Error: " + message,Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AdminAddNewMaidActivity.this,"Maid's info Image uploaded Successfully... ",Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(AdminAddNewMaidActivity.this," got the Maid's info Image Url Successfully...",Toast.LENGTH_SHORT).show();

                            SaveMaidInfoToDatabase();

                        }
                    }
                });

            }
        });


    }

    private void SaveMaidInfoToDatabase()
    {
        HashMap<String, Object> MaidMap = new HashMap<>();
        MaidMap.put("mid",maidRandomKey);
        MaidMap.put("date",saveCurrentDate);
        MaidMap.put("time",saveCurrentTime);
        MaidMap.put("description",Description);
        MaidMap.put("image",downloadImageUrl);
        MaidMap.put("category",categoryName);
        MaidMap.put("type1_price",Price1);
        MaidMap.put("type2_price",Price2);
        MaidMap.put("type3_price",Price3);
        MaidMap.put("mname",Mname);
        MaidMap.put("present_address",PresentAddress);
        MaidMap.put("maid_PhoneNumber",PhoneNumber);

        MaidsRef.child(maidRandomKey).updateChildren(MaidMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Intent intent = new Intent(AdminAddNewMaidActivity.this, AdminCategoryActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(AdminAddNewMaidActivity.this,"Maid's info is added successfully...",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message =task.getException().toString();
                            Toast.makeText(AdminAddNewMaidActivity.this,"Error: " + message ,Toast.LENGTH_SHORT).show();

                        }
                    }
                }) ;
    }


}