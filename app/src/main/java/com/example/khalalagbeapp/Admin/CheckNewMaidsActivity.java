package com.example.khalalagbeapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.khalalagbeapp.Interface.MaidListClickListner;
import com.example.khalalagbeapp.Model.Maids;
import com.example.khalalagbeapp.R;
import com.example.khalalagbeapp.ViewHolder.MaidsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CheckNewMaidsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference unverifiedMaidsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_new_maids);

        unverifiedMaidsRef= FirebaseDatabase.getInstance().getReference().child("Maids");

        recyclerView= findViewById(R.id.admin_maids_checklist);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Maids> options=
                new FirebaseRecyclerOptions.Builder<Maids>()
                .setQuery(unverifiedMaidsRef.orderByChild("productState").equalTo("Not Approved"),Maids.class).build();

        FirebaseRecyclerAdapter<Maids, MaidsViewHolder> adapter
        = new FirebaseRecyclerAdapter<Maids, MaidsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MaidsViewHolder holder, int position, @NonNull final Maids model) {

                holder.txtMaidsName.setText(model.getMname());
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
                        AlertDialog.Builder builder=new AlertDialog.Builder(CheckNewMaidsActivity.this);
                        builder.setTitle("DO you want to Approve this Maid's request. Are you sure? ");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position) {

                                if(position==0)
                                {
                                    ChangeProductState (maidId);
                                }
                                if(position==1)
                                {

                                }
                            }
                        });
                        builder.show();
                    }
                });

            }

            @NonNull
            @Override
            public MaidsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.maid_list_layout,parent,false);
                MaidsViewHolder holder= new MaidsViewHolder(view);
                return  holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void ChangeProductState(String maidID) {
        unverifiedMaidsRef.child(maidID).child("productState").setValue("Approved").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(CheckNewMaidsActivity.this, "That Maid's request has been approved and it is now available for hire from the seller.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}