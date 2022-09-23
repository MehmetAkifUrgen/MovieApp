package com.example.movieapp.retrofit


import com.example.movieapp.pojo.MovieDetails
import com.example.movieapp.pojo.PopularMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular?api_key=b953ac9f9bd22f92fd0cc94a9cc906b1")
     fun  getPopularMovies() : Call<PopularMovies>

    @GET("movie/{id}?api_key=b953ac9f9bd22f92fd0cc94a9cc906b1")
     fun getMovieDetails(@Path("id") id:String):Call<MovieDetails>
    @GET("movie/top_rated?api_key=b953ac9f9bd22f92fd0cc94a9cc906b1")
     fun  getTopRated() : Call<PopularMovies>

}