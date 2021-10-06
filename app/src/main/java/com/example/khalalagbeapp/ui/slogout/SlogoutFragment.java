package com.example.khalalagbeapp.ui.slogout;

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
import com.example.khalalagbeapp.ui.logout.LogoutViewModel;

public class SlogoutFragment extends Fragment {

    TextView slogoutText;
    SlogoutViewModel slogoutViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        slogoutViewModel= ViewModelProviders.of(this).get(SlogoutViewModel.class);

        View root= inflater.inflate(R.layout.fragment_slogout,container,false);
        slogoutText= root.findViewById(R.id.text_slogout);

        slogoutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                slogoutText.setText(s);
            }
        });

        return root;
    }
}
