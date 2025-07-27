package com.utkuaksu.yuzbir_uygulamas.ui.setup.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.utkuaksu.yuzbir_uygulamas.databinding.FragmentPlayerSetupBinding

class PlayerSetupFragment : Fragment() {

    private var _binding: FragmentPlayerSetupBinding? = null
    private val binding get() = _binding!!

    private var selectedPlayerCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttons = listOf(binding.btn2, binding.btn3, binding.btn4)

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                selectedPlayerCount = index + 2 // 0 -> 2, 1 -> 3, 2 -> 4

                // Seçilen butonu vurgula
                buttons.forEach { it.setBackgroundColor(Color.LTGRAY) }
                button.setBackgroundColor(Color.GREEN)

                createPlayerInputFields(selectedPlayerCount)
            }
        }

        binding.continueButton.setOnClickListener {
            if (selectedPlayerCount == 0) {
                Toast.makeText(requireContext(), "Lütfen kişi sayısını seçin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val playerNames = mutableListOf<String>()
            for (i in 0 until selectedPlayerCount) {
                val editText = binding.playerNameContainer.getChildAt(i) as EditText
                val name = editText.text.toString().trim()
                if (name.isEmpty()) {
                    Toast.makeText(requireContext(), "Tüm oyuncu isimlerini girin", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                playerNames.add(name)
            }

            val action = PlayerSetupFragmentDirections
                .actionPlayerSetupToGame(
                    playerNames.toTypedArray(),
                    isTeamMode = false
                )
            findNavController().navigate(action)
        }
    }

    private fun createPlayerInputFields(count: Int) {
        binding.playerNameContainer.removeAllViews()

        for (i in 1..count) {
            val editText = EditText(requireContext()).apply {
                hint = "$i. Oyuncu Adı"
                textSize = 16f
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = 16
                }
            }
            binding.playerNameContainer.addView(editText)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}