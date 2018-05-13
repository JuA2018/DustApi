package org.techtown.dustapi.service;

import org.techtown.dustapi.data.Dust;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QueryService {
    @GET(URLService.geturl + URLService.servicekey + "&_returnType=json")
    Call<Dust> getdust(@Query("sidoName") String sidoName, @Query("pageNo") int pageNo,
                       @Query("numOfRows") int numOfRows, @Query("ver") double ver);

}
