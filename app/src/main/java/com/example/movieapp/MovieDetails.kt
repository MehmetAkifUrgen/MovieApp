package com.example.movieapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.adapters.SetOnMovieClickListener
import com.example.movieapp.databinding.ActivityMovieDetailsBinding
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.pojo.MovieDetails
import com.example.movieapp.pojo.Result
import com.example.movieapp.viewModel.MovieDetailViewModel


class MovieDetails : AppCompatActivity() {

    private lateinit var binding:ActivityMovieDetailsBinding
    private lateinit var movieDetailsMvvm:MovieDetailViewModel
    private lateinit var movieId:String
    private lateinit var movieName:String
    private lateinit var movieThumb:String





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieDetailsMvvm=ViewModelProvider(this)[MovieDetailViewModel::class.java]
        getMovieInfoFromIntent()
        movieDetailsMvvm.getMovieDetails(movieId)
        observerMovieDetailsLiveData()

    }

    private fun observerMovieDetailsLiveData() {
        val imageUrl="https://image.tmdb.org/t/p/w500"
        movieDetailsMvvm.observeMovieDetails().observe(this
        ) { t ->
            var movie = t.toList()
            var languages=movie.get(0).spoken_languages.toList().map { item -> item.name }.toString()
            var category=movie.get(0).genres.toList().map { item -> item.name }.toString()
            Log.e("daa",movie.get(0).backdrop_path.toString())
            Glide.with(applicationContext)
                .load(imageUrl+movie.get(0).backdrop_path)
                .into(binding.imgMovieDetail)

            binding.textArea.text = "Area : $languages"
            binding.ratingText.text=movie!!.get(0).vote_average.toString()
            binding.textCategory.text="Genres : $category"
            binding.textInstructionsText.text = movie!!.get(0).overview
            binding.collapsingToolBar.title = ""
            binding.movieName.text=movie!!.get(0).title
            binding.collapsingToolBar.setCollapsedTitleTextColor(Color.WHITE)
            binding.collapsingToolBar.setExpandedTitleColor(Color.WHITE)
        }
    }


    private fun getTempDatas() {
        binding.collapsingToolBar.title=movieName

    }
    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(movieThumb)
            .into(binding.imgMovieDetail)
        binding.collapsingToolBar.title =  movieName
        binding.collapsingToolBar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolBar.setExpandedTitleColor(resources.getColor(R.color.white))
    }
    private fun loadingCase(){
        binding.progressBar.visibility=View.VISIBLE
        binding.textCategory.visibility=View.INVISIBLE
        binding.textArea.visibility=View.INVISIBLE
        binding.textInstructions.visibility=View.INVISIBLE
        binding.textInstructionsText.visibility=View.INVISIBLE
        binding.btnAddToFav.visibility=View.INVISIBLE
    }
    private fun onResponseCase(){
        binding.progressBar.visibility=View.INVISIBLE
        binding.textCategory.visibility=View.VISIBLE
        binding.textArea.visibility=View.VISIBLE
        binding.textInstructions.visibility=View.VISIBLE
        binding.textInstructionsText.visibility=View.VISIBLE
        binding.btnAddToFav.visibility=View.VISIBLE
    }


    private fun getMovieInfoFromIntent() {
        val iin = intent

        movieId= iin.getStringExtra("MOVIE_ID").toString()
        movieName= iin.getStringExtra("MOVIE_NAME").toString()



    }




}


