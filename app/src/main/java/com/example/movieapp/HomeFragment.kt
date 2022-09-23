package com.example.movieapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.adapters.PopularMoviesAdapter
import com.example.movieapp.adapters.SetOnMovieClickListener
import com.example.movieapp.adapters.TopRatedMoviesAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.pojo.Result
import com.example.movieapp.viewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm:HomeViewModel
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    private var id=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm= ViewModelProvider(this)[HomeViewModel::class.java]
        popularMoviesAdapter = PopularMoviesAdapter()
        topRatedMoviesAdapter= TopRatedMoviesAdapter()



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePopularItemsRecyclerView()
        prepareTopRatedItemsRecyclerView()

            homeMvvm.getPopularMovies()
            homeMvvm.getTopRatedMovies()


        observertopRatedMoviesLiveData()
        observerPopularMealsLiveData()

        popularMoviesAdapter.setOnMovieClickListener(object : SetOnMovieClickListener {
            override fun setOnClickListener(movie: Result) {
                val intent = Intent(activity, MovieDetails::class.java)

                intent.putExtra("MOVIE_ID",movie.id.toString())
                intent.putExtra("MOVIE_NAME",movie.title.toString())
                intent.putExtra("MOVIE_THUMB",movie.poster_path.toString())
                Log.d("title",movie.title)
                startActivity(intent)
            }

        })
        topRatedMoviesAdapter.setOnMovieClickListener(object : SetOnMovieClickListener {
            override fun setOnClickListener(movie: Result) {
                val intent = Intent(activity, MovieDetails::class.java)

                intent.putExtra("MOVIE_ID",movie.id.toString())
                intent.putExtra("MOVIE_NAME",movie.title.toString())
                intent.putExtra("MOVIE_THUMB",movie.poster_path.toString())
                Log.d("title",movie.title)
                startActivity(intent)
            }

        })



    }



    private fun preparePopularItemsRecyclerView() {
        binding.recPopularMovies.apply {
            layoutManager =LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter=popularMoviesAdapter
        }
    }
    private fun prepareTopRatedItemsRecyclerView() {
        binding.recTopRatedMovies.apply {
            layoutManager =LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter=topRatedMoviesAdapter
        }
    }
    private fun observerPopularMealsLiveData() {
        homeMvvm.observePopularMovies().observe(viewLifecycleOwner) { movieList ->
            popularMoviesAdapter.setMovies(popularMoviesList = movieList as ArrayList<Result>)
        }
    }
    private fun observertopRatedMoviesLiveData() {
        homeMvvm.observeTopRatedMovies().observe(viewLifecycleOwner) { movieList ->
            topRatedMoviesAdapter.setMovies(topRatedMovieList = movieList as ArrayList<Result>)
        }
    }



}