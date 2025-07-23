package com.utkuaksu.yuzbir_uygulamas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.utkuaksu.yuzbir_uygulamas.R

class TeamSetupFragment : Fragment() {

    private lateinit var etPlayer1: EditText
    private lateinit var etPlayer2: EditText
    private lateinit var etPlayer3: EditText
    private lateinit var etPlayer4: EditText
    private lateinit var btnStartGame: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_team_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etPlayer1 = view.findViewById(R.id.etPlayer1)
        etPlayer2 = view.findViewById(R.id.etPlayer2)
        etPlayer3 = view.findViewById(R.id.etPlayer3)
        etPlayer4 = view.findViewById(R.id.etPlayer4)
        btnStartGame = view.findViewById(R.id.btnStartGame)

        btnStartGame.setOnClickListener {
            val playerNames = listOf(
                etPlayer1.text.toString().trim(),
                etPlayer2.text.toString().trim(),
                etPlayer3.text.toString().trim(),
                etPlayer4.text.toString().trim()
            )

            if (playerNames.any { it.isEmpty() }) {
                Toast.makeText(requireContext(), "Lütfen tüm oyuncu isimlerini girin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bundle = Bundle().apply {
                putStringArrayList("playerNames", ArrayList(playerNames))
                putBoolean("isTeamMode", true)
            }

            findNavController().navigate(R.id.action_teamFragment_to_gameFragment, bundle)
        }
    }
}
