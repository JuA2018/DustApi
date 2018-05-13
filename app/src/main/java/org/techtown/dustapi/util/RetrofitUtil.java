package org.techtown.dustapi.util;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private final static String baseurl = "http://openapi.airkorea.or.kr/";

    private static OkHttpClient.Builder okhttp = new OkHttpClient.Builder(); //로그 볼 수 있게 하는거
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttp.addInterceptor(interceptor);
        builder.client(okhttp.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
