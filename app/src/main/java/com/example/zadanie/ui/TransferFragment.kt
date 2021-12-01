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
import com.example.zadanie.databinding.FragmentHomeBinding
import com.example.zadanie.databinding.TransferFragmentBinding
import com.example.zadanie.ui.viewModels.HomeViewModel
import com.example.zadanie.ui.viewModels.TransferViewModel
import com.opinyour.android.app.data.utils.Injection
import org.stellar.sdk.*
import org.stellar.sdk.responses.SubmitTransactionResponse
import org.stellar.sdk.Transaction.Builder

import org.stellar.sdk.responses.AccountResponse
import java.lang.Exception


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

//        homeViewModel.transformedText.observe(viewLifecycleOwner) {
//            Log.d("nasapremenna", "je: $it")
//        }

////      nav to DatabaseFragment
//        binding.databaseBtn.setOnClickListener {
//            it.findNavController().navigate(R.id.go_to_database)
//        }
    }
}