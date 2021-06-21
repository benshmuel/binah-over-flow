package com.example.binahoverflow.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.binahoverflow.databinding.BinahWebViewFragmentLayoutBinding
import com.example.binahoverflow.ui.activity.LoaderAware
import com.example.binahoverflow.ui.fragment.BinahListFragment.Companion.BUNDLE_VALUE_URL
import com.example.binahoverflow.ui.fragment.BinahListFragment.Companion.DEFAULT_URL_TO_LOAD

class BinahWebViewFragment : Fragment() {

    private lateinit var mActivityRef: LoaderAware

    private lateinit var binding: BinahWebViewFragmentLayoutBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivityRef = context as LoaderAware
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Init binding
        binding = BinahWebViewFragmentLayoutBinding.inflate(inflater, container, false)

        //Getting the url for the answer
        val urlFromRecycler = arguments?.getString(BUNDLE_VALUE_URL) ?: DEFAULT_URL_TO_LOAD

        //Loading it via WebView
        binding.binahWebView.loadUrl(urlFromRecycler)

        //hiding loader animation.
        mActivityRef.handleLoaderAnimation(View.GONE)

        return binding.root
    }
}