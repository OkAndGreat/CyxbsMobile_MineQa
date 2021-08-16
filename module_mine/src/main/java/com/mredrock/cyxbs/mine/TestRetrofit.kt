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
                            "Bearer eyJEYXRhIjp7ImdlbmRlciI6IueUtyIsInN0dV9udW0iOiIyMDE5MjExNjg1In0sIkRvbWFpbiI6Im1hZ2lwb2tlIiwiUmVkaWQiOiI1NGUxNzEzZTQ5MjUyMTcyNmMzZGQ3ZTg4Mzk1NDcxNzJhZTk1ZTlhIiwiZXhwIjoiNzM5ODg4Mzk4NyIsImlhdCI6IjE2MjkwNzI3MzQiLCJzdWIiOiJ3ZWIifQ==.c1V6gv46JR+LzYL6JEFNOKltgf6dWaeV4IqsJ4psU+GbkVjSE6pmj7/NMfIr7pxamfbtcnxjTFtqrVxpygmo2Iq5du5WghZGm7fM5+T0/dzNAWpLFJ7XJAv/4ddMJeLauawe/6YXnvmrvjh+XYJbMZPmwdO/AlmEODuuGbKDbvf/zlAxMWS4aVr9y6CnlnbVeReLIjE11VPkf5z/i6RUm4A3Unop7JGD6BawzBtXG+frSuwAPRA7As8y7jPUbvKwYP6XDzFAg+5Air0IEL1a1s3eHKC89aoUo80WefrrSu+V7AMjtuKMNmhHyhW3E6m0L5PPMSXewBMlYPloPf+uLQ==")
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