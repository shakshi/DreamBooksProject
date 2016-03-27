package com.codingblocks.dreambooks.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shakshi on 26-03-2016.
 */
public class ApiClient {
    private static ApiInterface mservice;

    public static ApiInterface getInterface(){

//        Gson gson = new GsonBuilder()
//                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
//                .create();
        if(mservice==null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://idreambooks.com/api/").addConverterFactory(GsonConverterFactory.create()).build();
            mservice = retrofit.create(ApiInterface.class);
        }

        return mservice;
    }
}
