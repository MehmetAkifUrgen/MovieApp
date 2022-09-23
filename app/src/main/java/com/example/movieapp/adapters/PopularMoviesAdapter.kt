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

class PopularMoviesAdapter :RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {


    private var popularMoviesList=ArrayList<Result>()
    private lateinit var setOnMovieClickListener: SetOnMovieClickListener



    fun setMovies(popularMoviesList: ArrayList<Result>){
        this.popularMoviesList=popularMoviesList
        notifyDataSetChanged()
    }
    fun setOnMovieClickListener(setOnMovieClickListener: SetOnMovieClickListener) {
        this.setOnMovieClickListener = setOnMovieClickListener
    }

    class PopularMoviesViewHolder(val binding:PopularMoviesItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(PopularMoviesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        Log.e("photo",popularMoviesList[position].poster_path)
        val imageUrl="https://image.tmdb.org/t/p/w500"
        Glide.with(holder.itemView)
            .load(imageUrl+popularMoviesList[position].poster_path)
            .into(holder.binding.imgPopularMoviesItem)

        holder.binding.tvPopularMoviesItem.text=popularMoviesList[position].title

        holder.itemView.setOnClickListener {
            setOnMovieClickListener.setOnClickListener(popularMoviesList[position])
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
        return popularMoviesList.size
    }

}

interface SetOnMovieClickListener {
    fun setOnClickListener(movie: Result)
}
