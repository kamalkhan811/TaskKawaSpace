package com.app.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.app.myapplication.Repository.UserDataRepository;
import com.app.myapplication.model.PeopleCardsResponse;
import com.app.myapplication.util.Resource;

public class UserViewModel extends ViewModel {

    private UserDataRepository userDataRepository;

    public void init() {
        userDataRepository = new UserDataRepository();
    }

    public LiveData<Resource<PeopleCardsResponse>> getUsersLiveData(String inc, int result) {
        return userDataRepository.getUsersDataApi(inc,result);
    }

}
