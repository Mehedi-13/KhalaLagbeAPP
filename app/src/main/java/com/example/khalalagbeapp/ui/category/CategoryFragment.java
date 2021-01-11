package com.example.khalalagbeapp.ui.category;

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
import com.example.khalalagbeapp.ui.cart.CartViewModel;

public class CategoryFragment extends Fragment {
    TextView categoryText;
    CategoryViewModel categoryViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        View root= inflater.inflate(R.layout.fragment_category,container,false);
        categoryText= root.findViewById(R.id.text_category);

        categoryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                categoryText.setText(s);
            }
        });

        return root;
    }
}
