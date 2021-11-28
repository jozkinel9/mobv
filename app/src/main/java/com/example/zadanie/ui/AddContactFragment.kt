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
import com.example.zadanie.databinding.AddContactFragmentBinding
import com.example.zadanie.databinding.ContactsFragmentBinding
import com.example.zadanie.ui.viewModels.AddContactViewModel
import com.example.zadanie.ui.viewModels.ContactsViewModel
import com.example.zadanie.ui.viewModels.DatabaseViewModel
import com.opinyour.android.app.data.utils.Injection

class AddContactFragment : Fragment() {
    private lateinit var addContactViewModel: AddContactViewModel
    private lateinit var binding: AddContactFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_contact_fragment, container, false)
        addContactViewModel =
            ViewModelProvider(this, Injection.provideViewModelFactory(requireContext()))
                .get(AddContactViewModel::class.java)

        binding.modelAddContact = addContactViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        nav to DatabaseFragment
//        binding.databaseBtn.setOnClickListener {
//            it.findNavController().navigate(R.id.go_to_database)
//        }

//      nav to ContactsFragment
//        binding.addContactBtn.setOnClickListener {
//            it.findNavController().navigate(R.id.action_addContactFragment_to_contactsFragment)
//        }
    }

}