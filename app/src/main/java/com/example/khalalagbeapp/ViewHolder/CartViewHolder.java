package com.example.khalalagbeapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khalalagbeapp.Interface.MaidListClickListner;
import com.example.khalalagbeapp.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
      public TextView txtMaidName , txtMaidSalary,txtMemberQuantity;
    private MaidListClickListner maidListClickListner;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txtMaidName= itemView. findViewById(R.id.cart_maids_name);
        txtMaidSalary= itemView. findViewById(R.id.cart_maid_salary);

        txtMemberQuantity= itemView. findViewById(R.id.cart_member_quantity);

    }

    @Override
    public void onClick(View v) {
        maidListClickListner.onClick(v, getAdapterPosition(),false);
    }

    public void setMaidListClickListner(MaidListClickListner maidListClickListner) {
        this.maidListClickListner = maidListClickListner;
    }
}
