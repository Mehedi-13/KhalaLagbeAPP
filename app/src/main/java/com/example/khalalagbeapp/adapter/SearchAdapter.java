package com.example.khalalagbeapp.adapter;

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
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ImageViewHolder> {
   // Context context;
    //Activity activity;
    private  boolean canChange;

    private List<Maids> maidsList;




//    public SearchAdapter(Context context, Activity activity){
//        this.context=context;
//        this.activity=activity;
//
//    }

    public SearchAdapter(){
//        this.context=context;
//        this.activity=activity;

    }
    public void setData(List<Maids> maidsList){
        this.maidsList = maidsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maid_list_layout,parent,false);
        SearchAdapter.ImageViewHolder imageViewHolder=new SearchAdapter.ImageViewHolder(view);


        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ImageViewHolder holder, int position) {
        final Maids model = maidsList.get(position);



        holder.addressMaid.setText(model.getPresent_address());

        holder.maidName.setText(model.getMname());
        holder.descriptionMaid.setText(model.getDescription());
        holder.salart1st.setText("Type1= "+ model.getType1_price() + "TK");
        Log.v("price", "" + model.getType1_price());
        holder.salary2nd.setText("Type2= "+ model.getType2_price() + "TK");
        holder.salary3rd.setText("Type3= "+model.getType3_price()+ "TK");
        Picasso.get().load(model.getImage()).into(holder.imageMaid);



    }



//    @Override
//    public void onBindViewHolder(UserWithdrawAdapter.ImageViewHolder holder, int position) {
//        final TransactionAdmin with = withdrawList.get(position);
//        String msg= activity.getResources().getString(R.string.withdraw_wuccessful);
//
//        if(with.getSuccessful()){
//            holder.message_withdraw.setText(msg);
//
//            ///////////// set Light here
//            //holder.iv_confirm_withdrawal
////            holder.iv_confirm_withdrawal.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
//            holder.iv_trans.setImageResource(R.drawable.money_sent);
//
//        }
//
//
//        holder.tv_withdraw_amount.setText(with.getAmount());
//        holder.tv_withdrawal_desc.setText(with.getDescription());
//        holder.tv_withdraw_doc_id.setText(with.getDocumentId());
//        holder.tv_name.setText(with.getSenderName());
//        holder.tv_acc_num.setText(with.getFrom());
//
//        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
//        cal.setTimeInMillis(Long.parseLong(with.getTimeStamp()));
//        String dateTime = DateFormat.format("dd/MM/yyyy hh:mm aa",cal).toString();
//        holder.et_withdraw_date.setText(dateTime);
//
//        holder.bt_more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(holder.ll_epand.getVisibility()==View.GONE){
//                    holder.bt_more.setImageResource(R.drawable.less);
//                    TransitionManager.beginDelayedTransition(holder.cv_trans,new AutoTransition());
//                    holder.ll_epand.setVisibility(View.VISIBLE);
//
//
//                }else{
//                    holder.bt_more.setImageResource(R.drawable.more);
//                    TransitionManager.beginDelayedTransition(holder.rl_trans,new AutoTransition());
//                    holder.ll_epand.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        holder.bt_confirm_withdrawal.setVisibility(View.GONE);
//        holder.bt_refuse.setVisibility(View.GONE);
//






    //}




    @Override
    public int getItemCount() {
        return maidsList == null ? 0 : maidsList.size();
    }
    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        //private TextView message_withdraw, tv_withdraw_amount, tv_withdrawal_desc, tv_withdraw_doc_id,et_withdraw_date,tv_name,tv_acc_num;
        TextView maidName, salart1st,salary2nd,salary3rd,addressMaid ,descriptionMaid;
        ImageView imageMaid;



        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            //message_withdraw=itemView.findViewById(R.id.message_withdraw);

            maidName= itemView.findViewById(R.id.product_maids_name);
            salart1st= itemView.findViewById(R.id.product_maids_salary1);
            salary2nd= itemView.findViewById(R.id.product_maids_salary2);
            salary3rd= itemView.findViewById(R.id.product_maids_salary3);
            addressMaid= itemView.findViewById(R.id.product_maids_present_address);
            descriptionMaid= itemView.findViewById(R.id.product_maids_description);
            imageMaid= itemView.findViewById(R.id.product_maid_info_image);




//




        }
    }


}