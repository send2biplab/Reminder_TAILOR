package com.ubk.casdis_tailor.ui.permission;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PeemissionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PeemissionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}