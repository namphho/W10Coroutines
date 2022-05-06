package com.watasolutions.w10coroutines.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watasolutions.w10coroutines.services.MovieRestClient
import kotlinx.coroutines.*

class LoginViewModel : ViewModel() {
    companion object {
        val TAG: String = LoginViewModel::class.java.name
    }

    fun getNowPlaying() {
        viewModelScope.launch {
            val movieResp =
                MovieRestClient.getInstance().api.listNowPlayMovies(language = "en-US", page = 1)
            Log.e("getNowPlaying", movieResp.results.toString())
        }
    }

    fun getComingUpMovie() {
        viewModelScope.launch {
            val movieResp =
                MovieRestClient.getInstance().api.listUpComingMovies(language = "en-US", page = 1)
            Log.e("getComingUpMovie", movieResp.results.toString())
        }
    }

    fun getNowPlayingAndComingUpMovieInSequences() {
        viewModelScope.launch {
            //Log.e(TAG, Thread.currentThread().toString())
            val startTime = System.currentTimeMillis()
            val listOfNowPlayResp = MovieRestClient.getInstance().api.listNowPlayMovies(
                language = "en-US",
                page = 1
            )

            val listOfComingUpResp =
                MovieRestClient.getInstance().api.listUpComingMovies(language = "en-US", page = 1)
            val endTime = System.currentTimeMillis()
            getDuration(startTime, endTime)
            Log.e(
                "Sequences",
                "total now: ${listOfNowPlayResp.results?.size} -- comingup: ${listOfComingUpResp.results?.size}"
            )
        }
    }


    fun getNowPlayAndComingUpInParallel() {
        viewModelScope.launch {
            val defers = listOf(
                async {
                    MovieRestClient.getInstance().api.listNowPlayMovies(
                        language = "en-US",
                        page = 1
                    )
                },
                async {
                    MovieRestClient.getInstance().api.listUpComingMovies(
                        language = "en-US",
                        page = 1
                    )
                }
            )
            val startTime = System.currentTimeMillis()
            val result = defers.awaitAll()
            val endTime = System.currentTimeMillis()
            val listOfNowPlay = result[0]
            val listOfComingUp = result[1]
            getDuration(startTime, endTime)
            Log.e(
                "Parallel",
                "total now: ${listOfNowPlay.results?.size} -- comingup: ${listOfComingUp.results?.size}"
            )
        }
    }

    fun runSuspendFunctionsInSequences() {
        viewModelScope.launch {
            val deferred1 = async {
                delay(1000L)
            }
            val deferred2 = async {
                delay(2000L)
            }
            val startTime = System.currentTimeMillis()
            deferred1.await()
            deferred2.await()
            val endTime = System.currentTimeMillis()
            getDuration(startTime, endTime)
        }
    }

    fun runSuspendFunctionsInParallels() {
        viewModelScope.launch {
            val deferred = listOf(
                async {
                    delay(1000L)
                },
                async {
                    delay(2000L)
                }
            )
            val startTime = System.currentTimeMillis()
            deferred.awaitAll()
            val endTime = System.currentTimeMillis()
            getDuration(startTime, endTime)
        }
    }

    private fun getDuration(start: Long, end: Long) {
        val duration = (end - start)
        Log.e("time", "consume time: $duration ms")
    }
}