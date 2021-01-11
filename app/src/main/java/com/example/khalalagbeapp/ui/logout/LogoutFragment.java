package com.example.khalalagbeapp.ui.logout;

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

public class LogoutFragment extends Fragment {

    TextView logoutText;
    LogoutViewModel logoutViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        logoutViewModel= ViewModelProviders.of(this).get(LogoutViewModel.class);

        View root= inflater.inflate(R.layout.fragment_logout,container,false);
        logoutText= root.findViewById(R.id.text_logout);

        logoutViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                logoutText.setText(s);
            }
        });

        return root;
    }
}
