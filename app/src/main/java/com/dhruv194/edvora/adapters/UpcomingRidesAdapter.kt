package com.dhruv194.edvora.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dhruv194.edvora.databinding.CardItemBinding
import com.dhruv194.edvora.dataclasses.RidesDataClass

class UpcomingRidesAdapter : RecyclerView.Adapter<UpcomingRidesAdapter.UpcomingRidesViewHolder>() {
    inner class UpcomingRidesViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallBAck = object : DiffUtil.ItemCallback<RidesDataClass>(){
        override fun areItemsTheSame(oldItem: RidesDataClass, newItem: RidesDataClass): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RidesDataClass, newItem: RidesDataClass): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallBAck)
    var upcomingrides : List<RidesDataClass>
        get() = differ.currentList
        set(value)  {differ.submitList(value) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingRidesViewHolder {
        return UpcomingRidesViewHolder(CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: UpcomingRidesViewHolder, position: Int) {
        holder.binding.apply {
            val urides = upcomingrides[position]
            itemCityName.text = urides.city
            itemStateName.text = urides.state
            itemRideId.text = urides.id.toString()
            itemOriginStation.text = urides.originStationCode.toString()
            itemStationPath.text = urides.stationPath.toString()
            itemDate.text = urides.date

        }
    }

    override fun getItemCount()= upcomingrides.size
}