package com.example.zadanie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zadanie.R
import com.example.zadanie.databinding.BasicFragmentBinding
import com.example.zadanie.databinding.ContactsFragmentBinding
import com.example.zadanie.ui.viewModels.BasicViewModel
import com.example.zadanie.ui.viewModels.ContactsViewModel
import com.example.zadanie.ui.viewModels.DatabaseViewModel
import com.opinyour.android.app.data.utils.Injection

class ContactsFragment : Fragment() {
    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var binding: ContactsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.contacts_fragment, container, false)
        contactsViewModel =
            ViewModelProvider(this, Injection.provideViewModelFactory(requireContext()))
                .get(ContactsViewModel::class.java)

        binding.contactsModel = contactsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        nav to DatabaseFragment
//        binding.databaseBtn.setOnClickListener {
//            it.findNavController().navigate(R.id.go_to_database)
//        }

//      nav to AddContactFragment
        binding.addContactBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_contactsFragment_to_addContactFragment)
        }
    }
}