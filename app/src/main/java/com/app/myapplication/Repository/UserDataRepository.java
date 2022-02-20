package com.app.myapplication.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.app.myapplication.model.PeopleCardsResponse;
import com.app.myapplication.network.RetrofitClient;
import com.app.myapplication.util.Resource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataRepository {

    public LiveData<Resource<PeopleCardsResponse>> getUsersDataApi(String inc, int result) {
        final MutableLiveData<Resource<PeopleCardsResponse>> mUsersMutableLiveData  = new MutableLiveData<>();
        mUsersMutableLiveData.setValue(Resource.loading(null));
        RetrofitClient.getInstance().
                getApi().getData(inc,result).enqueue(new Callback<PeopleCardsResponse>() {
            @Override
            public void onResponse(@NonNull Call<PeopleCardsResponse> call, @NonNull Response<PeopleCardsResponse> response) {
                if(response.body()!=null)
                {
                    mUsersMutableLiveData.setValue(Resource.success(response.body()));
                }else {
                    mUsersMutableLiveData.setValue(Resource.error(response.message(),null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PeopleCardsResponse> call, @NonNull Throwable t) {
                mUsersMutableLiveData.setValue(Resource.error(t.getMessage(),null));
            }
        });
        return mUsersMutableLiveData;
    }
}
