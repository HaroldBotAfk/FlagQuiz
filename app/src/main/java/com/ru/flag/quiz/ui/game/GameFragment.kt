package com.ru.flag.quiz.ui.game

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ru.flag.quiz.R
import com.ru.flag.quiz.data.models.FlagCard
import com.ru.flag.quiz.databinding.FragmentGameBinding
import com.ru.flag.quiz.utils.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class GameFragment : Fragment(R.layout.fragment_game) {

    private val binding: FragmentGameBinding by viewBinding(FragmentGameBinding::bind)
    private val viewModel: GameViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        initButtons()

        Handler(Looper.getMainLooper()).post {
            viewModel.prepareGame()
        }
    }

    private fun bindViewModel() {
        viewModel.showQuestion.observe(viewLifecycleOwner) { flagCard ->
            updateQuestion(flagCard)
        }
        viewModel.trueButtonNumber.observe(viewLifecycleOwner) { buttonNumber ->
            paintButton(buttonNumber, requireContext().getColor(R.color.green))
        }
        viewModel.falseButtonNumber.observe(viewLifecycleOwner) { buttonNumber ->
            paintButton(buttonNumber, requireContext().getColor(R.color.red))
        }
        viewModel.neutralButtonNumber.observe(viewLifecycleOwner) { buttonNumber ->
            paintButton(buttonNumber, requireContext().getColor(R.color.white))
        }
        viewModel.isEnabledButtons.observe(viewLifecycleOwner) { boolean ->
            enableButtons(boolean)
        }
        viewModel.resultNavigate.observe(viewLifecycleOwner) { result ->
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToResultFragment(result))
        }
        viewModel.quantity.observe(viewLifecycleOwner) { counter ->
            binding.counterTextView.text = counter
        }
        viewModel.toast.observe(viewLifecycleOwner) { stringRes ->
            toast(stringRes)
        }
    }

    private fun initButtons() {
        binding.someFlagButton1.setOnClickListener {
            viewModel.checkAnswer(1, binding.someFlagButton1.text.toString())
        }
        binding.someFlagButton2.setOnClickListener {
            viewModel.checkAnswer(2, binding.someFlagButton2.text.toString())
        }
        binding.someFlagButton3.setOnClickListener {
            viewModel.checkAnswer(3, binding.someFlagButton3.text.toString())
        }
        binding.someFlagButton4.setOnClickListener {
            viewModel.checkAnswer(4, binding.someFlagButton4.text.toString())
        }
    }

    private fun enableButtons(boolean: Boolean) {
        binding.someFlagButton1.isEnabled = boolean
        binding.someFlagButton2.isEnabled = boolean
        binding.someFlagButton3.isEnabled = boolean
        binding.someFlagButton4.isEnabled = boolean
    }

    private fun updateQuestion(flagCard: FlagCard) {
        binding.flagImageView.setBackgroundResource(flagCard.flag)
        binding.someFlagButton1.text = flagCard.listAnswer[0]
        binding.someFlagButton2.text = flagCard.listAnswer[1]
        binding.someFlagButton3.text = flagCard.listAnswer[2]
        binding.someFlagButton4.text = flagCard.listAnswer[3]
    }

    private fun paintButton(buttonNumber: Int, color: Int) {
        when (buttonNumber) {
            1 -> binding.someFlagButton1.setBackgroundColor(color)
            2 -> binding.someFlagButton2.setBackgroundColor(color)
            3 -> binding.someFlagButton3.setBackgroundColor(color)
            4 -> binding.someFlagButton4.setBackgroundColor(color)
            else -> toast(R.string.error)
        }
    }
}