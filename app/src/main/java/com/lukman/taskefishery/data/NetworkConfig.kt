package com.lukman.taskefishery.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class NetworkConfig {
    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://stein.efishery.com/v1/storages/5e1edf521073e315924ceab4/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(Network::class.java)
}
interface Network {
    @GET("list")
    fun getUsers(
        @QueryMap query: Map<String, @JvmSuppressWildcards Any>
    ): Call<List<ResponseHargaItem>>

    @POST("list")
    fun postHarga(
        @Body data: List<PostHarga>
    ): Call<ResponseData>
}