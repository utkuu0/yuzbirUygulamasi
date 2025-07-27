package com.utkuaksu.yuzbir_uygulamas.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.utkuaksu.yuzbir_uygulamas.databinding.FragmentGameBinding
import com.utkuaksu.yuzbir_uygulamas.model.Player
import com.utkuaksu.yuzbir_uygulamas.ui.setup.adapter.PlayerAdapter

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var playerList: MutableList<Player>
    private lateinit var adapter: PlayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val names = args?.getStringArray("playerNames") ?: arrayOf()
        val isTeamMode = args?.getBoolean("isTeamMode") ?: false

        // Bu fragment bireysel mod için ayarlanacak
        if (!isTeamMode) {
            setupIndividualGame(names.toList())
        } else {
            setupTeamGame(names.toList())
        }
    }

    //Takım
    private fun setupTeamGame(teamNames: List<String>) {
        playerList = teamNames.map { Player(it) }.toMutableList()
        sortPlayers()

        adapter = PlayerAdapter(playerList) { index ->
            playerList[index].score += 1
            sortPlayers()
            adapter.notifyDataSetChanged()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    //Bireysel
    private fun setupIndividualGame(playerNames: List<String>) {
        playerList = playerNames.map { Player(it) }.toMutableList()
        sortPlayers()

        adapter = PlayerAdapter(playerList) { index ->
            playerList[index].score += 1
            sortPlayers()
            adapter.notifyDataSetChanged()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun sortPlayers() {
        playerList.sortBy { it.score } // En küçük puanı en üstte göster
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
