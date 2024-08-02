package com.example.task3a.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.task3a.GuessResult
import com.example.task3a.R
import com.example.task3a.viewmodel.GuessViewModel
import com.example.task3a.databinding.FragmentGuessBinding
import com.example.task3a.viewmodel.SharedViewModel

class GuessFragment : Fragment() {
    private lateinit var binding: FragmentGuessBinding
    private val viewModel: GuessViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        with(binding) {
            viewModel.randomChar.observe(viewLifecycleOwner) { char ->
                textViewLetter.text = char.toString()
            }

            viewModel.result.observe(viewLifecycleOwner) { result ->
                textViewResult.text = when(result) {
                    GuessResult.WIN -> getString(R.string.guess_result_win)
                    GuessResult.TRY_AGAIN -> getString(R.string.guess_result_try_again)
                }
            }

            viewModel.guessedNumber.observe(viewLifecycleOwner) { number ->
                textViewResult.text = number?.toString() ?: getString(viewModel.result.value?.message
                    ?: R.string.guess_result_try_again)
            }
        }
    }

    private fun setupClickListeners() = with(binding) {
        val numberButtons = listOf(
            button0,
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9
        )

        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                viewModel.selectNumber(index)
            }
        }

        buttonClear.setOnClickListener {
            viewModel.clearGuess()
        }

        buttonGuess.setOnClickListener {
            viewModel.guess()
        }

        textViewLetter.setOnClickListener {
            viewModel.randomNumber.value?.let { hiddenNumber ->
                sharedViewModel.setHiddenNumber(hiddenNumber)
                findNavController().navigate(R.id.action_guess_to_detail)
            }
        }
    }
}
