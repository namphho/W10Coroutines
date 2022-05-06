package com.watasolutions.w10coroutines.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRestClient {
    private var _api : MovieApis

    val api : MovieApis
        get() = _api

    init {
        _api = createMovieDBService()
    }

    companion object {
        private var mInstance: MovieRestClient? = null

        fun getInstance() = mInstance ?: synchronized(this){
            mInstance ?: MovieRestClient().also { mInstance = it }
        }
    }

    private fun createMovieDBService() : MovieApis{
        //create okhttp
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor())
            .build()

        //create retrofit
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        return retrofit.create(MovieApis::class.java)
    }
}
