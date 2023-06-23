package com.multiplatform.clubdistances.homeScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.multiplatform.clubdistances.databinding.ClubRowItemBinding
import com.multiplatform.clubdistances.homeScreen.model.Club

class ClubAdapter : ListAdapter<Club, ClubAdapter.ViewHolder>(ClubDiffCallback()) {

    interface OnItemClickListener{
        fun onItemClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ClubRowItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val club = getItem(position)
        holder.bind(club)
    }

    inner class ViewHolder(private val binding: ClubRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(club: Club) {
            binding.clubName.text = club.clubName
            binding.clubLoft.text = club.clubLoft
            binding.clubBrand.text = club.clubBrand
            binding.clubDistance.text = club.distance.toString()
        }
    }
}

class ClubDiffCallback : DiffUtil.ItemCallback<Club>() {
    override fun areItemsTheSame(oldItem: Club, newItem: Club): Boolean {
        return oldItem.clubName == newItem.clubName
    }

    override fun areContentsTheSame(oldItem: Club, newItem: Club): Boolean {
        return oldItem == newItem
    }
}