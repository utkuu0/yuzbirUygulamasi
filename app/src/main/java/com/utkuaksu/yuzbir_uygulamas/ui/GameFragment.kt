package com.utkuaksu.yuzbir_uygulamas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.utkuaksu.yuzbir_uygulamas.R

class GameFragment : Fragment() {

    private lateinit var playerNames: List<String>
    private var isTeamMode: Boolean = false
    private val totalScores = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            playerNames = bundle.getStringArrayList("playerNames") ?: listOf("Oyuncu 1", "Oyuncu 2")
            isTeamMode = bundle.getBoolean("isTeamMode", false)
        }

        totalScores.clear()

        val playerContainer = view.findViewById<LinearLayout>(R.id.playerContainer)
        val inflater = LayoutInflater.from(requireContext())
        playerContainer.removeAllViews()

        if (isTeamMode && playerNames.size == 4) {
            // Takım modunda, 2 takım kartı göster

            // Takım 1 için puan
            totalScores.add(0) // Takım 1 puanı
            // Takım 2 için puan
            totalScores.add(0) // Takım 2 puanı

            for (teamIndex in 0..1) {
                val cardView = inflater.inflate(R.layout.team_item, playerContainer, false)

                val tvTeamName = cardView.findViewById<TextView>(R.id.tvTeamName)
                val tvTotalScore = cardView.findViewById<TextView>(R.id.tvTotalScore)
                val etRoundScore = cardView.findViewById<EditText>(R.id.etRoundScore)
                val btnAddScore = cardView.findViewById<Button>(R.id.btnAddScore)

                tvTeamName.text = if (teamIndex == 0) "Takım 1 (Oyuncular: ${playerNames[0]}, ${playerNames[2]})"
                else "Takım 2 (Oyuncular: ${playerNames[1]}, ${playerNames[3]})"

                tvTotalScore.text = "Toplam Puan: 0"

                btnAddScore.setOnClickListener {
                    val enteredScoreStr = etRoundScore.text.toString().trim()
                    if (enteredScoreStr.isEmpty()) {
                        etRoundScore.error = "Puan giriniz"
                        return@setOnClickListener
                    }

                    val enteredScore = try {
                        enteredScoreStr.toInt()
                    } catch (e: NumberFormatException) {
                        etRoundScore.error = "Geçerli sayı girin"
                        return@setOnClickListener
                    }

                    totalScores[teamIndex] += enteredScore
                    tvTotalScore.text = "Toplam Puan: ${totalScores[teamIndex]}"

                    etRoundScore.text.clear()
                }

                playerContainer.addView(cardView)
            }

        } else {
            // Takım modu değilse veya 4 oyuncudan farklıysa normal oyuncu kartları göster
            for ((index, name) in playerNames.withIndex()) {
                totalScores.add(0)

                val cardView = inflater.inflate(R.layout.player_item, playerContainer, false)

                val tvName = cardView.findViewById<TextView>(R.id.tvPlayerName)
                val tvTotalScore = cardView.findViewById<TextView>(R.id.tvTotalScore)
                val etRoundScore = cardView.findViewById<EditText>(R.id.etRoundScore)
                val btnAddScore = cardView.findViewById<Button>(R.id.btnAddScore)

                tvName.text = name
                tvTotalScore.text = "Toplam Puan: 0"

                btnAddScore.setOnClickListener {
                    val enteredScoreStr = etRoundScore.text.toString().trim()
                    if (enteredScoreStr.isEmpty()) {
                        etRoundScore.error = "Puan giriniz"
                        return@setOnClickListener
                    }

                    val enteredScore = try {
                        enteredScoreStr.toInt()
                    } catch (e: NumberFormatException) {
                        etRoundScore.error = "Geçerli sayı girin"
                        return@setOnClickListener
                    }

                    totalScores[index] += enteredScore
                    tvTotalScore.text = "Toplam Puan: ${totalScores[index]}"

                    etRoundScore.text.clear()
                }

                playerContainer.addView(cardView)
            }
        }
    }
}
