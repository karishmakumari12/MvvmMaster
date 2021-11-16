package com.example.mvvmmaster.retrofit.serviceinterface

import com.example.mvvmmaster.retrofit.model.Movie
import com.example.mvvmmaster.retrofit.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("{fullUrl}")
    fun getEmployeeDetails(@Path(value = "fullUrl", encoded = true) fullUrl: String): Call<MovieResponse>

    @GET("movielist.json")
    fun getAllMovies() : Call<List<Movie>>


}