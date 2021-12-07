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
import com.example.zadanie.databinding.ContactsFragmentBinding
import com.example.zadanie.databinding.TransactionsFragmentBinding
import com.example.zadanie.ui.viewModels.ContactsViewModel
import com.example.zadanie.ui.viewModels.TransactionsViewModel
import com.opinyour.android.app.data.utils.Injection

class TransactionsFragment : Fragment() {

    private lateinit var transactionsViewModel: TransactionsViewModel
    private lateinit var binding: TransactionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.transactions_fragment, container, false)
        transactionsViewModel =
            ViewModelProvider(this, Injection.provideViewModelFactory(requireContext()))
                .get(TransactionsViewModel::class.java)

        binding.transactionsModel = transactionsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}