package com.example.khalalagbeapp.ui.slogout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlogoutViewModel extends ViewModel {
    MutableLiveData<String> mText;

    public SlogoutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Logout fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
