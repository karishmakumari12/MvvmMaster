package com.velmurugan.mvvmretrofitrecyclerviewkotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmmaster.MainAdapter
import com.example.mvvmmaster.R
import com.example.mvvmmaster.mvvm.MainViewModel
import com.example.mvvmmaster.mvvm.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create adapter object
        val adapter: MainAdapter = MainAdapter(this)
        // set adapter object to recyclerview
        recyclerview.adapter = adapter

        //get view model class object
        viewModel =
            ViewModelProvider(this, MyViewModelFactory()).get(
                MainViewModel::class.java
            )
        //call view holder method to call retofit API
        viewModel.getAllMovies()

        //obserb retofit API response from view model class
        viewModel.movieList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")

            //set api response or send api response to adapter class to create list
            adapter.setMovieList(it)
        })

        //observe retrofit api error
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this,"error $it",Toast.LENGTH_SHORT).show()

        })
    }
}