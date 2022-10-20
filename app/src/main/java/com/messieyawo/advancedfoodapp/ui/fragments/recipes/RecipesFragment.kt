package com.messieyawo.advancedfoodapp.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.messieyawo.advancedfoodapp.R
import com.messieyawo.advancedfoodapp.data.adapters.RecipesAdapter
import com.messieyawo.advancedfoodapp.data.viewmodel.MainViewModel
import com.messieyawo.advancedfoodapp.data.viewmodel.RecipesViewModel
import com.messieyawo.advancedfoodapp.databinding.FragmentRecipesBinding
import com.messieyawo.advancedfoodapp.utils.NetworkResult
import com.messieyawo.advancedfoodapp.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private val args by navArgs<RecipesFragmentArgs>()

private lateinit var recyclerView: RecyclerView
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy {
        RecipesAdapter()
    }
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private lateinit var recipesViewModel: RecipesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        setUpRecyclerView()
        readDatabase()

         /**
          * show recipe bottom sheett
          * **/
        binding.recipesFab.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_rcipeBottomSheetFragment)

        }
    }




    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner) { database ->
                if (database.isNotEmpty() && !args.backFromBottomSheet) {
                    Log.d("RecipesFragment", "readDatabase called!")
                    mAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()
                } else {
                    requestApiData()
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }


    private fun requestApiData() {
        Log.d("RecipesFragment", "requestApiData called!")
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                   // loadDataFromCache()
                    response.data?.let { mAdapter.setData(it) }
                   // recipesViewModel.saveMealAndDietType()
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }



    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].foodRecipe)
                }
            }
        }
    }

    private fun showShimmerEffect() {
        binding.shimmerLayout.startShimmer()
        binding.recyclerview.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE
        binding.recyclerview.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}