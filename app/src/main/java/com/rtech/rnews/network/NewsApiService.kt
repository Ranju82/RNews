package com.rtech.rnews.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org/"
private const val API_KEY="908462ab75da43ab8313c45291cbc55d"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val httpLoggingInterceptor=HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val okHttpClient=OkHttpClient.Builder()
    .addInterceptor(httpLoggingInterceptor)
    .addInterceptor { chain ->
        val request=chain.request().newBuilder()
            .addHeader("X-Api-Key", API_KEY)
            .build()
        chain.proceed(request)
    }
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface NewsApiService{
    @GET("/v2/top-headlines")
    fun getHeadlines(@Query("category") category: String,
                    @Query("language") language:String):Deferred<Headlines>
}

object NewsApi{
    val retrofitService:NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}
