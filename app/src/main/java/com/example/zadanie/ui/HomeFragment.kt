package com.example.zadanie.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.example.zadanie.R
import com.example.zadanie.databinding.FragmentHomeBinding
import com.example.zadanie.ui.viewModels.DatabaseViewModel
import com.example.zadanie.ui.viewModels.HomeViewModel
import com.opinyour.android.app.data.utils.Injection


class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel =
            ViewModelProvider(this, Injection.provideViewModelFactory(requireContext()))
                .get(HomeViewModel::class.java)
        binding.homeModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        homeViewModel.transformedText.observe(viewLifecycleOwner) {
//            Log.d("nasapremenna", "je: $it")
//        }

//      nav to DatabaseFragment
        binding.databaseBtn.setOnClickListener {
            it.findNavController().navigate(R.id.go_to_database)
        }

        binding.loginBtn.setOnClickListener {
            homeViewModel.checkUserPin(it.findNavController())
        }

////      nav to BasicFragment
//        binding.loginBtn.setOnClickListener {
//            it.findNavController().navigate(R.id.action_homeFragment_to_basicFragment)
//        }

//      nav to RegisterFragment
        binding.registerBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
        }
    }
}