package com.example.khalalagbeapp.Buyers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khalalagbeapp.Model.Cart;
import com.example.khalalagbeapp.Prevalent.Prevalent;
import com.example.khalalagbeapp.R;
import com.example.khalalagbeapp.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextProcessBtn;
    private TextView txtTotalSalary ,txtMsg1;

    private int overTotalPrice = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView= findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView. setLayoutManager(layoutManager);

        NextProcessBtn = findViewById(R.id.next_process_btn);
        txtTotalSalary = findViewById(R.id.total_salary);
        txtMsg1 = findViewById(R.id.msg1);

        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtTotalSalary.setText("Total Salary : " + String.valueOf(overTotalPrice) + "TK");

                Intent intent= new Intent(CartActivity.this, ConfirmFinalHireActivity.class);
                intent.putExtra("Total Salary",String.valueOf(overTotalPrice));
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        CheckHireState();

        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options=
                new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User View")
                .child(Prevalent.currentOnlineUsers.getPhone())
                        .child("Maids"), Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final Cart model)
            {
               holder.txtMemberQuantity.setText("Member Quantity : " + model.getQuantity());
               holder.txtMaidSalary.setText( model.getType() + " : " +model.getType_price() + "  TK");
               holder.txtMaidName.setText(model.getMname());


               int oneTypeMaidSalary =((Integer.valueOf(model.getType_price()))) * Integer.valueOf(model.getQuantity());
               overTotalPrice= overTotalPrice + oneTypeMaidSalary;


               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v)
                   {
                       CharSequence options[] = new CharSequence[]
                               {
                                       "Edit",
                                       "Remove"
                               };
                       AlertDialog.Builder builder= new AlertDialog.Builder(CartActivity.this);
                       builder.setTitle("Cart Options:");

                       builder.setItems(options, new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {

                               if(i==0)
                               {
                                   Intent intent= new Intent(CartActivity.this, MaidDetailsActivity.class);
                                    intent.putExtra("mid",model.getMid());
                                    startActivity(intent);
                               }

                               if(i==1)
                               {
                                   cartListRef.child("User View")
                                           .child(Prevalent.currentOnlineUsers.getPhone())
                                           .child("Maids")
                                           .child(model.getMid())
                                           .removeValue()
                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) 
                                               {
                                                   if(task.isSuccessful())
                                                   {
                                                       Toast.makeText(CartActivity.this, "Removed Successfully.", Toast.LENGTH_SHORT).show();
                                                       Intent intent= new Intent(CartActivity.this, HomeActivity.class);

                                                       startActivity(intent);
                                                   }
                                               }
                                           });
                               }

                           }
                       });
                       builder.show();

                   }
               });

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_maids_layout,parent,false);
                CartViewHolder holder= new CartViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

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
                    String userName = snapshot.child("mname").getValue().toString();

                    if(consignmentState.equals("consignment"))
                    {
                       //txtTotalSalary.setText("Total Salary : " + overTotalPrice +"TK");
                       txtTotalSalary.setText("Dear" + userName +"\n Your selected maid is consignment successfully.");
                       recyclerView.setVisibility(View.GONE);

                       txtMsg1.setVisibility(View.VISIBLE);
                        txtMsg1.setText("Congratulation, your final selected maid has been consignment successfully. Soon you will get your selected maid.");
                       NextProcessBtn.setVisibility(View.GONE);
                        Toast.makeText(CartActivity.this, "You can hire another maid, once you get your first final selected maid.", Toast.LENGTH_SHORT).show();

                    }
                    else if (consignmentState.equals("not consignment"))
                    {

                        txtTotalSalary.setText("Consignment State = Not Consignment");
                        recyclerView.setVisibility(View.GONE);

                        txtMsg1.setVisibility(View.VISIBLE);

                        NextProcessBtn.setVisibility(View.GONE);
                        Toast.makeText(CartActivity.this, "You can hire another maid, once you get your first final selected maid.", Toast.LENGTH_SHORT).show();


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}