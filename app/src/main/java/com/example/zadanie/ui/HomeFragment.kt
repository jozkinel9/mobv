package com.example.zadanie.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.zadanie.ui.viewModels.HomeViewModel
import com.example.zadanie.R
import com.example.zadanie.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.nasmodel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: 2. nahradit observer databindingom v xml
//        homeViewModel.word.observe(viewLifecycleOwner) { words_text.text = it }

        //TODO: 5. nahradit listener databindingom v xml
//        word_btn.setOnClickListener { changeWord() }

        //
        homeViewModel.transformedText.observe(viewLifecycleOwner){
            Log.d("nasapremenna", "je: $it")
        }

        //TODO: 6. umoznit navigaciu do DatabaseFragmentu po stlaceni tlacidla
    }

    //TODO: 5. odstanit funkciu pomocou databindingu v xml
//    private fun changeWord() {
//        val word = word_input.text.toString()
//        if (word.isNotEmpty()) {
//            homeViewModel.changeWord(word)
//            word_input.text.clear()
//        }
//    }
}
