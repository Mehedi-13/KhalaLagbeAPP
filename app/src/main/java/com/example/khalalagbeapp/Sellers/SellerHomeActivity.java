package com.example.khalalagbeapp.Sellers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khalalagbeapp.Admin.CheckNewMaidsActivity;
import com.example.khalalagbeapp.Buyers.MainActivity;
import com.example.khalalagbeapp.Model.Maids;
import com.example.khalalagbeapp.Prevalent.PrevalentS;
import com.example.khalalagbeapp.R;
import com.example.khalalagbeapp.SellerNewHomeActivity;
import com.example.khalalagbeapp.ViewHolder.ItemViewHolder;
import com.example.khalalagbeapp.ViewHolder.MaidsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import io.paperdb.Paper;

public class  SellerHomeActivity extends AppCompatActivity {
    private String type= "";
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference unverifiedMaidsRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);

        unverifiedMaidsRef= FirebaseDatabase.getInstance().getReference().child("Maids");

        recyclerView= findViewById(R.id.seller_home_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //navView.setSelectedItemId(R.id.navigation_home);
       // navView.setSelectedItemId(R.id.navigation_add);
        //navView.setSelectedItemId(R.id.navigation_logout);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_add, R.id.navigation_logout)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //equalTo(PrevalentS.currentOnlineUsers.getPhone()),Maids.class).build();
        //equalTo(FirebaseAuth.getInstance().getUid()),Maids.class).build();
        FirebaseRecyclerOptions<Maids> options=
                new FirebaseRecyclerOptions.Builder<Maids>()
                        .setQuery(unverifiedMaidsRef.orderByChild("sellerPhone").equalTo(PrevalentS.currentOnlineUsers.getPhone()),Maids.class).build();


        FirebaseRecyclerAdapter<Maids, ItemViewHolder> adapter
                = new FirebaseRecyclerAdapter<Maids, ItemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull final Maids model) {

                holder.txtMaidsName.setText(model.getMname());
                holder.productState.setText("State : " + model.getProductState());
                holder.txtMaidsDescription.setText(model.getDescription());
                holder.txtMaidPresentAddress.setText(model.getPresent_address());
                holder.txtMaidPrice1.setText("Type1= "+ model.getType1_price() + "TK");
                Log.v("price", "" + model.getType1_price());
                holder.txtMaidPrice2.setText("Type2= "+ model.getType2_price() + "TK");
                holder.txtMaidPrice3.setText("Type3= "+model.getType3_price()+ "TK");
                Picasso.get().load(model.getImage()).into(holder.imageView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String maidId = model.getMid();
                        CharSequence options [] = new CharSequence[]
                                {
                                        "Yes",
                                        "No"
                                };
                        AlertDialog.Builder builder=new AlertDialog.Builder(SellerHomeActivity.this);
                        builder.setTitle("Do you want to Delete this Maid's request. Are you sure? ");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {

                                if(position==0)
                                {
                                    deleteProduct (maidId);
                                }
                                if(position==1)
                                {

                                }
//                                if(position==2)
//                                {
//                                      hide();
//                                }
                            }
                        });
                        builder.show();
                    }
                });

            }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_maid_view,parent,false);
                ItemViewHolder holder= new ItemViewHolder(view);
                return  holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }




//    private void hide(String maidId){
//        HashMap <String, boolean> newMap = new HashMap<String, boolean>();
//        newMap.put("hide",false);
//        unverifiedMaidsRef.child(maidId).child("hide").updateChildren(newMap);
//    }




    //unverifiedMaidsRef.child(maidId).child("productState").removeValue()
    private void deleteProduct(String maidId) {
        unverifiedMaidsRef.child(maidId).removeValue()

                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(SellerHomeActivity.this, "Your profile has been deleted successfully.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private TextView mTextView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.navigation_home:
                          //  mTextView.setText(R.string.title_home);
                           // Intent intent2 = new Intent(SellerHomeActivity.this, SellerHomeActivity.class);
                            Intent intent2 = new Intent(SellerHomeActivity.this, SellerNewHomeActivity.class);
                            startActivity(intent2);
                            return true;

                        case R.id.navigation_add:
                            Intent intent1 = new Intent(SellerHomeActivity.this, SellerCategoryActivity.class);
                            startActivity(intent1);
                            return true;
                        case R.id.navigation_logout:
                            if(!type.equals("Sellers")) {

                                //Toast.makeText(HomeActivity.this,"logout clicked",Toast.LENGTH_SHORT).show();
                                Paper.book().destroy();
                                Intent intent = new Intent(SellerHomeActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                            return true;

                    }
                    return false;
                }
            };


}