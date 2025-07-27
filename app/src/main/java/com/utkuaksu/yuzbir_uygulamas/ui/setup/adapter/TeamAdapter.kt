package com.utkuaksu.yuzbir_uygulamas.ui.setup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utkuaksu.yuzbir_uygulamas.databinding.ItemTeamBinding
import com.utkuaksu.yuzbir_uygulamas.model.Team

class TeamAdapter(
    private val teams: List<Team>,
    private val onScoreIncrease: (position: Int) -> Unit
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTeamBinding.inflate(inflater, parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams[position]
        holder.binding.tvTeamName.text = team.name
        holder.binding.tvTeamScore.text = team.score.toString()

        holder.binding.btnAddScore.setOnClickListener {
            onScoreIncrease(position)
        }
    }


    override fun getItemCount(): Int = teams.size
}

