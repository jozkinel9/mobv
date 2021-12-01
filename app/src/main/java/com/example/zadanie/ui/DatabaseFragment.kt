package com.example.zadanie.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zadanie.R
import com.example.zadanie.databinding.FragmentDatabaseBinding
import com.example.zadanie.ui.viewModels.DatabaseViewModel
import com.opinyour.android.app.data.utils.Injection


class DatabaseFragment : Fragment() {
    private lateinit var databaseViewModel: DatabaseViewModel
    private lateinit var binding: FragmentDatabaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_database, container, false)
        databaseViewModel =
            ViewModelProvider(this, Injection.provideViewModelFactory(requireContext()))
                .get(DatabaseViewModel::class.java)

        binding.datamodel = databaseViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        word_btn.setOnClickListener { addWord() }
    }

//    private fun addWord() {
//        val word = word_input.text.toString()
//        if (word.isNotEmpty()) {
//            databaseViewModel.insertWord(word)
//            word_input.text.clear()
//        }
//    }
}
