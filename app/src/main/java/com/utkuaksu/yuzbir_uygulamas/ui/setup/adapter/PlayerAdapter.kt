package com.utkuaksu.yuzbir_uygulamas.ui.setup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utkuaksu.yuzbir_uygulamas.databinding.ItemPlayerBinding
import com.utkuaksu.yuzbir_uygulamas.model.Player

class PlayerAdapter(
    private val players: List<Player>,
    private val onScoreAdd: (Int) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.binding.tvPlayerName.text = player.name
        holder.binding.tvScore.text = player.score.toString()

        holder.binding.btnAddScore.setOnClickListener {
            onScoreAdd(position)
        }
    }

    override fun getItemCount() = players.size
}
