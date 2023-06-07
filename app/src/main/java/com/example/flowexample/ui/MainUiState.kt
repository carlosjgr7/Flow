package com.example.flowexample.ui

sealed class MainUiState{
    object NotStarted:MainUiState()
    data class Success(val number:Int):MainUiState()
    data class  Error(val msg:String):MainUiState()
}