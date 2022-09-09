package com.ubk.casdis_tailor.ui.specialmsg;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpecialmsgViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpecialmsgViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}