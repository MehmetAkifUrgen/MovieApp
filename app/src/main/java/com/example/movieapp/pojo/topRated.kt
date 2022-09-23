package com.example.movieapp.pojo

data class topRated(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)