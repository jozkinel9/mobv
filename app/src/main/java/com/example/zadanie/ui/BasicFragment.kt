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
import com.example.zadanie.databinding.BasicFragmentBinding
import com.example.zadanie.ui.viewModels.BasicViewModel
import com.opinyour.android.app.data.utils.Injection

class BasicFragment : Fragment() {
    private lateinit var basicViewModel: BasicViewModel
    private lateinit var binding: BasicFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.basic_fragment, container, false)
        basicViewModel =
            ViewModelProvider(this, Injection.provideViewModelFactory(requireContext()))
                .get(BasicViewModel::class.java)
        binding.basicModel = basicViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // nav to ContactsFragment
        binding.contactsBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_basicFragment_to_contactsFragment)
        }

        // nav to HomeFragment
        binding.logoutBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_basicFragment_to_homeFragment)
        }

        // nav to TransferFragment
        binding.trasnferBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_basicFragment_to_transferFragment)
        }

        // nav to TransactionsFragment
        binding.transactionsBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_basicFragment_to_transactionsFragment)
        }
    }
}