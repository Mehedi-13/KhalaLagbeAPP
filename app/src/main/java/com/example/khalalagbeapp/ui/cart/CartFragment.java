package com.example.khalalagbeapp.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.khalalagbeapp.R;

public class CartFragment extends Fragment {

    TextView cartText;
    CartViewModel cartViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);

        View root =inflater.inflate(R.layout.fragment_cart,container,false);
        cartText= root.findViewById(R.id.text_cart);

        cartViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                cartText.setText(s);
            }
        });

        return root;
    }
}
