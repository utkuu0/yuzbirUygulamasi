package com.utkuaksu.yuzbir_uygulamas.ui.setup.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.utkuaksu.yuzbir_uygulamas.databinding.FragmentTeamSetupBinding
import com.utkuaksu.yuzbir_uygulamas.model.Team
import com.utkuaksu.yuzbir_uygulamas.ui.setup.adapter.TeamAdapter

class TeamSetupFragment : Fragment() {

    private var _binding: FragmentTeamSetupBinding? = null
    private val binding get() = _binding!!

    private lateinit var teamList: MutableList<Team>
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTeamSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Başlangıçta 2 takım için boş isimlerle liste oluşturuyoruz
        teamList = mutableListOf(
            Team(name = "", score = 0),
            Team(name = "", score = 0)
        )

        adapter = TeamAdapter(teamList) { position ->
            // "+" butonuna tıklandığında puanı 1 artır
            teamList[position].score += 1
            adapter.notifyItemChanged(position)
        }

        binding.recyclerViewTeams.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTeams.adapter = adapter

        // Devam butonu tıklaması
        binding.btnContinue.setOnClickListener {
            // Takım isimlerini alalım, boş ise hata verelim
            var team1Name = binding.etTeam1Name.text.toString().trim()
            var team2Name = binding.etTeam2Name.text.toString().trim()

            if (team1Name.isEmpty()) {
                binding.etTeam1Name.error = "Takım 1 ismi boş olamaz"
                return@setOnClickListener
            }

            if (team2Name.isEmpty()) {
                binding.etTeam2Name.error = "Takım 2 ismi boş olamaz"
                return@setOnClickListener
            }

            teamList[0].name = team1Name
            teamList[1].name = team2Name

            val action = TeamSetupFragmentDirections.actionTeamSetupToGame(
                playerNames = teamList.map { it.name }.toTypedArray(),
                isTeamMode = true
            )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}