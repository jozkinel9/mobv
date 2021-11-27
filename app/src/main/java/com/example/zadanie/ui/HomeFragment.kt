package com.example.zadanie.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.example.zadanie.R
import com.example.zadanie.databinding.FragmentHomeBinding
import com.example.zadanie.ui.viewModels.HomeViewModel


class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.nasmodel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.transformedText.observe(viewLifecycleOwner) {
            Log.d("nasapremenna", "je: $it")
        }

        binding.databaseBtn.setOnClickListener {
            it.findNavController().navigate(R.id.go_to_database)
        }
    }
}