package com.app.myapplication.network;

import com.app.myapplication.model.PeopleCardsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET(".")
    Call<PeopleCardsResponse> getData(
            @Query("inc") String inc, @Query("results") int results
    );
}
