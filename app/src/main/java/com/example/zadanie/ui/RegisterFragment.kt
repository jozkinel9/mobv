package com.example.zadanie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zadanie.R
import com.example.zadanie.databinding.RegisterFragmentBinding
import com.example.zadanie.ui.viewModels.RegisterViewModel
import com.opinyour.android.app.data.utils.Injection

class RegisterFragment : Fragment() {
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)
        registerViewModel =
            ViewModelProvider(this, Injection.provideViewModelFactory(requireContext()))
                .get(RegisterViewModel::class.java)
        binding.registerModel = registerViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//      nav to HomeFragment
        binding.backBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }
    }
}