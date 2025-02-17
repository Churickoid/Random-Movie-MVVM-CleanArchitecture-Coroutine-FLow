package com.churickoid.filmity.data.retrofit.movie

import com.churickoid.filmity.data.retrofit.movie.entity.filter.GenresAndCountriesRequest
import com.churickoid.filmity.data.retrofit.movie.entity.id.MovieIdRequest
import com.churickoid.filmity.data.retrofit.movie.entity.list.MovieListRequest
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("api/v2.2/films")
    suspend fun getMovieList(
        @Header("X-API-KEY") token:String,
        @Query("page") page: Int = 1,
        @Query("genres") genre: Int?,
        @Query("countries") country: Int?,
        @Query("order") order: String = "RATING",
        @Query("type") type: String = "FILM",
        @Query("ratingFrom") ratingFrom: Int = 1,
        @Query("ratingTo") ratingTo: Int = 10,
        @Query("yearFrom") yearFrom: Int = 1000,
        @Query("yearTo") yearTo: Int = 3000,
    ): MovieListRequest

    @GET("api/v2.2/films/{id}")
    suspend fun getMovieById(@Header("X-API-KEY") token:String, @Path("id") id: Long): MovieIdRequest
    @GET("api/v2.2/films/filters")
    suspend fun getGenresAndCounties(@Header("X-API-KEY") token:String): GenresAndCountriesRequest

}