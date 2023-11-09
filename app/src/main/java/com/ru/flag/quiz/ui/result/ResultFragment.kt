package com.ru.flag.quiz.ui.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ru.flag.quiz.R
import com.ru.flag.quiz.databinding.FragmentResultBinding

class ResultFragment : Fragment(R.layout.fragment_result) {

    private val binding: FragmentResultBinding by viewBinding(FragmentResultBinding::bind)
    private val args: ResultFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        binding.numberResultTextView.text = "${args.result}/20"
    }

    private fun init() {
        binding.tryAgainButton.setOnClickListener {
            findNavController().navigate(ResultFragmentDirections.actionResultFragmentToGameFragment())
        }
        binding.mainMenuButton.setOnClickListener {
            findNavController().navigate(ResultFragmentDirections.actionResultFragmentToMenuFragment())
        }
    }
}