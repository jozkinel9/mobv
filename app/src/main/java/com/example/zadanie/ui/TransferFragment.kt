package com.example.zadanie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zadanie.R
import com.example.zadanie.databinding.ContactsFragmentBinding
import com.example.zadanie.databinding.TransferFragmentBinding
import com.example.zadanie.ui.viewModels.ContactsViewModel
import com.example.zadanie.ui.viewModels.TransferViewModel
import com.opinyour.android.app.data.utils.Injection

class TransferFragment : Fragment() {
    private lateinit var transferViewModel: TransferViewModel
    private lateinit var binding: TransferFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.transfer_fragment, container, false)
        transferViewModel =
            ViewModelProvider(this, Injection.provideViewModelFactory(requireContext()))
                .get(TransferViewModel::class.java)

        binding.transferModel = transferViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}