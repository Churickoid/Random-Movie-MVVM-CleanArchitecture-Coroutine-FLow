package com.example.randommovie.data.request.filter

import com.example.randommovie.domain.entity.ItemFilter

data class Country(
    val country: String,
    val id: Int
){
    fun toItemFilter(): ItemFilter {
        return ItemFilter(id,country)
    }
}