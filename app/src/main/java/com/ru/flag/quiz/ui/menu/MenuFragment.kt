package com.ru.flag.quiz.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ru.flag.quiz.R
import com.ru.flag.quiz.databinding.FragmentMenuBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val binding: FragmentMenuBinding by viewBinding(FragmentMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
    }

    private fun initButtons() {
        binding.startGameButton.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToGameFragment())
        }
        binding.developersButton.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToDevelopersFragment())
        }
        binding.exitButton.setOnClickListener {
            activity?.finish()
        }
    }
}