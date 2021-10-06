package com.example.khalalagbeapp.Buyers;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khalalagbeapp.Model.Maids;
import com.example.khalalagbeapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

//public class MyMainAdapter  extends RecyclerView.Adapter<MyMainAdapter.MyMaidViewHolder> implements Filterable
//{
//
//
//
//        private ArrayList<Maids> maidsList;
//        private ArrayList<Maids> tempMaidsList;
//        private Context context ;
//
//        public MyMainAdapter(ArrayList<Maids> maidsList, Context context) {
//            this.maidsList = maidsList;
//            tempMaidsList= new ArrayList<>(maidsList);
//            this.context = context;
//        }
//
//        @NonNull
//        @Override
//        public MyMaidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(context).inflate(R.layout.maid_list_layout,parent,false);
//            return new MyMaidViewHolder(view);
//        }
//
//
//        @Override
//        public void onBindViewHolder(@NonNull MyMaidViewHolder holder, int position) {
//            final Maids model = maidsList.get(position);
//            //holder.textView.setText(maids.getMname());
//            holder.maidName.setText(model.getMname());
//            holder.descriptionMaid.setText(model.getDescription());
//            holder.addressMaid.setText(model.getPresent_address());
//            holder.salart1st.setText("Type1= "+ model.getType1_price() + "TK");
//            Log.v("price", "" + model.getType1_price());
//            holder.salary2nd.setText("Type2= "+ model.getType2_price() + "TK");
//            holder.salary3rd.setText("Type3= "+model.getType3_price()+ "TK");
//            Picasso.get().load(model.getImage()).into(holder.imageMaid);
//
//
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context,MaidDetailsActivity.class);
//                    intent.putExtra("mid",model.getMid());
//                   context.startActivity(intent);
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return maidsList.size();
//        }
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence searchchar) {
//
//                ArrayList<Maids> filteredList =new ArrayList<>();
//                if(searchchar==null || searchchar.length()==0){
//                    filteredList.addAll(tempMaidsList);
//                }else
//                {
//                    String pattern =searchchar.toString().toLowerCase().trim();
//                    for (Maids maids :tempMaidsList) {
//                        if(maids.getPresent_address().toLowerCase().contains(pattern))
//                        {
//                            filteredList.add(maids);
//                        }
//
//
//                    }
//                }
//                FilterResults results =new FilterResults();
//                results.values=filteredList;
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                maidsList.clear();
//                maidsList.addAll((Collection<? extends Maids>) results.values);
//                notifyDataSetChanged();
//            }
//
//        };
//    }
//
//
//        public  class MyMaidViewHolder extends RecyclerView.ViewHolder {
//
//            TextView maidName, salart1st,salary2nd,salary3rd,addressMaid ,descriptionMaid;
//            ImageView imageMaid;
//            public MyMaidViewHolder(@NonNull View itemView) {
//                super(itemView);
//                //find your id ;
//                //textView=itemView.findViewById(R.id.)
//                maidName= itemView.findViewById(R.id.product_maids_name);
//                salart1st= itemView.findViewById(R.id.product_maids_salary1);
//                salary2nd= itemView.findViewById(R.id.product_maids_salary2);
//                salary3rd= itemView.findViewById(R.id.product_maids_salary3);
//                addressMaid= itemView.findViewById(R.id.product_maids_present_address);
//                descriptionMaid= itemView.findViewById(R.id.product_maids_description);
//                imageMaid= itemView.findViewById(R.id.product_maid_info_image);
//            }
//        }
//    }
//









//public class MyMainAdapter  extends RecyclerView.Adapter<MyMainAdapter.MyMaidViewHolder> implements Filterable
//{
//    private List<Maids> maidsList;
//    private List<Maids> maidsListAll;
//
//
//    @Override
//    public Filter getFilter() {
//        return maidsFilter;
//    }
//
//    private Filter maidsFilter =new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<Maids> filteredList =new ArrayList<>();
//            if (constraint== null || constraint.length() == 0)
//            {
//                filteredList.addAll(maidsListAll);
//            }
//            else
//            {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                for (Maids maids : maidsListAll){
//                    if(maids.getPresent_address().toLowerCase().contains(filterPattern))
//                    {
//                        filteredList.add(maids);
//                    }
//                }
//            }
//            FilterResults results= new FilterResults();
//            results.values=filteredList;
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            maidsList.clear();
//            maidsList.addAll((List)results.values);
//            notifyDataSetChanged();
//
//        }
//    };
//
//    @NonNull
//    @Override
//    public MyMainAdapter.MyMaidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.maid_list_layout,parent,false);
//        return new MyMaidViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyMainAdapter.MyMaidViewHolder holder, int position) {
//
//        final Maids model = maidsList.get(position);
//        holder.maidName.setText(model.getMname());
//            holder.descriptionMaid.setText(model.getDescription());
//            holder.addressMaid.setText(model.getPresent_address());
//            holder.salart1st.setText("Type1= "+ model.getType1_price() + "TK");
//            Log.v("price", "" + model.getType1_price());
//            holder.salary2nd.setText("Type2= "+ model.getType2_price() + "TK");
//            holder.salary3rd.setText("Type3= "+model.getType3_price()+ "TK");
//            Picasso.get().load(model.getImage()).into(holder.imageMaid);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return maidsList.size();
//    }
//
//    public class MyMaidViewHolder extends RecyclerView.ViewHolder {
//        TextView maidName, salart1st,salary2nd,salary3rd,addressMaid ,descriptionMaid;
//                  ImageView imageMaid;
//        public MyMaidViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            maidName= itemView.findViewById(R.id.product_maids_name);
//                salart1st= itemView.findViewById(R.id.product_maids_salary1);
//                salary2nd= itemView.findViewById(R.id.product_maids_salary2);
//                salary3rd= itemView.findViewById(R.id.product_maids_salary3);
//               addressMaid= itemView.findViewById(R.id.product_maids_present_address);
//                descriptionMaid= itemView.findViewById(R.id.product_maids_description);
//                imageMaid= itemView.findViewById(R.id.product_maid_info_image);
//
//
//        }
//    }
//
//    MyMainAdapter(List<Maids>maidsList){
//        this.maidsList=maidsList;
//        maidsListAll=new ArrayList<>(maidsList);
//    }
//
//
//
//}





public class MyMainAdapter extends FirebaseRecyclerAdapter<Maids,MyMainAdapter.MyMaidViewHolder>
{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyMainAdapter(@NonNull FirebaseRecyclerOptions<Maids> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyMainAdapter.MyMaidViewHolder holder, int position, @NonNull Maids model) {
//        if(model.hide!= true){
//
//            holder.maidName.setText(model.getMname());
//            holder.descriptionMaid.setText(model.getDescription());
//            holder.addressMaid.setText(model.getPresent_address());
//            holder.salart1st.setText("Type1= "+ model.getType1_price() + "TK");
//            Log.v("price", "" + model.getType1_price());
//            holder.salary2nd.setText("Type2= "+ model.getType2_price() + "TK");
//            holder.salary3rd.setText("Type3= "+model.getType3_price()+ "TK");
//            Picasso.get().load(model.getImage()).into(holder.imageMaid);
//        }
            holder.maidName.setText(model.getMname());
            holder.descriptionMaid.setText(model.getDescription());
            holder.addressMaid.setText(model.getPresent_address());
            holder.salart1st.setText("Type1= "+ model.getType1_price() + "TK");
            Log.v("price", "" + model.getType1_price());
            holder.salary2nd.setText("Type2= "+ model.getType2_price() + "TK");
            holder.salary3rd.setText("Type3= "+model.getType3_price()+ "TK");
            Picasso.get().load(model.getImage()).into(holder.imageMaid);

    }

    @NonNull
    @Override
    public MyMainAdapter.MyMaidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.maid_list_layout,parent,false);
        return new MyMaidViewHolder(view);
    }

    public class MyMaidViewHolder extends RecyclerView.ViewHolder{
        TextView maidName, salart1st,salary2nd,salary3rd,addressMaid ,descriptionMaid;
                          ImageView imageMaid;

        public MyMaidViewHolder(View view) {
            super(view);

                maidName= itemView.findViewById(R.id.product_maids_name);
                salart1st= itemView.findViewById(R.id.product_maids_salary1);
                salary2nd= itemView.findViewById(R.id.product_maids_salary2);
                salary3rd= itemView.findViewById(R.id.product_maids_salary3);
                addressMaid= itemView.findViewById(R.id.product_maids_present_address);
                descriptionMaid= itemView.findViewById(R.id.product_maids_description);
                imageMaid= itemView.findViewById(R.id.product_maid_info_image);


        }
    }
}