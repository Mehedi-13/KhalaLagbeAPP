package com.example.khalalagbeapp.ui.add;

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

public class AddFragment extends Fragment {

    TextView addText;
    AddViewModel addViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        addViewModel= ViewModelProviders.of(this).get(AddViewModel.class);

        View root= inflater.inflate(R.layout.fragment_add,container,false);
        addText= root.findViewById(R.id.text_add);

        addViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                addText.setText(s);
            }
        });

        return root;
    }
}
