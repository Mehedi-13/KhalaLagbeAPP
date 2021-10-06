package com.example.khalalagbeapp.Buyers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.khalalagbeapp.Model.Maids;
import com.example.khalalagbeapp.R;
import com.example.khalalagbeapp.adapter.SearchAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//public class SearchMaidActivity extends AppCompatActivity {
////    private Button SearchBtn;
////    private EditText inputText;
//    private RecyclerView searchList;
//    private  String SearchInput;
//    private MyMainAdapter myMainAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_maid);
//
//
//        //inputText =findViewById(R.id.search_maid_address);
//        //SearchBtn =findViewById(R.id.search_but);
//        searchList =findViewById(R.id.search_list);
//
//        searchList.setLayoutManager(new LinearLayoutManager(SearchMaidActivity.this));
//
//
////        SearchBtn.setOnClickListener(new View.OnClickListener()
////
////        {
////            @Override
////            public void onClick(View v) {
////                SearchInput= inputText.getText().toString().toLowerCase();
////
////            }
////        });
//
//
////                                .orderByChild("present_address").startAt(SearchInput).endAt(SearchInput + "\uf8ff")
//
//        FirebaseDatabase.getInstance().getReference().child("Maids").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList <Maids> maidsArrayList= new ArrayList<>();
//                if(snapshot.exists())
//                {
//                    for (DataSnapshot dataSnapshot:snapshot.getChildren())
//                    {
//                        Maids maids= dataSnapshot.getValue(Maids.class);
//                        maidsArrayList.add(maids);
//                    }
//
//                    myMainAdapter=new MyMainAdapter(maidsArrayList,SearchMaidActivity.this);
//
//                    searchList.setAdapter(myMainAdapter);
//                    Toast.makeText(SearchMaidActivity.this, "Nothing show"+ maidsArrayList.size(), Toast.LENGTH_SHORT).show();
//                    myMainAdapter.notifyDataSetChanged();
//
////                    inputText.addTextChangedListener(new TextWatcher() {
////                        @Override
////                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////
////                        }
////
////                        @Override
////                        public void onTextChanged(CharSequence s, int start, int before, int count) {
////
////
////
////                        }
////
////                        @Override
////                        public void afterTextChanged(Editable s) {
////                            myMainAdapter.getFilter().filter(s.toString());
////
////                        }
////                    });
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        searchList= findViewById(R.id.search_list);
//        myMainAdapter = new MyMainAdapter(Adapter);
//        searchList.setAdapter(myMainAdapter);
//        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        searchList.addItemDecoration(dividerItemDecoration);
//
//
//    }
//
//@Override
//public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.searchmenu, menu);
//        MenuItem item = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//@Override
//public boolean onQueryTextSubmit(String query) {
//        return false;
//        }
//
//@Override
//public boolean onQueryTextChange(String newText) {
//        myMainAdapter.getFilter().filter(newText);
//        return false;
//        }
//        });
//
//        return true;
//
//        }
//
//
//
//}










//public class SearchMaidActivity extends AppCompatActivity
//{
//    //RecyclerView recView;
//    //private Button SearchBtn;
//    //private EditText inputText;
//    private RecyclerView searchList;
//    private  String SearchInput;
//    private MyMainAdapter mainAdapter;
//    private Toolbar toolbar;
//
//    @Override
//   protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_maid);
//
////        inputText =findViewById(R.id.search_maid_address);
////       SearchBtn =findViewById(R.id.search_but);
//        searchList =findViewById(R.id.search_list);
//
//        toolbar=findViewById(R.id.toolBar);
//        setSupportActionBar(toolbar);
//
//        searchList.setLayoutManager(new LinearLayoutManager(this));
//
////        SearchBtn.setOnClickListener(new View.OnClickListener()
////
////        {
////            @Override
////            public void onClick(View v) {
////                SearchInput= inputText.getText().toString().toLowerCase();
////
////            }
////        });
//
//        FirebaseRecyclerOptions<Maids> options=
//                new FirebaseRecyclerOptions.Builder<Maids>()
//                .setQuery(FirebaseDatabase.getInstance().getReference().child("Maids"),Maids.class).build();
//
//        mainAdapter = new MyMainAdapter(options);
//        searchList.setAdapter(mainAdapter);
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mainAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mainAdapter.stopListening();
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.searchmenu,menu);
//        MenuItem  item= menu.findItem(R.id.search);
//        SearchView searchView= (SearchView)item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                processsearch(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                processsearch(s);
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void processsearch(String s) {
//
//        FirebaseRecyclerOptions<Maids> options=
//                new FirebaseRecyclerOptions.Builder<Maids>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference()
//                                .child("Maids").orderByChild("present_address")
//                                .startAt(s).endAt(s + "\uf8ff"),Maids.class).build();
//
//        mainAdapter=new MyMainAdapter(options);
//        mainAdapter.startListening();
//        searchList.setAdapter(mainAdapter);
//
//    }
//}












//public class SearchMaidActivity extends AppCompatActivity{
//
//    //RecyclerView recyclerView;
//    private MyMainAdapter myMainAdapter;
//    //private RecyclerView searchList;
//
//    private List<Maids> maidsList;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_maid);
//
//        setUpRecyclerView();
//    }
//
//    private void setUpRecyclerView() {
//        RecyclerView recyclerView = findViewById(R.id.search_list);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        myMainAdapter = new MyMainAdapter(maidsList);
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(myMainAdapter);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.searchmenu, menu);
//        MenuItem item = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                myMainAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//
//        return true;
//
//    }
//
//}










    //    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_maid);
//
//        ArrayList <Maids> maidsArrayList= new ArrayList<>();
//        maidsArrayList= new ArrayList<>();
//
//        FirebaseDatabase.getInstance().getReference().child("Maids").orderByChild("present_address").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<Maids> maidsArrayList = new ArrayList<>();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        recyclerView= findViewById(R.id.search_list);
//        myMainAdapter = new MyMainAdapter(maidsArrayList);
//        recyclerView.setAdapter(myMainAdapter);
//        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
//
//
//    }



//    FirebaseDatabase.getInstance().getReference().child("Maids").addListenerForSingleValueEvent(new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            ArrayList <Maids> maidsArrayList= new ArrayList<>();
//
//
//            }
//        }




public class SearchMaidActivity extends AppCompatActivity{

   private RecyclerView recview;
   private MyMainAdapter mainAdapter;
   private Toolbar toolbar;
   //FirebaseDatabase f
private List<Maids > mainMaidList;
private List<Maids > searched;
private SearchAdapter searchAdapter;

    FirebaseRecyclerOptions<Maids> options;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_maid);
        setTitle("");
        mainMaidList = new ArrayList<>();
        searched = new ArrayList<>();

        recview= findViewById(R.id.search_list);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        recview.setLayoutManager(new LinearLayoutManager(this));


        options=
                new FirebaseRecyclerOptions.Builder<Maids>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Maids"),Maids.class).build();

        mainAdapter = new MyMainAdapter(options);
        recview.setAdapter(mainAdapter);

        getTheData();



    }

    private void getTheData() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Maids");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mainMaidList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Maids maids = postSnapshot.getValue(Maids.class);
                    Maids newMaid = new Maids();
                    newMaid.setPresent_address(maids.getPresent_address());
                    newMaid.setMname(maids.getMname());
                    newMaid.setDescription(maids.getDescription());
                    newMaid.setType1_price(maids.getType1_price());
                    newMaid.setType2_price(maids.getType2_price());
                    newMaid.setType3_price(maids.getType3_price());
                   newMaid.setImage(maids.getImage());


//                    Maids newMaid = new Maids(maids.getMname(),maids.getCategory(),maids.getDate(),maids.getDescription(),maids.getImage(),maids.getTime(),maids.getMid(),maids.getType1_price()
//                            ,maids.getType3_price(),maids.getPresent_address(),maids.getMaid_PhoneNumber(),maids.getProductState(),maids.getPresent_address());
                    //System.out.println(trans.getFrom() + " my one");
                    mainMaidList.add(newMaid);
                    System.out.println(maids.getMname()+"nameeeeeeeeeeeeeeeee");
                    //adapter = new TransListAdapter(AdminTransactionsActivity.this, AdminTransactionsActivity.this);
                    //
                   // rv_transac.setAdapter(adapter);
//
                    //adapter.setData(transList);
                    //loading.setVisibility(View.GONE);
                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem  item= menu.findItem(R.id.search);
        SearchView searchView= (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
               processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s) {
        searched.clear();
        if(!s.isEmpty()){
            for (Maids md: mainMaidList
            ) {
                if(md.getPresent_address().toLowerCase().contains(s.toLowerCase())){
                    searched.add(md);

                }
            }











//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference maidsRef = rootRef.child("Maids");
//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Boolean found;
//                String search = "Maids";
//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String present_address = ds.child("present_address").getValue(String.class);
//                    found = present_address.contains(search);
//                    Log.d("TAG", present_address + " / " + found);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {}
//        };
//        maidsRef.addListenerForSingleValueEvent(eventListener);

            searchAdapter = new SearchAdapter();
            searchAdapter.setData(searched);
            recview.setAdapter(searchAdapter);

//        mainAdapter=new MyMainAdapter(options);
//        mainAdapter.startListening();
//        recview.setAdapter(mainAdapter);

        }else {

            mainAdapter = new MyMainAdapter(options);
            recview.setAdapter(mainAdapter);

        }


//        FirebaseRecyclerOptions<Maids> options=
//                new FirebaseRecyclerOptions.Builder<Maids>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference()
//                                .child("Maids").orderByChild("present_address")
//                                .startAt(s).endAt(s + "\uf8ff"),Maids.class).build();
       // List<Maids> maidsList = new ArrayList<>()




    }
}






