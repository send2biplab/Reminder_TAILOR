package com.ubk.casdis_tailor.ui.keyword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KeywordViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KeywordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}