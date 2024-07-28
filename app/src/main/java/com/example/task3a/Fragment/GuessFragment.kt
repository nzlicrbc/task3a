package com.example.task3a.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.task3a.ViewModel.GuessViewModel
import com.example.task3a.databinding.FragmentGuessBinding

class GuessFragment : Fragment() {
    private var _binding: FragmentGuessBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GuessViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.randomChar.observe(viewLifecycleOwner) { char ->
            binding.textViewLetter.text = char.toString()
        }

        viewModel.result.observe(viewLifecycleOwner) { result ->
            binding.textViewResult.text = result
        }

        viewModel.guessedNumber.observe(viewLifecycleOwner) { number ->
            if(number != null) {
                binding.textViewResult.text = number.toString()
            } else {
                binding.textViewResult.text = viewModel.result.value
            }
        }
    }

    private fun setupClickListeners() = with(binding) {
        val numberButtons = listOf (
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
    }
}
