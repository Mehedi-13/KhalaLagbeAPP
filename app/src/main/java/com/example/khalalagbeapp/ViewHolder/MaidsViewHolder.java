package com.example.khalalagbeapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khalalagbeapp.Interface.MaidListClickListner;
import com.example.khalalagbeapp.R;

public class MaidsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtMaidsName, txtMaidsDescription, txtMaidPrice1 , txtMaidPrice2,txtMaidPrice3,txtMaidPresentAddress ;
    public ImageView imageView;
    public MaidListClickListner listner;


    public MaidsViewHolder(@NonNull View itemView)
    {
        super(itemView);

        imageView= (ImageView) itemView.findViewById(R.id.product_maid_info_image);
        txtMaidsName= (TextView) itemView.findViewById(R.id.product_maids_name);
        txtMaidsDescription= (TextView) itemView.findViewById(R.id.product_maids_description);
        txtMaidPresentAddress= (TextView) itemView.findViewById(R.id.product_maids_present_address);
        txtMaidPrice1= (TextView) itemView.findViewById(R.id.product_maids_salary1);
        txtMaidPrice2= (TextView) itemView.findViewById(R.id.product_maids_salary2);
        txtMaidPrice3= (TextView) itemView.findViewById(R.id.product_maids_salary3);
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
