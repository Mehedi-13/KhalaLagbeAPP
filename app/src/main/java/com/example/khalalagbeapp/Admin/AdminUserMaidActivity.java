package com.example.khalalagbeapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khalalagbeapp.Model.Cart;
import com.example.khalalagbeapp.Model.Maids;
import com.example.khalalagbeapp.R;
import com.example.khalalagbeapp.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminUserMaidActivity extends AppCompatActivity {
    private RecyclerView MaidsList;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference cartListRef;

    private String userID="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_maid);

        userID= getIntent().getStringExtra("uid");

        MaidsList= findViewById(R.id.product_maids_list);
        MaidsList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        MaidsList.setLayoutManager(layoutManager);

        cartListRef= FirebaseDatabase.getInstance().getReference()
                .child("Cart List").child("Admin View").child(userID).child("Maids");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Cart> options=
                new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef,Cart.class)
                .build();





//        final List<Cart>options = new ArrayList<>();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Maids");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                options.clear();
//                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    Maids maids = postSnapshot.getValue(Maids.class);
//                    Cart cart = new Cart();
//                    cart.
//                    Maids newMaid = new Maids();
//                    newMaid.setPresent_address(maids.getPresent_address());
//                    newMaid.setMname(maids.getMname());
//                    newMaid.setDescription(maids.getDescription());
//                    newMaid.setType1_price(maids.getType1_price());
//                    newMaid.setType2_price(maids.getType2_price());
//                    newMaid.setType3_price(maids.getType3_price());
//                    newMaid.setImage(maids.getImage());
//
//
////                    Maids newMaid = new Maids(maids.getMname(),maids.getCategory(),maids.getDate(),maids.getDescription(),maids.getImage(),maids.getTime(),maids.getMid(),maids.getType1_price()
////                            ,maids.getType3_price(),maids.getPresent_address(),maids.getMaid_PhoneNumber(),maids.getProductState(),maids.getPresent_address());
//                    //System.out.println(trans.getFrom() + " my one");
//                    mainMaidList.add(newMaid);
//                    System.out.println(maids.getMname()+"nameeeeeeeeeeeeeeeee");
//                    //adapter = new TransListAdapter(AdminTransactionsActivity.this, AdminTransactionsActivity.this);
//                    //
//                    // rv_transac.setAdapter(adapter);
////
//                    //adapter.setData(transList);
//                    //loading.setVisibility(View.GONE);
//                }
//
//            }
//
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }





        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter= new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model)
            {
                holder.txtMemberQuantity.setText("Member Quantity : " + model.getQuantity());
                holder.txtMaidSalary.setText( model.getType() + " : " +model.getType_price() + " TK");
                holder.txtMaidName.setText(model.getMname() + " : " + model.getMid() + " , " + model.getMaid_PhoneNumber());


            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_maids_layout,parent,false);
                CartViewHolder holder= new CartViewHolder(view);
                return holder;


            }
        };

        MaidsList.setAdapter(adapter);
        adapter.startListening();

    }
}