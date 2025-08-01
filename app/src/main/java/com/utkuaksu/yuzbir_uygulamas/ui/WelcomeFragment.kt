package com.utkuaksu.yuzbir_uygulamas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.utkuaksu.yuzbir_uygulamas.R
import android.widget.Button
import com.utkuaksu.yuzbir_uygulamas.databinding.FragmentWelcomeBinding

// WelcomeFragment.kt
class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnIndividual.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_playerSetup)
        }

        binding.btnTeam.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_teamSetup)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

