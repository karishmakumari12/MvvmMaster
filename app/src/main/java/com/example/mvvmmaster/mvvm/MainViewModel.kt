package com.example.mvvmmaster.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmmaster.retrofit.model.Movie
import com.example.mvvmmaster.retrofit.service.ServiceBuilder
import com.example.mvvmmaster.retrofit.serviceinterface.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()

    //calling retofit api
    fun getAllMovies() {
        val call: ApiInterface = ServiceBuilder.buildService(ApiInterface::class.java)
        val response = call.getAllMovies()
        response.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {

                //setting api response to MutableLiveData object
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}