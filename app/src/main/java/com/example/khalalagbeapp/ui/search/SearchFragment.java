package com.example.khalalagbeapp.ui.search;

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

public class SearchFragment extends Fragment {
    TextView searchText;
    SearchViewModel searchViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        View root= inflater.inflate(R.layout.fragment_search,container,false);
        searchText= root.findViewById(R.id.text_search);

        searchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                searchText.setText(s);
            }
        });

        return root;
    }
}
