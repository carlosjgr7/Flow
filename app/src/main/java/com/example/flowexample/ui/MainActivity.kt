package com.example.flowexample.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.flowexample.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var count: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            viewModel.uiState.collect {uiState->
                when(uiState){
                    is MainUiState.Error -> binding.tvCount.text =  uiState.msg
                    MainUiState.NotStarted -> {binding.tvCount.text  = "Sin iniciar"}
                    is MainUiState.Success -> binding.tvCount.text  = uiState.number.toString()
                }
            }
        }

        binding.btnStart.setOnClickListener {
            viewModel.exampleFlowStart()
        }


    }
}