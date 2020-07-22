package com.nytimes.data.network

import com.nytimes.data.response.GetMasters
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface MyApi {


    @POST("7.json?api-key=ojwLsa5Om06Oo7PGOaW4MYpwtcbd98eR")
    suspend fun MasterMostpopular(): Response<GetMasters>


    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): MyApi {
            val networkOkhttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(networkOkhttpClient)
                .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/viewed/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkhttpInterceptor())//when you offline then comment this
                .build()
                .create(MyApi::class.java)
        }


        //okhttp response interceptor
        fun getOkhttpInterceptor(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build()
            return client as OkHttpClient
        }


        operator fun invoke(): MyApi? {
            return RetrofitClient.retrofitInstance?.create(MyApi::class.java)
        }
    }
}

