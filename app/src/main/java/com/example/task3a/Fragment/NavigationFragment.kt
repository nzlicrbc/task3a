package com.example.task3a.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task3a.R
import com.example.task3a.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {
    private lateinit var binding: FragmentNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonCounter.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_to_counter)
            }

            buttonNumberGuess.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_to_number_guess)
            }
        }
    }
}