package com.example.mvvmmaster

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmmaster.databinding.AdapterMovieBinding
import com.example.mvvmmaster.retrofit.model.Movie
import kotlinx.android.synthetic.main.adapter_movie.view.*

class MainAdapter(var activity: Activity) : RecyclerView.Adapter<MainViewHolder>() {

    var movies = mutableListOf<Movie>()

    //method get movie list from model view
    fun setMovieList(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    // create view for each raw of adapter item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie, parent, false)
        return MainViewHolder(view)
    }

    //bind data to view holder xml
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.name.text = movie.name
        Glide.with(activity).load(movie.imageUrl).into(holder.imageview)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

//responsible to create raw for adapter list
class MainViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageview: ImageView = itemView.imageview
    var name: TextView = itemView.name


}