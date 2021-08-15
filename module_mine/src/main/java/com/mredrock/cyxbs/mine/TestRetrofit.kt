package com.mredrock.cyxbs.mine

import com.mredrock.cyxbs.mine.network.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object TestRetrofit {
    val testRetrofit = provideRetrofit()


    private fun provideRetrofit(): ApiService {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor{
                    val build = it.request()
                        .newBuilder()
                        .addHeader("Authorization",
                            "Bearer eyJEYXRhIjp7ImdlbmRlciI6IueUtyIsInN0dV9udW0iOiIyMDE5MjExNjg1In0sIkRvbWFpbiI6Im1hZ2lwb2tlIiwiUmVkaWQiOiI1NGUxNzEzZTQ5MjUyMTcyNmMzZGQ3ZTg4Mzk1NDcxNzJhZTk1ZTlhIiwiZXhwIjoiNzM5ODg0NDcyNiIsImlhdCI6IjE2MjkwMzM0NzMiLCJzdWIiOiJ3ZWIifQ==.3C7p1j7iOXb4vJ9fpCde8whhh/NEhpFfq136BDtxxGZf6ER0mxehTKpvxBJsh/odQQlc5m4W9iFN58ldLzNI061ZpHLkWHF0mMIe/7bgn38E9uO0OiU/Pqsme8gkAePGPlXEPzJCEqgrQJKIfafXaCSWayMHhB3ICgn503I+pms2/0fA8xn/L053Te+bVQFOoDfmcd335aGY7TgGegFMNDPkEr1d5Rd1cxOnBPqLKbtUpZW+H92+IuY1Xe7zXj7tRh+8F5wKmnWXBLGDwc7krad6tXpwGSFhzRoOpwcnHMWoxNvEP8viXx1MfxBe3eoIwdkU+EPvJ137TgVxH+odWA==")
                        .build()
                return@addInterceptor it.proceed(build)
                }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://be-dev.redrock.cqupt.edu.cn")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}