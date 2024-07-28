package com.example.task3a.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.task3a.ViewModel.CounterViewModel
import com.example.task3a.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {
    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CounterViewModel by viewModels()
    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.counter.observe(viewLifecycleOwner, Observer { newValue ->
            binding.textViewCounter.text = newValue.toString()
        })

        binding.switchUseViewModel.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.textViewCounter.text = viewModel.counter.value.toString()
            } else {
                binding.textViewCounter.text = counter.toString()
            }
        }

        binding.buttonIncrement.setOnClickListener {
            if (binding.switchUseViewModel.isChecked) {
                viewModel.incrementCounter()
            } else {
                counter++
                binding.textViewCounter.text = counter.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}