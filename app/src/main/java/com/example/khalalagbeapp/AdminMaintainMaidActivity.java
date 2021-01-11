package com.example.khalalagbeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AdminMaintainMaidActivity extends AppCompatActivity {

    private Button applyChangeBtn, deleteBtn;
    private EditText name,salary1,salary2,salary3,description,address,phoneNumber;
    private ImageView imageView;
    private  String maidID= "";
    private DatabaseReference maidsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain_maid);

        maidID = getIntent().getStringExtra("mid");
        maidsRef= FirebaseDatabase.getInstance().getReference().child("Maids").child(maidID);

        applyChangeBtn= findViewById(R.id.apply_change_maintain);
        name= findViewById(R.id.maid_name_maintain);
        salary1= findViewById(R.id.product_maids_salary1_maintain);
        salary2= findViewById(R.id.product_maids_salary2_maintain);
        salary3= findViewById(R.id.product_maids_salary3_maintain);
        description= findViewById(R.id.product_maids_description_maintain);
        address= findViewById(R.id.product_maids_present_address_maintain);
        imageView= findViewById(R.id.product_maid_info_image_maintain);
        phoneNumber= findViewById(R.id.product_maids_phoneNumber_maintain);
        deleteBtn = findViewById(R.id.delete_maid_list);
        
        
        displaySrecificmaidInfo();

        applyChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                deleteThisMaidInfo();

            }
        });


    }

    private void deleteThisMaidInfo()
    {
        maidsRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) 
            {
                Intent intent= new Intent(AdminMaintainMaidActivity.this,AdminCategoryActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(AdminMaintainMaidActivity.this, "This Maid's Info is deleted successfully.", Toast.LENGTH_SHORT).show();

                
            }
        });
    }


    private void applyChanges()

    {
        String mName= name.getText().toString();
        String mSalary1= salary1.getText().toString();
        String mSalary2= salary2.getText().toString();
        String mSalary3= salary3.getText().toString();
        String mDescription= description.getText().toString();
        String mAddress= address.getText().toString();
        String mPhoneNumber= phoneNumber.getText().toString();


        if(mName.equals(""))
        {
            Toast.makeText(this, "Write Down Maid's Name.", Toast.LENGTH_SHORT).show();
        }
        else if(mSalary1.equals(""))
        {
            Toast.makeText(this, "Write Down Maid's Type1 Salary.", Toast.LENGTH_SHORT).show();
        }
        else if(mSalary2.equals(""))
        {
            Toast.makeText(this, "Write Down Maid's Type2 Salary.", Toast.LENGTH_SHORT).show();
        }
        else if(mSalary3.equals(""))
        {
            Toast.makeText(this, "Write Down Maid's Type3 Salary.", Toast.LENGTH_SHORT).show();
        }
        else if(mDescription.equals(""))
        {
            Toast.makeText(this, "Write Down Maid's Description.", Toast.LENGTH_SHORT).show();
        }
        else if(mAddress.equals(""))
        {
            Toast.makeText(this, "Write Down Maid's Address.", Toast.LENGTH_SHORT).show();
        }
        else if(mPhoneNumber.equals(""))
        {
            Toast.makeText(this, "Write Down Maid's Phone Number.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String, Object> MaidMap = new HashMap<>();
            MaidMap.put("mid",maidID);
            MaidMap.put("description",mDescription);
            MaidMap.put("type1_price",mSalary1);
            MaidMap.put("type2_price",mSalary2);
            MaidMap.put("type3_price",mSalary3);
            MaidMap.put("mname",mName);
            MaidMap.put("present_address",mAddress);
            MaidMap.put("maid_PhoneNumber",mPhoneNumber);

            maidsRef.updateChildren(MaidMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(AdminMaintainMaidActivity.this, "Changes applied successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(AdminMaintainMaidActivity.this,AdminCategoryActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            });

        }



    }




    private void displaySrecificmaidInfo()
    {
        maidsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    String mName= snapshot.child("mname").getValue().toString();
                    String mSalary1= snapshot.child("type1_price").getValue().toString();
                    String mSalary2= snapshot.child("type2_price").getValue().toString();
                    String mSalary3= snapshot.child("type3_price").getValue().toString();
                    String mDescription= snapshot.child("description").getValue().toString();
                    String mAddress= snapshot.child("present_address").getValue().toString();
                    String mPhoneNumber= snapshot.child("maid_PhoneNumber").getValue().toString();
                    String mImage= snapshot.child("image").getValue().toString();



                    name.setText(mName);
                    salary1.setText(mSalary1);
                    salary2.setText(mSalary2);
                    salary3.setText(mSalary3);
                    description.setText(mDescription);
                    address.setText(mAddress);
                    phoneNumber.setText(mPhoneNumber);

                    Picasso.get().load(mImage).into(imageView);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}