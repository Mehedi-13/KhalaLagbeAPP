package com.example.khalalagbeapp.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.khalalagbeapp.R;

public class OrderFragment extends Fragment {

    TextView orderText;
    OrderViewModel orderViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        View root= inflater.inflate(R.layout.fragment_order,container,false);
        orderText= root.findViewById(R.id.text_order);

        orderViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                orderText.setText(s);
            }
        });


        return root;
    }
}
