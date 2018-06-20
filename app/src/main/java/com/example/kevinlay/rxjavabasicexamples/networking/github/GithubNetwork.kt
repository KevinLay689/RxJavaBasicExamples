package com.example.kevinlay.rxjavabasicexamples.networking.github

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by kevinlay on 6/19/18.
 */
class GithubNetwork {

    fun getInfo(username: String): Single<List<GithubModel>> {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GithubApi::class.java)
                .getUserInfo(username)
    }
}