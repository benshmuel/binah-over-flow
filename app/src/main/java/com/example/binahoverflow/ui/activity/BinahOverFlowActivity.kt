package com.example.binahoverflow.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.binahoverflow.R
import com.example.binahoverflow.databinding.BinahOverFlowActivityBinding

class BinahOverFlowActivity : AppCompatActivity() ,LoaderAware{

    private lateinit var binding :BinahOverFlowActivityBinding

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BinahOverFlowActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun handleLoaderAnimation(visibility: Int) {
        binding.loader.visibility = visibility
    }
}