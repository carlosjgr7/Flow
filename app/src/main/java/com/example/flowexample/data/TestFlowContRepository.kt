package com.example.flowexample.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestFlowContRepository {
    val counter: Flow<Int> = flow {
        var counter = 0
        while (true){
            counter++
            emit(counter)
            delay(1000)
        }
    }

}