package com.example.khalalagbeapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khalalagbeapp.Interface.MaidListClickListner;
import com.example.khalalagbeapp.R;



public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtMaidsName, txtMaidsDescription, txtMaidPrice1 , txtMaidPrice2,txtMaidPrice3,txtMaidPresentAddress,productState ;
    public ImageView imageView;
    public MaidListClickListner listner;


    public ItemViewHolder(@NonNull View itemView)
    {
        super(itemView);

        productState= (TextView) itemView.findViewById(R.id.product_state);
        imageView= (ImageView) itemView.findViewById(R.id.product_maid_info_image1);
        txtMaidsName= (TextView) itemView.findViewById(R.id.product_maids_name1);
        txtMaidsDescription= (TextView) itemView.findViewById(R.id.product_maids_description1);
        txtMaidPresentAddress= (TextView) itemView.findViewById(R.id.product_maids_present_address1);
        txtMaidPrice1= (TextView) itemView.findViewById(R.id.product_maids_salary11);
        txtMaidPrice2= (TextView) itemView.findViewById(R.id.product_maids_salary21);
        txtMaidPrice3= (TextView) itemView.findViewById(R.id.product_maids_salary31);
    }

    public void setMaidListClickListner (MaidListClickListner listner)
    {
        this.listner=listner;

    }

    @Override
    public void onClick(View v)
    {

        listner.onClick(v, getAdapterPosition(),false);
    }



}

