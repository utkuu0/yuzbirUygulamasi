package com.utkuaksu.yuzbir_uygulamas.ui.setup.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.utkuaksu.yuzbir_uygulamas.databinding.FragmentTeamScoreBinding
import com.utkuaksu.yuzbir_uygulamas.model.Team
import com.utkuaksu.yuzbir_uygulamas.ui.setup.adapter.TeamAdapter

class TeamScoreFragment : Fragment() {

    private var _binding: FragmentTeamScoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var teamList: MutableList<Team>
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTeamScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Geçici örnek veri (TeamSetupFragment'tan veri aktarılmalı aslında)
        teamList = mutableListOf(
            Team("Takım 1", 0),
            Team("Takım 2", 0)
        )

        adapter = TeamAdapter(teamList) { position ->
            teamList[position].score += 1
            adapter.notifyItemChanged(position)
        }

        binding.recyclerViewScores.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewScores.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
