package com.example.binahoverflow.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.binahoverflow.databinding.BinahWebViewFragmentLayoutBinding

class BinahWebViewFragment: Fragment() {

    private lateinit var binding: BinahWebViewFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = BinahWebViewFragmentLayoutBinding.inflate(inflater,container,false)

        val urlFromRecycler = arguments?.getString("URL") ?: "https://stackoverflow.com"

        binding.binahWebView.loadUrl(urlFromRecycler)

        return binding.root
    }
}