package com.example.binahoverflow.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.binahoverflow.R
import com.example.binahoverflow.adapters.binahAdapter
import com.example.binahoverflow.databinding.BinaListFragmentLayoutBinding
import com.example.binahoverflow.viewmodels.BinahListViewModel

class BinahListFragment : Fragment() {

    val TAG = "HomeAssignment"
    private lateinit var binding: BinaListFragmentLayoutBinding

    private val viewModel: BinahListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = BinaListFragmentLayoutBinding.inflate(inflater, container, false)


        val binahAdapter = binahAdapter(OnAdapterItemClicked())


        binding.binahRecycler.apply {
            adapter = binahAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


        binding.filterToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.filterAnswered()
            } else {
                viewModel.filterUnAnswered()
            }
        }


        viewModel.getQuestions().observe(viewLifecycleOwner) {

            it.forEach { Log.d(TAG, "onCreateView: $it") }

            binahAdapter.submitList(it)


        }


        return binding.root
    }


    inner class OnAdapterItemClicked : binahAdapter.OnItemClicked {
        override fun goToWebView(withUrl: String) {
            val bundle: Bundle = Bundle().apply {
                putString("URL", withUrl)
            }
            findNavController().navigate(
                R.id.action_binahQuestionsFragment_to_binahWebViewFragment,
                bundle
            )

        }

    }
}