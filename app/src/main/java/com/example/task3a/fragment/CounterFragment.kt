package com.example.task3a.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.task3a.viewmodel.CounterViewModel
import com.example.task3a.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {
    private lateinit var binding: FragmentCounterBinding
    private val viewModel: CounterViewModel by viewModels()
    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.counter.observe(viewLifecycleOwner) { newValue ->
            binding.textViewCounter.text = newValue.toString()
        }

        with(binding) {
            switchUseViewModel.setOnCheckedChangeListener { _, isChecked ->
                textViewCounter.text = if (isChecked) {
                        viewModel.counter.value.toString()
                    } else {
                        counter.toString()
                    }
            }

            buttonIncrement.setOnClickListener {
                if (switchUseViewModel.isChecked) {
                    viewModel.incrementCounter()
                } else {
                    counter++
                    textViewCounter.text = counter.toString()
                }
            }
        }
    }
}