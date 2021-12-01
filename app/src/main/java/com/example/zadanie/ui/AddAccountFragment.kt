package com.example.zadanie.ui

import android.content.Intent
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
import com.example.zadanie.databinding.AddAccountFragmentBinding
import com.example.zadanie.databinding.BasicFragmentBinding
import com.example.zadanie.databinding.RegisterFragmentBinding
import com.example.zadanie.ui.viewModels.AddAccountViewModel
import com.example.zadanie.ui.viewModels.BasicViewModel
import com.example.zadanie.ui.viewModels.DatabaseViewModel
import com.example.zadanie.ui.viewModels.RegisterViewModel
import com.opinyour.android.app.data.utils.Injection

class AddAccountFragment : Fragment() {
    private lateinit var addAccountViewModel: AddAccountViewModel
    private lateinit var binding: AddAccountFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_account_fragment, container, false)
        addAccountViewModel =
            ViewModelProvider(this, Injection.provideViewModelFactory(requireContext()))
                .get(AddAccountViewModel::class.java)
        binding.addAccountModel = addAccountViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//      nav to HomeFragment
        binding.backBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_addAccountFragment_to_homeFragment)
        }
    }
}