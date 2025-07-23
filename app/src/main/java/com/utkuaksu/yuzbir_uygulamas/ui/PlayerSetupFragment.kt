package com.utkuaksu.yuzbir_uygulamas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.utkuaksu.yuzbir_uygulamas.R
import com.utkuaksu.yuzbir_uygulamas.databinding.FragmentPlayerSetupBinding

class PlayerSetupFragment : Fragment() {

    private var _binding: FragmentPlayerSetupBinding? = null
    private val binding get() = _binding!!

    private var playerCount = 2 // Başlangıçta en az 2 kişi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Spinner'a kişi sayısı seçeneklerini bağla
        val playerOptions = listOf("2", "3", "4")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, playerOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPlayerCount.adapter = adapter

        // Spinner seçim dinleyicisi
        binding.spinnerPlayerCount.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                playerCount = position + 2 // 0 -> 2 kişi, 1 -> 3 kişi, 2 -> 4 kişi
                generateNameInputs(playerCount)

                // Eğer 4 kişi ise eşli oynama seçeneğini göster
                binding.switchTeamMode.visibility = if (playerCount == 4) View.VISIBLE else View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // "Oyuna Başla" butonuna tıklanınca
        binding.btnStartGame.setOnClickListener {
            val playerNames = mutableListOf<String>()
            for (i in 0 until binding.playerInputContainer.childCount) {
                val editText = binding.playerInputContainer.getChildAt(i) as EditText
                val name = editText.text.toString().trim()
                if (name.isEmpty()) {
                    editText.error = "İsim gerekli"
                    return@setOnClickListener
                }
                playerNames.add(name)
            }

            val isTeamMode = binding.switchTeamMode.isChecked

            // TODO: Verileri ViewModel'e veya bundle'a aktar
            findNavController().navigate(R.id.action_playerSetupFragment_to_gameFragment)
        }
    }

    private fun generateNameInputs(count: Int) {
        binding.playerInputContainer.removeAllViews()
        val inflater = LayoutInflater.from(requireContext())

        for (i in 1..count) {
            val editText = EditText(requireContext()).apply {
                hint = "$i. Oyuncu"
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 16, 0, 16)
                }
            }
            binding.playerInputContainer.addView(editText)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
