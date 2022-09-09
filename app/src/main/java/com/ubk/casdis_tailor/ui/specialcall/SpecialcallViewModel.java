package com.ubk.casdis_tailor.ui.specialcall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpecialcallViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpecialcallViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}