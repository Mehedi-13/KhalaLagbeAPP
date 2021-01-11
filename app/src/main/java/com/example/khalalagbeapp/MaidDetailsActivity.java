 package com.example.khalalagbeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.khalalagbeapp.Model.Maids;
import com.example.khalalagbeapp.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

 public class MaidDetailsActivity extends AppCompatActivity {


    private ImageView maidImage;
    private ElegantNumberButton numberButton;
    private TextView maidsSalary,maidsSalary2,maidsSalary3,maidDescription,maidName, maidPresentAddress;
    private  String maidID= "" , state="Normal";
    private String selectedSalaryType =  "";
    private Button addToCartButton;
    private  String SelectedItemSalary = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_details);

        maidID = getIntent().getStringExtra("mid");



        addToCartButton= findViewById(R.id.maid_add_to_cart_button);
        numberButton= findViewById(R.id.number_btn);
        maidImage= findViewById(R.id.maid_image_details);
        maidsSalary = findViewById(R.id.maids_salary_details);
        maidsSalary2 = findViewById(R.id.maids_salary2_details);
        maidsSalary3 = findViewById(R.id.maids_salary3_details);
        maidDescription = findViewById(R.id.maids_description_details);
        maidName = findViewById(R.id.maids_name_details);
        maidPresentAddress = findViewById(R.id.maids_present_address_details);

        getMaidDetails(maidID);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(state.equals("Maid Consignment") || state.equals("Maid Placed") )
                {
                    Toast.makeText(MaidDetailsActivity.this, "You can hire another maid, once you fire your first selected maid.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    addingToCartList();
                }

            }
        });


    }


     @Override
     protected void onStart() {
         super.onStart();
         CheckHireState();
     }

     private void addingToCartList()
     {
         String saveCurrentTime,saveCurrentDate;

         Calendar calEorDate = Calendar.getInstance();
         SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, YYYY");
         saveCurrentDate= currentDate.format(calEorDate.getTime());

         SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
         saveCurrentTime= currentDate.format(calEorDate.getTime());

        final  DatabaseReference cartListRef =FirebaseDatabase.getInstance().getReference().child("Cart List");
         final HashMap<String,Object> cartMap = new HashMap<>();

         cartMap.put("mid",maidID);
         cartMap.put("mname",maidName.getText().toString());
         cartMap.put ("type",selectedSalaryType);
         cartMap.put ("type_price",SelectedItemSalary);
         cartMap.put("date",saveCurrentDate);
         cartMap.put("time",saveCurrentTime);
         cartMap.put("quantity",numberButton.getNumber());

         cartListRef.child("User View").child(Prevalent.currentOnlineUsers.getPhone())
                 .child("Maids").child(maidID)
                 .updateChildren(cartMap)
                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task)
                     {
                        if(task.isSuccessful())
                        {
                            cartListRef.child("Admin View").child(Prevalent.currentOnlineUsers.getPhone())
                                    .child("Maids").child(maidID)
                                    .updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if (task.isSuccessful())
                                            {
                                                Toast.makeText(MaidDetailsActivity.this, "Added to Cart List.", Toast.LENGTH_SHORT).show();

                                                Intent intent= new Intent(MaidDetailsActivity.this,HomeActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }
                     }
                 });

     }





     private void getMaidDetails(String maidID)
     {
         DatabaseReference maidsRef= FirebaseDatabase.getInstance().getReference().child("Maids");

         maidsRef.child(maidID).addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot)
             {
                 if (snapshot.exists())
                 {
                     final Maids maids= snapshot.getValue(Maids.class);

                     maidName.setText(maids.getMname());
                     maidPresentAddress.setText(maids.getPresent_address());
                     maidsSalary.setText(" Type-1 Salary:" + maids.getType1_price() +"TK");
                     maidsSalary2.setText("Type-2 Salary:" + maids.getType2_price()+"TK");
                     maidsSalary3.setText("Type-3 Salary:" + maids.getType3_price()+"TK ");
                     maidDescription.setText(maids.getDescription());

                     Picasso.get().load(maids.getImage()).into(maidImage);

                     maidsSalary.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             //Toast.makeText(MaidDetailsActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                             maidsSalary.setTextColor(getResources().getColor(R.color.colorAccent) );

                             SelectedItemSalary= maids.getType1_price();

                             selectedSalaryType = "Type-1_Salary";

                         }
                     });

                     maidsSalary2.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             //Toast.makeText(MaidDetailsActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                             maidsSalary2.setTextColor(getResources().getColor(R.color.colorAccent) );

                             SelectedItemSalary= maids.getType2_price();

                             selectedSalaryType = "Type-2_Salary";

                         }
                     });

                     maidsSalary3.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             //Toast.makeText(MaidDetailsActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                             maidsSalary3.setTextColor(getResources().getColor(R.color.colorAccent) );

                             SelectedItemSalary= maids.getType3_price();

                             selectedSalaryType = "Type-3_Salary";

                         }
                     });





                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

     }


     private void CheckHireState()
     {
         DatabaseReference hireRef;
         hireRef=FirebaseDatabase.getInstance().getReference().child("Hire").child(Prevalent.currentOnlineUsers.getPhone());

         hireRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot)
             {
                 if(snapshot.exists())
                 {
                     String consignmentState = snapshot.child("state").getValue().toString();


                     if(consignmentState.equals("consignment"))
                     {
                        state= "Maid Consignment";
                     }
                     else if (consignmentState.equals("not consignment"))
                     {
                         state= "Maid Placed";
                     }
                 }

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
     }

 }