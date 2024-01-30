package com.example.data.api

import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.repository.NewsRepository
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


fun createNewsApiService(): NewsApiService {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    return retrofit.create(NewsApiService::class.java)
}

val networkModule = module {
    single { createNewsApiService() }
}

val repositoryModule = module {
    single<NewsRepository> { NewsRepositoryImpl(get()) }
}
