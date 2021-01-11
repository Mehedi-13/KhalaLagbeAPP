package com.example.khalalagbeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khalalagbeapp.Model.Maids;
import com.example.khalalagbeapp.ViewHolder.MaidsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchMaidActivity extends AppCompatActivity {
    private Button SearchBtn;
    private EditText inputText;
    private RecyclerView searchList;
    private  String SearchInput;
    private MyMainAdapter myMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_maid);


        inputText =findViewById(R.id.search_maid_address);
        SearchBtn =findViewById(R.id.search_but);
        searchList =findViewById(R.id.search_list);

        searchList.setLayoutManager(new LinearLayoutManager(SearchMaidActivity.this));


        SearchBtn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                SearchInput= inputText.getText().toString().toLowerCase();

            }
        });



        FirebaseDatabase.getInstance().getReference().child("Maids").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList <Maids> maidsArrayList= new ArrayList<>();
                if(snapshot.exists())
                {
                    for (DataSnapshot dataSnapshot:snapshot.getChildren())
                    {
                        Maids maids= dataSnapshot.getValue(Maids.class);
                        maidsArrayList.add(maids);
                    }

                    myMainAdapter=new MyMainAdapter(maidsArrayList,SearchMaidActivity.this);

                    searchList.setAdapter(myMainAdapter);
                    Toast.makeText(SearchMaidActivity.this, "Nothing show"+ maidsArrayList.size(), Toast.LENGTH_SHORT).show();
                    myMainAdapter.notifyDataSetChanged();

                    inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {



                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            myMainAdapter.getFilter().filter(s.toString());

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }



}