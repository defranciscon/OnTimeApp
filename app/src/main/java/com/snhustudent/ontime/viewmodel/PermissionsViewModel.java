package com.snhustudent.ontime.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PermissionsViewModel extends AndroidViewModel {
    private final MutableLiveData<String> mText;

    public PermissionsViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is a permissions fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
