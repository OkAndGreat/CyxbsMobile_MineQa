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
                            "Bearer eyJEYXRhIjp7ImdlbmRlciI6IueUtyIsInN0dV9udW0iOiIyMDE5MjExNjg1In0sIkRvbWFpbiI6Im1hZ2lwb2tlIiwiUmVkaWQiOiI1NGUxNzEzZTQ5MjUyMTcyNmMzZGQ3ZTg4Mzk1NDcxNzJhZTk1ZTlhIiwiZXhwIjoiNzM5ODg5NzcwOSIsImlhdCI6IjE2MjkwODY0NTYiLCJzdWIiOiJ3ZWIifQ==.SuuObkTr46P2MRom9rT6FfY1pp60EFNdHP4hheocmoK7+gtDvk6P72cCdRI8JJzM30+kz3tJv7kBZy+9Cp/YIAotiL/QN8ZNY8Z7weMH6yHudtneOpOMVOBejDsNgsj1FW+LJwMl+rk5Tdmt3dOMIGtyPo9vWzIAUeH3fBNbnWwL24jPrEkFCY6A++lj0l34YUBFpEUjTX58UsDZfh4BaPt0kY7GBzT/yfFXwVYdhYRdgpGgjgvOqtSURQT1IXIJHO46Kncsgea9UVL5SZbiiVXHQKlmx/hlRUTCOse6Y1PMEH6QWkHPUJaVXFr72CXxtJsQKVkmBP9td6UaCnuyQQ==")
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