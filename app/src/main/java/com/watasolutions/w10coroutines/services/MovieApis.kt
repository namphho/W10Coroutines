package com.watasolutions.w10coroutines.services

import com.watasolutions.w10coroutines.model.MovieResp
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApis {

    @GET("movie/now_playing")
    suspend fun listNowPlayMovies(
        @Query("language") language: String, @Query("page") page: Int,
    ): MovieResp

    //    ///movie/upcoming
    @GET("movie/upcoming")
    suspend fun listUpComingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MovieResp
}