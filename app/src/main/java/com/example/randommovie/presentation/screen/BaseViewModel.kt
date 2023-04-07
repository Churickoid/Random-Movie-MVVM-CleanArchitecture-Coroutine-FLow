package com.example.randommovie.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randommovie.domain.entity.ActionsAndMovie
import com.example.randommovie.domain.usecases.list.AddUserInfoForMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BaseViewModel(private val addUserInfoForMovieUseCase: AddUserInfoForMovieUseCase) :
    ViewModel() {

    val color = MutableStateFlow(Pair(0x3700B3, 0xffffff))
    fun setColor(colorMain: Int, colorBack: Int) {
        color.value = Pair(colorMain, colorBack)
    }

    fun addRatedMovie(actionsAndMovie: ActionsAndMovie) {
        viewModelScope.launch {
            addUserInfoForMovieUseCase(actionsAndMovie)
        }
    }
}