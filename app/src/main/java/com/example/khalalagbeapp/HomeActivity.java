package com.example.khalalagbeapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khalalagbeapp.Model.Maids;
import com.example.khalalagbeapp.Prevalent.Prevalent;
import com.example.khalalagbeapp.ViewHolder.MaidsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NavUtils;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DatabaseReference Maidsref;
    private RecyclerView recyclerView;
    private String type= "";
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        Paper.init(this);

        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();
        if(bundle != null)
        {
            type= getIntent().getExtras().get("Admin").toString();
        }

        Maidsref= FirebaseDatabase.getInstance().getReference().child("Maids");


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!type.equals("Admin"))
                {

                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }

            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow ,
                R.id.nav_cart,R.id.nav_search,R.id.nav_orders,R.id.nav_settings,R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener()
        {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                int id =destination.getId();
                switch (id){
                    case R.id.nav_home:
                        //Toast.makeText(HomeActivity.this,"Home clicked",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_cart:
                        if(!type.equals("Admin"))
                        {
                            Intent intent2 = new Intent(HomeActivity.this,CartActivity.class);
                            startActivity(intent2);

                        }
                        break;
                    case R.id.nav_orders:
                        if(!type.equals("Admin"))
                        {
                            Toast.makeText(HomeActivity.this,"Order clicked",Toast.LENGTH_SHORT).show();

                        }
                         break;
                    case R.id.nav_search:
                        if(!type.equals("Admin"))
                        {
                            Intent intent5 = new Intent(HomeActivity.this,SearchMaidActivity.class );
                            startActivity(intent5);
                        }

                        break;
                    case R.id.nav_settings:
                        if(!type.equals("Admin"))
                        {
                            Intent intent1 = new Intent(HomeActivity.this,SettingsActivity.class);
                            startActivity(intent1);
                        }
                        //Toast.makeText(HomeActivity.this,"Setting clicked",Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_logout:
                        if(!type.equals("Admin")) {

                            //Toast.makeText(HomeActivity.this,"logout clicked",Toast.LENGTH_SHORT).show();
                            Paper.book().destroy();
                            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }

                        break;

                }

            }
        });


        View headerView =navigationView.getHeaderView(0);
        TextView userNameTextView =headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

        if(!type.equals("Admin"))
        {
            userNameTextView.setText(Prevalent.currentOnlineUsers.getName());
            Picasso.get().load(Prevalent.currentOnlineUsers.getImage()).placeholder(R.drawable.profile).into(profileImageView);


        }

     recyclerView =findViewById(R.id.recycler_menu);
     recyclerView.setHasFixedSize(true);
     layoutManager= new LinearLayoutManager(this);
     recyclerView.setLayoutManager(layoutManager);

    }


    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Maids> options =
                new FirebaseRecyclerOptions.Builder<Maids>()
                .setQuery(Maidsref, Maids.class)
                .build();

        FirebaseRecyclerAdapter<Maids, MaidsViewHolder> adapter=
                new FirebaseRecyclerAdapter<Maids, MaidsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final MaidsViewHolder holder, int position, @NonNull final Maids model)
                    {
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

                                if(type.equals("Admin"))
                                {
                                    Intent intent = new Intent(HomeActivity.this,AdminMaintainMaidActivity.class);
                                    intent.putExtra("mid",model.getMid());
                                    startActivity(intent);

                                }
                                else
                                {
                                    Intent intent = new Intent(HomeActivity.this,MaidDetailsActivity.class);
                                    intent.putExtra("mid",model.getMid());
                                    startActivity(intent);

                                }

                            }
                        });

                    }

                    @NonNull
                    @Override
                    public MaidsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.maid_list_layout,parent,false);
                        MaidsViewHolder holder= new MaidsViewHolder(view);
                        return  holder;
                    }
                };


        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == android.R.id.home)
//        {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}