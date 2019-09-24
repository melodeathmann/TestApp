package by.life.testapp.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiRequest {

    private const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/"

    var api: ApiInterface = initApi()
        private set

    private fun initApi(): ApiInterface =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

}


