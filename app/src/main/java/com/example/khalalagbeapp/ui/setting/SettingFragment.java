package com.example.khalalagbeapp.ui.setting;

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

public class SettingFragment extends Fragment {

    TextView settingText;
    SettingViewModel settingViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        settingViewModel= ViewModelProviders.of(this).get(SettingViewModel.class);

        View root= inflater.inflate(R.layout.fragment_setting,container,false);
        settingText= root.findViewById(R.id.text_setting);

        settingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                settingText.setText(s);
            }
        });


        return root;
    }
}
