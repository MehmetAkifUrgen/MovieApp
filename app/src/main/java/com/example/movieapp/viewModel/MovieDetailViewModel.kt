package com.example.movieapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.pojo.MovieDetails
import com.example.movieapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(): ViewModel() {
    private var getMovieDetailsMutableLiveData = MutableLiveData<List<MovieDetails>>()

     fun getMovieDetails(id:String) {
        GlobalScope.launch(Dispatchers.IO){
            RetrofitInstance.api.getMovieDetails(id)
                .enqueue(object : Callback<MovieDetails>{
                    override fun onResponse(
                        call: Call<MovieDetails>,
                        response: Response<MovieDetails>
                    ) {
                        getMovieDetailsMutableLiveData.value = listOf(response.body()!!)
                    }

                    override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
        }
    }

    fun observeMovieDetails() : LiveData<List<MovieDetails>> {
        return getMovieDetailsMutableLiveData
    }

    }






