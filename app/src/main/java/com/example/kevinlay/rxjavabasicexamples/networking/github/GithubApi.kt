package com.example.kevinlay.rxjavabasicexamples.networking.github

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by kevinlay on 6/19/18.
 */
interface GithubApi {

    @GET("users/{username}/repos")
    fun getUserInfo(@Path("username") username: String): Single<List<GithubModel>>
}