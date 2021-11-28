package com.example.zadanie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.zadanie.R
import com.example.zadanie.databinding.BasicFragmentBinding
import com.example.zadanie.ui.viewModels.BasicViewModel
import com.example.zadanie.ui.viewModels.TransferViewModel

class BasicFragment : Fragment() {
    private val basicViewModel: BasicViewModel by viewModels()
    private lateinit var binding: BasicFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.basic_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        nav to DatabaseFragment
//        binding.databaseBtn.setOnClickListener {
//            it.findNavController().navigate(R.id.go_to_database)
//        }
    }

}