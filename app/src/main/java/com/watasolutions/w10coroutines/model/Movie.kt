package com.watasolutions.w10coroutines.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genreIDS: List<Long>? = null,
    val id: Long? = null,

    @SerializedName("original_language")
    val originalLanguage: OriginalLanguage? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Long? = null
)
