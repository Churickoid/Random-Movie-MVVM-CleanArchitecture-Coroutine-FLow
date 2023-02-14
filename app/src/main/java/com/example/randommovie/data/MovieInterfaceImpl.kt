package com.example.randommovie.data

import android.util.Log
import com.example.randommovie.data.request.requestEntity.Item
import com.example.randommovie.domain.MovieInterface
import com.example.randommovie.domain.entity.Movie
import com.example.randommovie.domain.entity.MovieExtension
import com.example.randommovie.domain.entity.SearchFilter
import kotlin.random.Random

class MovieInterfaceImpl(private val retrofitMovieApiInterface: RetrofitMovieApiInterface) :
    MovieInterface {
    override suspend fun getRandomMovie(searchFilter: SearchFilter): Movie {
        val randYear = Random.nextInt(searchFilter.yearBottom, searchFilter.yearTop + 1)
        val randRating = Random.nextInt(searchFilter.ratingBottom, searchFilter.ratingTop)
      //  val genre = if (searchFilter.genre != null) searchFilter.genre!!
       // else Random.nextInt(1, 7)

        val randPage = Random.nextInt(1, 6)
        val movieList = retrofitMovieApiInterface.getMovieList(
            page = randPage,
            yearFrom = randYear,
            yearTo = randYear,
            ratingFrom = randRating,
            ratingTo = randRating + 1,
            //genre = genre
        ).items
        val randomItemId = Random.nextInt(movieList.size)
        Log.e("!!!", movieList[randomItemId].toString())
        return itemToMovie(movieList[randomItemId])
    }

    override fun showMoreInformation(movie: Movie): MovieExtension {
        TODO("Not yet implemented")
    }

    override fun addMustWatchedMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun addRatedMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    private fun itemToMovie(item: Item): Movie {
        return Movie(
            id = item.kinopoiskId,
            titleRu = item.nameRu,
            title = item.nameOriginal,
            poster = item.posterUrlPreview,
            genre = if (item.genres.isNotEmpty()) item.genres[0].genre else "",
            releaseDate = item.year,
            ratingKP = item.ratingKinopoisk,
            ratingIMDB = item.ratingImdb,
            country = if (item.countries.isNotEmpty()) item.countries[0].country else ""
        )
    }

}