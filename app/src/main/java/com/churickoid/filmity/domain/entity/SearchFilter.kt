package com.churickoid.filmity.domain.entity

data class SearchFilter(
    val yearBottom: Int = 1980,
    val yearTop: Int = 2022,
    val ratingBottom: Int = 6,
    val ratingTop: Int = 9,
    val order:OrderFilter = OrderFilter.NUM_VOTE,
    val type: Type = Type.FILM,
    val genres: List<ItemFilter> = listOf(),
    val countries: List<ItemFilter> = listOf()

)
enum class OrderFilter{
    NUM_VOTE,RATING,YEAR
}
enum class Type{
    FILM,TV_SERIES,ALL
}