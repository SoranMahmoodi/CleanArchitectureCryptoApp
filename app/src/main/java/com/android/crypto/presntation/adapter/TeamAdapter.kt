package com.android.crypto.presntation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.crypto.databinding.ItemTeamsBinding
import com.android.crypto.domain.entites.Team

class TeamAdapter : ListAdapter<Team, TeamAdapter.TeamViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            ItemTeamsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindData(currentList[position])
    }

    inner class TeamViewHolder(private val binding: ItemTeamsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(team: Team) {
            binding.tvItemTeamsName.text = team.name
            binding.tvItemTeamsPosition.text = team.position
        }

    }

}