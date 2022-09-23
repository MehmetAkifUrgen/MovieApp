package com.example.movieapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.pojo.PopularMovies
import com.example.movieapp.pojo.Result
import com.example.movieapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private var getPopularMoviesLiveData = MutableLiveData<List<Result>>()
    private var getTopRatedMoviesLiveData = MutableLiveData<List<Result>>()
     fun getPopularMovies(){
        GlobalScope.launch(Dispatchers.IO){
            RetrofitInstance.api.getPopularMovies()
                .enqueue(object : Callback<PopularMovies> {
                    override fun onResponse(
                        call: Call<PopularMovies>,
                        response: Response<PopularMovies>
                    ) {
                        if (response.body() != null) {
                            getPopularMoviesLiveData.value=response.body()!!.results
                            Log.e("daraaa",getPopularMoviesLiveData.value.toString())

                        }
                        else Log.e("hataaaa","okeee")
                    }

                    override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                        Log.d("Home Fragment",t.message.toString())
                    }
                })
        }
    }
     fun getTopRatedMovies(){
        GlobalScope.launch(Dispatchers.IO){
            RetrofitInstance.api.getTopRated()
                .enqueue(object : Callback<PopularMovies> {
                    override fun onResponse(
                        call: Call<PopularMovies>,
                        response: Response<PopularMovies>
                    ) {
                        if (response.body() != null) {
                            getTopRatedMoviesLiveData.value=response.body()!!.results
                            Log.e("daraaa",getTopRatedMoviesLiveData.value.toString())

                        }
                        else Log.e("hataaaa","okeee")
                    }

                    override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                        Log.d("Home Fragment",t.message.toString())
                    }
                })
        }
    }

    fun observePopularMovies(): LiveData<List<Result>> {
        return getPopularMoviesLiveData
    }
    fun observeTopRatedMovies(): LiveData<List<Result>> {
        return getTopRatedMoviesLiveData
    }
}