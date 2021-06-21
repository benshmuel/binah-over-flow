package com.example.binahoverflow.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.binahoverflow.R
import com.example.binahoverflow.adapters.BinahAdapter
import com.example.binahoverflow.databinding.BinaListFragmentLayoutBinding
import com.example.binahoverflow.ui.activity.LoaderAware
import com.example.binahoverflow.viewmodels.BinahListViewModel

class BinahListFragment : Fragment() {

    private lateinit var mActivityRef: LoaderAware

    private lateinit var binding: BinaListFragmentLayoutBinding

    private val viewModel: BinahListViewModel by viewModels()

    companion object {

        //Just in case if something went wrong the the link to the question.
        const val DEFAULT_URL_TO_LOAD = "https://stackoverflow.com"

        const val BUNDLE_VALUE_URL: String = "URL"

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivityRef = context as LoaderAware
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Setting binding
        binding = BinaListFragmentLayoutBinding.inflate(inflater, container, false)

        //init the recyclerview adapter
        val binahAdapter = BinahAdapter(OnAdapterItemClicked())

        //Config recycler
        binding.binahRecycler.apply {
            adapter = binahAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        //Setting the observer for fetching question (api)
        viewModel.getQuestions().observe(viewLifecycleOwner) {
            //Submitting to the adapter
            binahAdapter.submitList(it)
            //Just a simple count title.
            binding.resultCounter.text = resources.getString(R.string.results, it.size.toString())
            //Turning off the loader.
            mActivityRef.handleLoaderAnimation(View.GONE)
        }

        setFilterLogic()

        return binding.root
    }

    private fun setFilterLogic() {
        binding.filterToggle.setOnCheckedChangeListener { _, isChecked ->
            mActivityRef.handleLoaderAnimation(View.VISIBLE)
            if (isChecked) {
                viewModel.filterAnswered()
            } else {
                viewModel.filterUnAnswered()
            }
        }
    }


    inner class OnAdapterItemClicked : BinahAdapter.OnItemClicked {
        override fun goToWebView(withUrl: String) {
            mActivityRef.handleLoaderAnimation(View.VISIBLE)

            //Building Bundle with the question url to be passed next.
            val bundle: Bundle = Bundle().apply {
                putString(BUNDLE_VALUE_URL, withUrl)
            }
            findNavController().navigate(
                R.id.action_binahQuestionsFragment_to_binahWebViewFragment,
                bundle
            )
        }
    }
}