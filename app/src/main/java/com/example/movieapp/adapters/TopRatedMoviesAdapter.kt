package com.example.movieapp.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.PopularMoviesItemBinding
import com.example.movieapp.pojo.MovieDetails
import com.example.movieapp.pojo.PopularMovies
import com.example.movieapp.pojo.Result
import com.example.movieapp.pojo.topRated

class TopRatedMoviesAdapter :RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMoviesViewHolder>() {


    private var topRatedMovieList=ArrayList<Result>()
    private lateinit var setOnMovieClickListener: SetOnMovieClickListener



    fun setMovies(topRatedMovieList: ArrayList<Result>){
        this.topRatedMovieList=topRatedMovieList
        notifyDataSetChanged()
    }
    fun setOnMovieClickListener(setOnMovieClickListener: SetOnMovieClickListener) {
        this.setOnMovieClickListener = setOnMovieClickListener
    }

    class TopRatedMoviesViewHolder(val binding:PopularMoviesItemBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {
        return TopRatedMoviesViewHolder(PopularMoviesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {

        val imageUrl="https://image.tmdb.org/t/p/w500"
        Glide.with(holder.itemView)
            .load(imageUrl+topRatedMovieList[position].poster_path)
            .into(holder.binding.imgPopularMoviesItem)

        holder.binding.tvPopularMoviesItem.text=topRatedMovieList[position].title

        holder.itemView.setOnClickListener {
            setOnMovieClickListener.setOnClickListener(topRatedMovieList[position])
        }


        /* holder.itemView.setOnClickListener {movie ->
             movie.id
             val context=holder.itemView.context
             val intent = Intent( context, com.example.movieapp.activities.MovieDetails::class.java)
             intent.putExtra("id",movie.id)
             context.startActivity(intent)

         }*/



    }

    override fun getItemCount(): Int {
        return topRatedMovieList.size
    }

}

interface SetOnTopRatedMovieClickListener {
    fun setOnClickListener(movie: Result)
}
